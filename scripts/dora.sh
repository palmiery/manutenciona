#!/usr/bin/env bash
# Coleta das métricas DORA do Manutenciona (docs/metricas-02.md).
#
# Requisitos: bash e GitHub CLI (`gh`) autenticado com acesso de leitura ao repositório.
# Não precisa de `jq` instalado: usa o jq embutido no `gh` (--jq).
#
# Uso:
#   bash scripts/dora.sh                 # repositório padrão palmiery/manutenciona
#   REPO=dono/repo bash scripts/dora.sh  # outro repositório
#   SINCE=2026-10-01 bash scripts/dora.sh  # só PRs integrados a partir dessa data
#
# Definições (detalhadas em docs/metricas-02.md):
#   - Implantação: PR integrado (merged) na branch `master`.
#   - PR de mudança: PR integrado que não é release (base `develop`, ou base `master` vindo de outra branch que não `develop`).
#   - Horários convertidos para UTC-3 (Natal). Durações em horas corridas.
set -euo pipefail

REPO="${REPO:-palmiery/manutenciona}"
SINCE="${SINCE:-2026-09-01}"   # início do período analisado (data de merge)

jqlib='
def t: fromdateiso8601;
def loc: (t - 10800);                                    # UTC-3
def fmt: (loc | strftime("%d/%m %H:%M"));
def wk: ((loc / 86400 | floor) as $d | ($d - (($d + 3) % 7)) * 86400);  # segunda-feira da semana (epoch)
def week: (wk | strftime("%d/%m"));
def h2: ((. * 10 | round) / 10);
def deploys: [ .[] | select(.baseRefName == "master") ] | sort_by(.mergedAt | t);
def changes: [ .[] | select(.baseRefName == "develop" or (.baseRefName == "master" and .headRefName != "develop")) ];
def firstcommit: ([ .commits[] | select(.messageHeadline | startswith("Merge ") | not) | .authoredDate ] | min) // .mergedAt;
def median: sort | if length == 0 then null elif length % 2 == 1 then .[length/2|floor] else (.[length/2 - 1] + .[length/2]) / 2 end;
def text: ([.title, (.body // "")] + [ .commits[].messageHeadline | select(startswith("Merge pull request") | not) ]) | join(" ");
def corrective: test("corrig|correç|fix|hotfix|erro|error|link"; "i");
def codepaths: any(.files[]?.path; test("^(frontend|backend)/src/"));
'

# O gh só aplica --jq ao que ele mesmo baixa, então cada bloco refaz a mesma consulta.
# --limit 49: com `commits` na consulta, a API GraphQL recusa mais que isso (49 x 100 x 100 nós).
# Se o período passar de 49 PRs integrados, rode por partes mudando SINCE.
Q() {
  gh pr list -R "$REPO" --state merged --limit 49 --search "merged:>=$SINCE" \
    --json number,title,body,baseRefName,headRefName,mergedAt,commits,files \
    --jq "$jqlib $1"
}

echo "== Repositório: $REPO  (coletado em $(date '+%d/%m/%Y %H:%M'))"
echo
echo "== 1. Implantações (PRs integrados na master)"

Q 'deploys | .[] | "#\(.number)\t\(.mergedAt | fmt)\tsemana de \(.mergedAt | week)\t\(.title)"'
echo
echo "== 2. Frequência de implantação por semana (semana começando na segunda)"
Q 'deploys as $d
   | ("'"$SINCE"'T03:00:00Z" | wk) as $w0 | (now | todate | wk) as $w1
   | [ range($w0; $w1 + 1; 604800) as $w | [ $d[] | select((.mergedAt | wk) == $w) ] as $in
       | {w: $w, n: ($in | length), prs: [ $in[].number ]} ] as $weeks
   | ($weeks[] | "\(.w | strftime("%d/%m"))\t\(.n)\t\(.prs | map("#\(.)") | join(" "))"),
     "TOTAL: \($d | length) implantações em \($weeks | length) semanas = \(($d | length) / ($weeks | length) | h2) por semana; semanas com pelo menos 1 implantação: \([ $weeks[] | select(.n > 0) ] | length) de \($weeks | length); mediana por semana: \($weeks | map(.n) | median)"'
echo
echo "== 3. Tempo de espera da mudança (1º commit do PR de mudança -> implantação na master), em horas"
Q '(deploys) as $d | changes | sort_by(.number) | map(. as $c
     | ($c | firstcommit) as $start
     | (if $c.baseRefName == "master" then $c else ([ $d[] | select((.mergedAt|t) >= ($c.mergedAt|t)) ] | first) end) as $dep
     | select($dep != null)
     | {n: $c.number, start: $start, dep: $dep.number, depAt: $dep.mergedAt, h: ((($dep.mergedAt|t) - ($start|t)) / 3600)})
   | (.[] | "#\(.n)\t1º commit \(.start | fmt)\t-> #\(.dep) em \(.depAt | fmt)\t\(.h | h2) h"),
     "-- por semana da implantação (mediana / máximo / N):",
     (group_by(.depAt | wk) | .[] | "semana de \(.[0].depAt | week)\tmediana \(map(.h) | median | h2) h\tmáx \(map(.h) | max | h2) h\tN=\(length)"),
     "MEDIANA: \(map(.h) | median | h2) h   MÉDIA: \((map(.h) | add / length) | h2) h   N=\(length)"'
echo
echo "== 4. Taxa de retrabalho e taxa de falha de mudança"
echo "   Retrabalho: implantação feita até 24 h após a anterior cujo texto (títulos, descrições e commits"
echo "   dos PRs incluídos) indica correção (regex: corrig|correç|fix|hotfix|erro|error|link)."
echo "   Falha: a implantação ANTERIOR a um retrabalho que alterou código (frontend/src ou backend/src)."
Q '(changes) as $ch | deploys | . as $d | [ range(0; length) as $i | $d[$i] as $cur
     | (if $i == 0 then null else $d[$i-1] end) as $prev
     | ([ $ch[] | select(.baseRefName == "develop"
           and (($prev == null) or ((.mergedAt|t) > ($prev.mergedAt|t)))
           and ((.mergedAt|t) <= ($cur.mergedAt|t))) ] + [$cur]) as $inc
     | ($inc | map(text) | join(" ")) as $txt
     | (($prev != null) and ((($cur.mergedAt|t) - ($prev.mergedAt|t)) <= 86400) and ($txt | corrective)) as $rework
     | {n: $cur.number, prev: $prev.number, at: $cur.mergedAt, prevAt: $prev.mergedAt, rework: $rework,
        code: ($inc | any(codepaths)), inc: [ $inc[].number ] } ]
   | (.[] | "#\(.n)\t\(.at | fmt)\tinclui \(.inc | map("#\(.)") | join(" "))\t\(if .rework then "RETRABALHO" + (if .code then " (código) -> falha em #\(.prev), recuperação \(((((.at|t) - (.prevAt|t)) / 60) | round)) min" else " (só documentação)" end) else "-" end)"),
     "TAXA DE RETRABALHO: \(map(select(.rework)) | length)/\(length) = \((map(select(.rework)) | length) * 1000 / length | round / 10)%",
     "TAXA DE FALHA DE MUDANÇA: \(map(select(.rework and .code)) | length)/\(length) = \((map(select(.rework and .code)) | length) * 1000 / length | round / 10)%",
     "TEMPO DE RECUPERAÇÃO (por falha): \([ .[] | select(.rework and .code) | "#\(.prev) -> #\(.n): \((((.at|t) - (.prevAt|t)) / 60) | round) min" ] | join("; "))"'
