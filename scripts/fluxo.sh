#!/usr/bin/env bash
# Mede as duas filas do mapa de fluxo de valor (docs/fluxo-de-valor.md) para PRs integrados
# a partir de SINCE:
#   1. Espera antes do início: criação do cartão (issue fechada pelo PR) -> 1º commit do PR.
#   2. Espera por revisão: abertura do PR -> 1ª revisão de outro integrante.
#
# Requisitos: bash e GitHub CLI (`gh`) autenticado. Não precisa de `jq` (usa o --jq do gh).
# Uso:
#   SINCE=2026-10-15 bash scripts/fluxo.sh   # "antes" (US01–US05, Sprint 1)
#   SINCE=2026-11-24 bash scripts/fluxo.sh   # "depois" (Sprint 2, medir em 30/11)
set -euo pipefail

REPO="${REPO:-palmiery/manutenciona}"
SINCE="${SINCE:-2026-11-24}"
UNTIL="${UNTIL:-2099-12-31}"

echo "== $REPO, PRs de mudança (base develop) integrados entre $SINCE e $UNTIL"
printf 'PR\tcartão\tespera antes do início (dias)\tespera por revisão (h)\n'

gh pr list -R "$REPO" --state merged --base develop --limit 40 --search "merged:$SINCE..$UNTIL" \
  --json number,createdAt,author,reviews,commits,closingIssuesReferences \
  --jq '.[] | . as $p
        | ([ .commits[] | select(.messageHeadline | startswith("Merge ") | not) | .authoredDate ] | min) as $c
        | ([ .reviews[] | select(.author.login != $p.author.login) | .submittedAt ] | min) as $r
        | "\(.number) \($c) \(.createdAt) \($r // "-") \([ .closingIssuesReferences[].number ] | join(","))"' |
while read -r pr first opened review issues; do
  wait_rev="-"
  if [ "$review" != "-" ]; then
    wait_rev=$(gh api rate_limit --jq "((\"$review\" | fromdateiso8601) - (\"$opened\" | fromdateiso8601)) / 3600 * 10 | round / 10")
  fi
  if [ -z "$issues" ]; then
    printf '#%s\t(sem cartão ligado)\t-\t%s\n' "$pr" "$wait_rev"
    continue
  fi
  for i in ${issues//,/ }; do
    wait_start=$(gh issue view "$i" -R "$REPO" --json createdAt \
      --jq "((\"$first\" | fromdateiso8601) - (.createdAt | fromdateiso8601)) / 86400 * 100 | round / 100")
    printf '#%s\t#%s\t%s\t%s\n' "$pr" "$i" "$wait_start" "$wait_rev"
  done
done
