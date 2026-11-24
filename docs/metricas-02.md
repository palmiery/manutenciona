# Métricas DORA — Sprint 2 (final)

* **Projeto:** Manutenciona · **Repositório:** [`palmiery/manutenciona`](https://github.com/palmiery/manutenciona)
* **Período analisado:** 01/09/2026 (início do projeto) até **24/11/2026 20:29** (data desta coleta).
* **Fonte:** API do GitHub (PRs, commits dos PRs, revisões, arquivos alterados), via GitHub CLI. Fuso: UTC-3. Durações em **horas corridas**.
* **Como reproduzir:** `bash scripts/dora.sh` (precisa só de `bash` e `gh` autenticado; usa o `jq` embutido no `gh`). O script imprime todas as tabelas deste documento.

> ⚠️ **Recoletar na véspera da entrega (29–30/11):** os PRs da Sprint 2 ainda não estavam integrados na data desta coleta. Rode `bash scripts/dora.sh` de novo e atualize as tabelas e a seção de interpretação antes do commit final.

---

## 1. O que conta como implantação

O projeto ainda não é publicado em servidor. A versão "no ar" é a que está na branch **`master`**: é dela que o professor e qualquer pessoa sobem o sistema (`docker compose up`).

* **Implantação = um PR integrado (merged) na `master`.**
* Como se conta: cada PR com `baseRefName == "master"` e `state == MERGED` conta como **uma** implantação, na data/hora do merge (`mergedAt`).
* **PR de mudança** = PR integrado que não é release: base `develop`, ou base `master` vindo de outra branch que não seja `develop`. Os PRs `develop → master` são releases: só levam para a `master` o que já foi integrado na `develop`.

Comando-base (lista as implantações):

```bash
gh pr list -R palmiery/manutenciona --state merged --base master --limit 49 \
  --json number,mergedAt,title \
  --jq 'sort_by(.mergedAt)[] | "#\(.number)\t\(.mergedAt)\t\(.title)"'
```

Conferência pelo Git (mostra os merges de PR na `master`):

```bash
git fetch origin && git log --first-parent origin/master --format='%h %ad %s' --date=iso | grep 'pull request\|(#'
```

> O Git mostra 11 das 13 implantações. Os commits de merge dos PRs `#2` e `#26` não estão mais no histórico atual da `master`, provavelmente por reescrita de histórico no início do projeto. Por isso a **fonte oficial é a API de PRs**, e não o `git log`.

---

## 2. Frequência de implantação

**Definição:** número de implantações (merges na `master`) por semana. A semana começa na segunda-feira.

**Comando:** seção `== 2` de `bash scripts/dora.sh`.

| Semana (seg.) | Implantações | PRs |
| :--- | :---: | :--- |
| 31/08 | 2 | #2, #5 |
| 07/09 | 5 | #25, #26, #29, #31, #34 |
| 14/09 | 3 | #36, #39, #41 |
| 21/09 | 0 | — |
| 28/09 | 0 | — |
| 05/10 | 0 | — |
| 12/10 | 3 | #57, #59, #65 |
| 19/10 | 0 | — |
| 26/10 | 0 | — |
| 02/11 | 0 | — |
| 09/11 | 0 | — |
| 16/11 | 0 | — |
| 23/11 | 0 | — *(Sprint 2 em andamento)* |

**Valor:** **13 implantações em 13 semanas = 1,0 por semana em média**. A **mediana por semana é 0**: só **4 das 13 semanas** tiveram alguma implantação.

Distribuição por dia: as 13 implantações aconteceram em apenas **8 dias distintos**. Em 4 desses dias houve 2 ou 3 implantações: 10/09 (2), 13/09 (3), 16/09 (2) e 15/10 (2). No dia 15/10, a release `#57` e a correção `#59` saíram com 1 hora de diferença.

**Limitações:**

* "Implantação" aqui é merge na `master`, não publicação em servidor.
* Releases que só levam documentação contam igual às que levam código. Das 13 implantações, só **3 alteraram código de aplicação** (`frontend/src` ou `backend/src`): `#2`, `#57` e `#59`.
* O PR `#26` repete o conteúdo do `#25`: foi uma ressincronização da `develop` depois de um *squash merge*.

---

## 3. Tempo de espera da mudança (*lead time for changes*)

**Definição:** tempo entre o **primeiro commit** de um PR de mudança e a **implantação que o levou para a `master`**.

* Para PRs com base `develop`, a implantação correspondente é **o primeiro PR integrado na `master` depois do merge na `develop`**. Exemplo: `#50` foi integrado na `develop` às 16:23 de 15/10 e chegou à `master` pela release `#57`, às 18:04.
* Para PRs com base direto na `master` (só o `#2`), a implantação é o próprio merge.
* Commits de merge (`Merge ...`) são ignorados ao escolher o primeiro commit. Eles têm datas antigas e distorceriam a medida.

**Comando:** seção `== 3` de `bash scripts/dora.sh`.

**Valor:** **mediana de 0,6 h** · média de 6,1 h · N = 22 PRs de mudança.

Distribuição por PR:

| PR | 1º commit | Implantação | Horas |
| :--- | :--- | :--- | ---: |
| #2 | 01/09 10:59 | #2 — 02/09 12:05 | 25,1 |
| #3 | 03/09 05:58 | #5 — 03/09 06:19 | 0,3 |
| #4 | 03/09 06:17 | #5 — 03/09 06:19 | 0,0 |
| #20 | 10/09 15:45 | #25 — 10/09 16:21 | 0,6 |
| #21 | 10/09 15:46 | #25 — 10/09 16:21 | 0,6 |
| #22 | 10/09 15:53 | #25 — 10/09 16:21 | 0,5 |
| #23 | 10/09 15:55 | #25 — 10/09 16:21 | 0,4 |
| #24 | 10/09 15:58 | #25 — 10/09 16:21 | 0,4 |
| #28 | 13/09 15:41 | #29 — 13/09 16:17 | 0,6 |
| #30 | 13/09 16:33 | #31 — 13/09 16:44 | 0,2 |
| #33 | 13/09 23:12 | #34 — 13/09 23:23 | 0,2 |
| #35 | 15/09 06:54 | #36 — 15/09 10:05 | 3,2 |
| #38 | 14/09 09:01 | #39 — 16/09 20:50 | **59,8** |
| #40 | 16/09 20:54 | #41 — 16/09 20:59 | 0,1 |
| #50 (US01) | 15/10 11:39 | #57 — 15/10 18:04 | 6,4 |
| #51 (US02) | 15/10 11:20 | #57 — 15/10 18:04 | 6,7 |
| #54 (US05) | 15/10 11:42 | #57 — 15/10 18:04 | 6,4 |
| #55 (US03) | 15/10 11:22 | #57 — 15/10 18:04 | 6,7 |
| #56 (US04) | 15/10 11:16 | #57 — 15/10 18:04 | 6,8 |
| #58 | 15/10 18:58 | #59 — 15/10 19:04 | 0,1 |
| #60 | 16/10 16:40 | #65 — 16/10 21:31 | 4,8 |
| #64 | 16/10 17:13 | #65 — 16/10 21:31 | 4,3 |

Distribuição por semana da implantação:

| Semana | Mediana | Máximo | N |
| :--- | ---: | ---: | :---: |
| 31/08 | 0,3 h | 25,1 h | 3 |
| 07/09 | 0,4 h | 0,6 h | 8 |
| 14/09 | 3,2 h | 59,8 h | 3 |
| 12/10 | **6,4 h** | 6,8 h | 8 |

**Limitações:**

* O relógio começa no **primeiro commit enviado ao GitHub**, não no início real do trabalho. As 5 histórias da Sprint 1 foram escritas localmente e só tiveram commit no dia 15/10, com os 5 primeiros commits em 26 minutos (11:16–11:42). Por isso esta métrica **subestima** o tempo real. O tempo antes do commit aparece no mapa de fluxo ([`fluxo-de-valor.md`](fluxo-de-valor.md)).
* A data do commit (`authoredDate`) é a do computador de quem fez o commit e pode ser reescrita por *rebase*.
* Os PRs de documentação da Sprint 0 (`#20`–`#24`) são pequenos e foram integrados em lote no mesmo dia, o que puxa a mediana para baixo.

---

## 4. Taxa de falha de mudança

**Definição:** proporção de implantações que **quebraram o sistema** e exigiram uma correção de código logo em seguida.

Regra usada (reproduzível, implementada no script):

1. Uma implantação é **de retrabalho** quando sai **até 24 h depois da anterior** e o texto dos PRs que ela inclui (títulos, descrições e commits) indica correção. Regex: `corrig|correç|fix|hotfix|erro|error|link`.
2. Se esse retrabalho **altera código de aplicação** (`frontend/src/` ou `backend/src/`), a implantação **anterior** conta como **falha**.

**Comando:** seção `== 4` de `bash scripts/dora.sh`.

| Implantação | Data | PRs incluídos | Classificação |
| :--- | :--- | :--- | :--- |
| #2 | 02/09 12:05 | #2 | — |
| #5 | 03/09 06:19 | #3, #4, #5 | Retrabalho (só documentação) |
| #25 | 10/09 16:21 | #20–#24, #25 | — |
| #26 | 10/09 19:59 | #26 | — |
| #29 | 13/09 16:17 | #28, #29 | — |
| #31 | 13/09 16:44 | #30, #31 | Retrabalho (só documentação) |
| #34 | 13/09 23:23 | #33, #34 | — |
| #36 | 15/09 10:05 | #35, #36 | — |
| #39 | 16/09 20:50 | #38, #39 | — |
| #41 | 16/09 20:59 | #40, #41 | Retrabalho (só documentação) |
| **#57** | 15/10 18:04 | #50, #51, #54, #55, #56, #57 | **Falha**: quebrou o build do frontend |
| #59 | 15/10 19:04 | #58, #59 | Retrabalho (código): corrige a falha do #57 |
| #65 | 16/10 21:31 | #60, #64, #65 | — |

**Valor:** **1 / 13 = 7,7%**.

Se considerarmos só as implantações que alteraram código de aplicação (`#2`, `#57`, `#59`), a taxa é **1 / 3 = 33%**.

**A falha:** a release `#57` levou para a `master` o `home.component.ts` sem o import de `DatePipe`. A descrição do PR `#58` registra o erro `TS2304: Cannot find name 'DatePipe'`, que quebrou o build do Angular. Os 5 PRs da `develop` passaram pela revisão sem que ninguém rodasse o build: **não havia CI**.

**Limitações:**

* A classificação depende das palavras usadas nos títulos e commits. Uma correção com título neutro não seria detectada.
* Não há registro de falhas percebidas por usuários, porque o sistema ainda não tem usuários reais.

---

## 5. Tempo de recuperação (*failed deployment recovery time*)

**Definição:** tempo entre a implantação que falhou e a implantação que a corrigiu.

**Comando:** linha `TEMPO DE RECUPERAÇÃO` da seção `== 4` de `bash scripts/dora.sh`.

| Falha | Correção | Tempo |
| :--- | :--- | ---: |
| #57 — 15/10 18:04 | #59 — 15/10 19:04 | **60 min** |

Decomposição, com fonte nos commits e no PR `#58`: **54 min** até a correção começar (primeiro commit do `#58` às 18:58) e **6 min** de correção e integração (18:58 → 19:04).

**Valor:** **60 min** (N = 1).

**Limitações:** há um único caso, então não dá para falar em média nem em tendência. O PR de correção (`#58`) e a release dele (`#59`) foram integrados **sem revisão de outro integrante**, por *bypass* de administrador, como registrado na retrospectiva 01.

---

## 6. Taxa de retrabalho (*rework rate*)

**Definição:** proporção de implantações **não planejadas**, feitas para corrigir algo de uma implantação anterior (código **ou** documentação). Usa a regra 1 da seção 4.

**Valor:** **4 / 13 = 30,8%** (`#5`, `#31`, `#41`, `#59`).

| Retrabalho | Corrige | Intervalo | O que corrigiu |
| :--- | :--- | ---: | :--- |
| #5 | #2 | 18 h | Informações da documentação inicial e arquivos temporários de editor versionados por engano |
| #31 | #29 | 27 min | Links do README (`#30`) |
| #41 | #39 | 9 min | Link do vídeo na proposta (`#40`) |
| #59 | #57 | 60 min | Build quebrado (`DatePipe`, `#58`) |

**Limitações:** a mesma da seção 4: depende do texto dos PRs.

---

## 7. Resumo

| Métrica | Valor | N |
| :--- | :--- | :---: |
| Frequência de implantação | 1,0 por semana (mediana 0; 4 de 13 semanas com implantação) | 13 implantações |
| Tempo de espera da mudança | mediana 0,6 h · média 6,1 h | 22 PRs |
| Taxa de falha de mudança | 7,7% (33% entre as implantações com código) | 13 |
| Tempo de recuperação | 60 min | 1 falha |
| Taxa de retrabalho | 30,8% | 13 |

---

## 8. Interpretação

**O que os números dizem**

1. **O trabalho chega em lotes.** A frequência média (1 por semana) parece saudável, mas a mediana semanal é 0. Houve **4 semanas seguidas sem implantação** (21/09 a 11/10), seguidas de um único dia, 15/10, em que entraram as 5 histórias do MVP, a falha e a correção. É o mesmo padrão de "trabalho em lote no fim da sprint" apontado na retrospectiva 01.
2. **O tempo de espera da mudança é curto porque o commit é tardio.** A mediana de 0,6 h não significa entrega rápida: o código só vai para o GitHub quando está pronto para integrar. O tempo de espera real está **antes do primeiro commit**, cerca de 35 dias no quadro (ver [`fluxo-de-valor.md`](fluxo-de-valor.md)). Na semana de 12/10 a mediana sobe para 6,4 h, por causa da fila de revisão de 4,4 h criada pelos 5 PRs abertos juntos.
3. **A única implantação grande de código falhou.** A release `#57` (US01–US05, +302 linhas) quebrou o build. Isso é consequência direta de não haver CI: nenhum dos 6 PRs envolvidos rodou build automaticamente. O CI com build, testes e lint em todo PR, criado nesta sprint, ataca exatamente essa causa.
4. **Quase um terço das implantações é retrabalho**, mas 3 dos 4 casos são correções de documentação feitas minutos depois (links, vídeo). Isso indica PRs integrados sem revisão: `#30`, `#40` e `#58` não tiveram revisão de outro integrante.
5. **A recuperação foi rápida (60 min)**, mas porque o administrador integrou a correção sem revisão. O tempo baixo veio de pular o processo, não de um processo bom.

**O que os números NÃO dizem**

* Não medem qualidade percebida por usuários: não há produção nem usuários reais.
* Com N = 1 falha e 3 implantações de código, as taxas de falha e de recuperação **não têm significância estatística**: descrevem um caso.
* O tempo de espera da mudança não captura o tempo de desenvolvimento local nem a espera no quadro. Para isso usamos o mapa de fluxo.
* As implantações de documentação inflam a frequência. Contando só código, foram 3 implantações em 13 semanas.

**Para a Sprint 2:** a meta é que o CI bloqueie PRs com build quebrado (taxa de falha → 0) e que as integrações sejam menores e mais frequentes, em vez de um lote no fim. A coleta de 29–30/11 deve mostrar se isso aconteceu.
