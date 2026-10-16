# 🔄 Retrospectiva da Sprint 1 — Manutenciona

* **Período da Sprint 1:** 17/09/2026 a 16/10/2026
* **Data de Realização:** 16/10/2026
* **Participantes:**
  * Allyson Carmo (`@AllysonGustavo1`) — Product Owner / Developer
  * Allane Oliveira (`@AllaneE`) — Scrum Master / Developer
  * Renato Campos (`@palmiery`) — Tech Lead / Developer
* **Referências:** [Quadro Kanban — GitHub Projects #10](https://github.com/users/palmiery/projects/10) · [Acordo de Processo (`docs/proposta.md`)](proposta.md#5-acordo-de-processo)

---

## 1. Fatos e Métricas de Fluxo

Os dados abaixo foram coletados diretamente do **Quadro Kanban ([GitHub Projects #10](https://github.com/users/palmiery/projects/10))** e do histórico do repositório utilizando a GitHub CLI (`gh`) e o Git:

```bash
gh pr list --repo palmiery/manutenciona --state all --json number,title,state,author,createdAt,mergedAt,reviews
gh issue list --repo palmiery/manutenciona --state all --json number,title,state,createdAt,closedAt,assignees
git log origin/master --format='%ad | %h | %an | %s' --date=iso
```

### 1.1 Estado Real do Quadro Kanban e Resumo Quantitativo da Sprint 1

O quadro no GitHub Projects #10 foi estruturado com **5 colunas e políticas explícitas de entrada e saída** registradas na descrição de cada coluna (`Backlog`, `Ready`, `In progress [WIP 5]`, `In review [WIP 5]` e `Done`), além do campo `Estimate` preenchido em todos os cartões da Sprint 0 e Sprint 1.

| Indicador / Fato Observado | Valor / Número | Evidência no Repositório e no Quadro |
| :--- | :---: | :--- |
| **Histórias de Usuário entregues no incremento** | **5 de 5 (100% — 18 pts)** | US01 (`#6`, 5 pts), US02 (`#7`, 3 pts), US03 (`#8`, 3 pts), US04 (`#9`, 5 pts) e US05 (`#10`, 2 pts) em `Done` |
| **Pull Requests integrados na Sprint 1** | **8 PRs** | PRs `#50`, `#51`, `#54`, `#55`, `#56` (1 pt cada), `#57` (Release S1, 1 pt) e `#58`/`#59` (Refactor) |
| **PRs com revisão registrada (`APPROVED`)** | **6 de 8 (75%)** | PRs `#50` a `#57` revisados; PRs `#58` e `#59` (hotfix pós-integração do `DatePipe`) integrados via *bypass* de admin |
| **Ocupação máxima de WIP na coluna `In review`** | **5/5 itens (100% do limite)** | Em 15/10/2026, os 5 PRs (`#50` a `#56`) entraram juntos em revisão, atingindo o teto `WIP 5` da coluna |
| **Preenchimento dos campos `Start date` e `Target date`** | **0 de 32 cartões** | Os campos `Estimate` foram preenchidos em 100% dos cartões da S1, mas `Start date` e `Target date` ficaram em branco no Projects |

---

### 1.2 Tabela de Métricas por Item Concluído (Lead Time e Cycle Time)

> **Convenção adotada pela equipe (Seções 7.6 e 10.2 do guia):**
> Como os campos `Start date` e `Target date` do GitHub Projects não foram preenchidos manualmente durante a sprint, as datas foram extraídas dos eventos de movimentação do quadro (`project_v2_item_status_changed`) e dos timestamps de abertura/merge dos PRs:
> * **Lead Time:** medido em **dias corridos** desde a entrada do cartão no quadro (`added_to_project_v2`) até a chegada em `Done` (`closedAt` / `mergedAt`).
> * **Cycle Time:** medido desde a entrada em `In progress` / início da implementação e abertura do PR até a conclusão da revisão e merge em `Done`.

| Cartão / Issue | Estimativa | PR Vinculado | Entrada no Quadro (`Backlog`) | Início (`In progress` / PR) | Pronto (`Done` / Merge) | Lead Time (dias corridos) | Cycle Time (Execução → `Done`) |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| **US01 (`#6`)** — Dashboard por status | 5 pts | `#50` (1 pt) | 10/09/2026 | 15/10 (14h12 UTC) | 15/10 (21h06 UTC) | 35 dias *(28 na S1)* | **6h 54min** (~0,5 dia) |
| **US02 (`#7`)** — Abertura de solicitação | 3 pts | `#51` (1 pt) | 10/09/2026 | 15/10 (14h55 UTC) | 15/10 (21h06 UTC) | 35 dias *(28 na S1)* | **6h 11min** (~0,5 dia) |
| **US03 (`#8`)** — Alterar status/prioridade | 3 pts | `#55` (1 pt) | 10/09/2026 *(Ready: 27/09)* | 15/10 (15h04 UTC) | 15/10 (21h06 UTC) | 35 dias *(18 em Ready)* | **6h 02min** (~0,5 dia) |
| **US04 (`#9`)** — Salas e equipamentos | 5 pts | `#56` (1 pt) | 10/09/2026 | 15/10 (15h06 UTC) | 15/10 (21h06 UTC) | 35 dias *(28 na S1)* | **6h 00min** (~0,5 dia) |
| **US05 (`#10`)** — Filtrar por status e sala | 2 pts | `#54` (1 pt) | 10/09/2026 | 15/10 (15h00 UTC) | 15/10 (21h06 UTC) | 35 dias *(28 na S1)* | **6h 06min** *(PR: 4h 57m)* |
| **T2 (`#44`)** — Políticas e limites de WIP | 1 pt | `#60` | 27/09/2026 | 14/10 (08h00 UTC) | 14/10 (11h16 UTC) | 17 dias | **~3h 16min** (~0,5 dia) |
| **Refactor** — Remoção de `DatePipe` | — | `#58` / `#59` | 15/10/2026 | 15/10 (22h01 UTC) | 15/10 (22h04 UTC) | < 1 dia | **3 min** |

---

### 1.3 Consolidação das Quatro Métricas de Fluxo e Lei de Little

* **Lead Time Médio:**
  * Desde a entrada no quadro na Sprint 0 (10/09): $\frac{35 + 35 + 35 + 35 + 35 + 17}{6} = \mathbf{32,0\text{ dias corridos}}$.
  * Considerando a janela da Sprint 1 (de 17/09 a 15/10): $\frac{28 \times 5 + 17}{6} = \mathbf{26,2\text{ dias corridos}}$.
* **Cycle Time Médio (Execução e Revisão):**
  * Tempo médio entre a movimentação para `In progress` / abertura do PR e a chegada em `Done`: **~5h 45min** (ou **~0,5 a 1,0 dia corrido** incluindo a codificação local no dia 15/10).
* **Tempo Médio de Espera (Fila antes do início):**
  * $\text{Lead Time} - \text{Cycle Time} \approx 26,2 - 1,0 = \mathbf{25,2\text{ dias}}$. Os cartões passaram mais de **90% do tempo aguardando em `Backlog` / `Ready`** antes de entrarem em execução.
* **Throughput (Vazão por Semana na Sprint 1):**
  * **Semanas 1 a 3 (17/09 a 07/10):** 0 cartões concluídos em `Done`.
  * **Semana 4 (08/10 a 16/10):** **6 issues concluídas** (`#6`, `#7`, `#8`, `#9`, `#10`, `#44`, somando **19 pontos**) + **8 PRs integrados**.
  * **Throughput médio da sprint:** $\frac{6\text{ issues}}{4\text{ semanas}} = \mathbf{1,5\text{ issues/semana}}$ (ou **3,0 cartões/semana** contando issues + PRs no quadro).
* **WIP Médio (pela Lei de Little):**
  * $\text{Throughput diário} = \frac{6\text{ itens}}{28\text{ dias}} \approx 0,214\text{ item/dia}$.
  * $\text{WIP médio ativo} = \text{Throughput} \times \text{Cycle Time} = 0,214 \times 1,0\text{ dia} \approx \mathbf{0,21\text{ item}}$ ao longo da sprint, porém concentrado num pico de **5 itens simultâneos** (100% do `WIP 5`) no dia 15/10.
* **Cartão que mais demorou e onde ficou parado (Gargalo):**
  * Na fila de revisão (`In review`), o **PR `#54` / US05 (`#10` — Filtrar chamados por status e sala)** foi o que mais demorou para ser integrado (**4h 57min** aberto até o merge às 19h57 UTC), pois os merges anteriores das US01–US04 na `develop` geraram conflito em `home.component.ts` e `SolicitacaoService.java`, exigindo um commit extra de resolução (`2709e3e`).
  * No quadro geral, a **US03 (`#8`)** ficou **18 dias parada na coluna `Ready`** (de 27/09 a 15/10) esperando puxada para `In progress`.

---

## 2. Análise de Causas (5 Porquês)

A equipe reuniu-se com o quadro e as métricas abertas e aplicou os **5 Porquês** aos três problemas principais observados na Sprint 1:

### Problema A: Trabalho em Lote no Fim da Sprint (*Mura*) e Lotação da Coluna `In review` (5/5)
* **Fato:** As 5 histórias (`#6` a `#10`) foram movidas para `In progress` e tiveram seus 5 PRs (`#50` a `#56`) abertos num intervalo de 13 minutos no dia 15/10, lotando a coluna `In review` (5/5) e causando conflito de merge no PR `#54`.
  1. **Por que os 5 PRs foram abertos e movidos juntos no dia 15/10?**
     Porque as 5 histórias foram desenvolvidas localmente em sequência e só subiram para revisão quando o pacote completo do incremento estava pronto.
  2. **Por que não foram entregues e revisadas uma a uma ao longo das semanas?**
     Porque a equipe tratou o incremento da Sprint 1 como uma entrega única de fim de sprint, em vez de fluxo contínuo semanal.
  3. **Por que o limite de WIP não impediu o acúmulo de 5 PRs simultâneos?**
     Porque o limite de WIP foi ajustado para `WIP 5` para comportar as 5 histórias do MVP ao mesmo tempo, o que na prática permitiu que 100% do trabalho da sprint ficasse aberto simultaneamente.
  4. **Por que os cartões não foram movidos gradualmente e com datas registradas ao longo das semanas?**
     Porque os campos `Start date` e `Target date` do GitHub Projects não foram preenchidos no momento em que o trabalho começava.
  5. **Por que isso ocorreu?** *(Causa Raiz)*
     Porque faltou a regra de **puxada sequencial individual** (terminar e integrar 1 história na `develop` antes de abrir a branch da próxima) e o hábito de preencher `Start date` ao mover o cartão para `In progress`.

---

### Problema B: Revisões de Código (Prática XP) superficiais nos PRs em lote e Uso de *Bypass* em Hotfix (`#58`/`#59`)
* **Fato:** Os PRs `#50` a `#56` receberam aprovações curtas (*"Ok"*, *"pode subir"*), enquanto apenas o PR `#57` teve discussão detalhada de revisão, e os PRs `#58`/`#59` (remoção do `DatePipe` pós-integração) foram integrados em 3 minutos sem revisão (`reviews: []`).
  1. **Por que os PRs `#50` a `#56` tiveram comentários curtos e os `#58`/`#59` entraram sem review?**
     Nos PRs `#50` a `#56`, porque 5 PRs caíram ao mesmo tempo na fila de revisão no dia 15/10; já nos PRs `#58`/`#59`, porque surgiu um bug/refatoração rápida do `DatePipe` logo após o merge da release `#57` e o administrador fez o merge direto para corrigir rápido.
  2. **Por que foi possível integrar o `#58` na `develop` e o `#59` na `master` sem revisão de outro membro?**
     Porque a regra de proteção estava configurada apenas na `master` (com permissão de *bypass* pelo administrador do repositório) e ainda não estava aplicada na branch `develop`.
  3. **Por que o bug do `DatePipe` só foi percebido após a integração na `master` (`#57`)?**
     Porque a revisão em lote dos 5 PRs anteriores na `develop` foi feita sob pressão de tempo e sem um checklist padronizado de validação no PR.
  4. **Por que isso aconteceu?** *(Causa Raiz)*
     Porque faltava estender a regra de proteção para a branch `develop` (desativando *bypass* sem revisão) e adotar um template de Pull Request com checklist técnico mínimo para o revisor.

---

### Problema C: Divergência Pontual nas Políticas de WIP entre Colunas e Documentação
* **Fato:** Na descrição da coluna `Ready` do quadro constava *"respeitando o limite de WIP de 3 cartões"*, enquanto nas colunas `In progress` e `In review` (e no PR `#60`) o limite foi atualizado para `WIP 5`.
  1. **Por que houve diferença entre o texto da coluna `Ready` (WIP 3) e `In progress`/`In review` (WIP 5)?**
     Porque os limites de WIP foram atualizados durante a sprint para 5, mas o texto descritivo da coluna `Ready` manteve a referência anterior de 3 cartões.
  2. **Por que um limite `WIP 5` para uma equipe de 3 pessoas é alto segundo a Lei de Little?** *(Causa Raiz)*
     Porque com `WIP 5` quase todo o backlog da sprint cabe em andamento ao mesmo tempo, reduzindo o efeito de sistema puxado e aumentando o tempo de fila e o risco de conflitos entre branches.

---

## 3. Plano de Ações para a Sprint 2

Para atacar diretamente as causas raízes identificadas, a equipe definiu **3 ações concretas**, já cadastradas como cartões no `Backlog` do [GitHub Projects #10](https://github.com/users/palmiery/projects/10):

| # | Cartão no Quadro | Ação Concreta (O que será feito) | Causa Atacada | Responsável | Prazo | Como será verificado na Sprint 2 |
| :---: | :---: | :--- | :--- | :---: | :---: | :--- |
| **Ação 1** | [`#61`](https://github.com/palmiery/manutenciona/issues/61) | **Estender a *Branch Protection* para a `develop` e restringir *bypass* sem revisão:** aplicar na `develop` a mesma trava de revisão já existente na `master` e exigir ao menos 1 aprovação mesmo para administradores (inclusive em hotfixes/refatorações). | Problema B (Merge via bypass nos PRs `#58`/`#59` e proteção na `develop`) | **Renato Campos** (`@palmiery`) | **19/10/2026** (Início da Sprint 2) | Regras ativas tanto em `master` quanto em `develop`; 100% dos PRs da Sprint 2 (incluindo `refactor`/`fix`) com review aprovado. |
| **Ação 2** | [`#62`](https://github.com/palmiery/manutenciona/issues/62) | **Criar Template de Pull Request (`.github/pull_request_template.md`) com Checklist de Code Review XP**, exigindo ao menos 1 comentário técnico construtivo no código antes do *Approve* e aviso no WhatsApp. | Problema B (Qualidade da Prática XP de Revisão) | **Allane Oliveira** (`@AllaneE`) | **20/10/2026** | 100% dos PRs da Sprint 2 preenchidos com o template e contendo comentários técnicos de revisão. |
| **Ação 3** | [`#63`](https://github.com/palmiery/manutenciona/issues/63) | **Preencher `Start date`/`Target date` no Projects, Puxada Sequencial e Uniformização do WIP:** preencher as datas do cartão ao movê-lo para `In progress`, alinhar o texto de WIP da coluna `Ready` e proibir abrir 2ª branch `feature/...` sem integrar a anterior. | Problemas A e C (Acúmulo no fim da sprint e campos de data vazios) | **Allyson Carmo** (`@AllysonGustavo1`) | **19/10/2026** (Sprint Planning S2) | 100% dos cartões da Sprint 2 no GitHub Projects com `Start date` e `Target date` preenchidos e entregas distribuídas semanalmente. |

---

## 4. Revisão do Acordo de Processo (Mudanças para a Sprint 2)

A partir dos dados da Sprint 1 e das atualizações consolidadas no PR `#60` e nesta retrospectiva, o **Acordo de Processo** (`docs/proposta.md`, Seção 5) passa a vigorar com os seguintes ajustes para a Sprint 2:

1. **Cadência e Limites de WIP Alinhados ao Quadro:**
   * Cadência ajustada para **3 Sprints** (conforme PR `#60`).
   * Unificação da descrição de WIP entre todas as colunas do GitHub Projects e o documento de proposta, adotando a regra prática de **1 branch ativa por vez por desenvolvedor** antes de abrir novo PR.
2. **Uso Obrigatório dos Campos de Data no GitHub Projects (`Start date` e `Target date`):**
   * Ao mover qualquer cartão de `Ready` para `In progress`, o responsável preenche imediatamente o campo **`Start date`** no GitHub Projects #10; ao concluir o merge e mover para `Done`, registra a data final em **`Target date`**, permitindo cálculo direto de *Lead Time* e *Cycle Time* pelo próprio quadro na Sprint 2.
3. **Trava Mecânica e Qualidade da Prática XP (Revisão de Código):**
   * Com o *Branch Protection* ativo na `develop` e `master` (`#61`) e o template de PR (`#62`), toda revisão de código deve conter pelo menos um comentário técnico avaliando a implementação ou validação antes do *Approve*.
