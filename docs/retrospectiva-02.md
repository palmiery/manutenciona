# Retrospectiva Final (Sprint 2) — Manutenciona

> **RASCUNHO.** Os fatos levantados no repositório até 24/11/2026 já estão preenchidos. Os trechos marcados com **[GRUPO]** dependem da reunião de retrospectiva e precisam ser completados e revisados por todos antes de 30/11.

* **Data de realização:** [GRUPO] __/11/2026
* **Participantes:** Allyson Carmo (`@AllysonGustavo1`), Allane Oliveira (`@AllaneE`), Renato Campos (`@palmiery`)
* **Referências:** [Retrospectiva 01](retrospectiva-01.md) · [Métricas DORA](metricas-02.md) · [Fluxo de valor](fluxo-de-valor.md) · [Uso de IA](uso-de-ia.md) · [Proteção de branch](protecao-de-branch.md) · [Quadro (Projects #10)](https://github.com/users/palmiery/projects/10)

---

## 1. As ações da Retrospectiva 01 foram cumpridas?

Cada ação foi conferida contra a forma de verificação combinada na própria Retrospectiva 01 (Seção 3).

| Ação (cartão) | Responsável / prazo | Verificação combinada | Evidência encontrada | Cumprida? |
| :--- | :--- | :--- | :--- | :---: |
| **1** — Proteção também na `develop`, sem *bypass* ([#61](https://github.com/palmiery/manutenciona/issues/61)) | Renato · 19/10 | Regras ativas em `master` e `develop`; 100% dos PRs da S2 com review aprovado | Em 24/11, `gh api repos/palmiery/manutenciona/branches/develop --jq .protected` → `false`; issue #61 aberta. [GRUPO: atualizar se a proteção for aplicada conforme `docs/protecao-de-branch.md`] | ❌ até 24/11 |
| **2** — Template de PR com checklist XP ([#62](https://github.com/palmiery/manutenciona/issues/62)) | Allane · 20/10 | 100% dos PRs da S2 usando o template, com comentário técnico | Em 24/11 não existe `.github/pull_request_template.md`; issue #62 aberta | ❌ até 24/11 |
| **3** — Datas no Projects, puxada sequencial e WIP uniforme ([#63](https://github.com/palmiery/manutenciona/issues/63)) | Allyson · 19/10 | 100% dos cartões da S2 com `Start date`/`Target date`; entregas distribuídas por semana | Nenhum commit em nenhuma branch entre 18/10 e 23/11 (`git log --all --since=2026-10-18 --until=2026-11-24` vazio); as entregas da S2 se concentraram na última semana. [GRUPO: conferir as datas dos cartões no quadro] | ❌ |

**Por que não foram feitas?** [GRUPO — responder com a causa real, não com justificativa. Pontos de partida observados nos dados: (a) as ações viraram cartões no `Backlog`, mas nenhum foi puxado para `In progress`; (b) entre 18/10 e 23/11 não houve nenhuma atividade no repositório (período de provas/outras disciplinas?); (c) nenhuma ação tinha um "gatilho" no dia a dia — dependiam de lembrar.]

---

## 2. Evolução do processo: Sprint 1 × Sprint 2 (com números)

| Indicador | Sprint 1 (fonte: [retro 01](retrospectiva-01.md)) | Sprint 2 (fonte) | Leitura |
| :--- | :--- | :--- | :--- |
| Lead time médio dos itens | 26,2 dias corridos (janela da S1) | [ver `fluxo-de-valor.md`] | |
| Cycle time médio (execução → Done) | ~5h45 | [ver `fluxo-de-valor.md`] | |
| Eficiência de fluxo | não medida | [ver `fluxo-de-valor.md`] | |
| Throughput | 1,5 issue/semana, tudo na semana 4 | [ver `metricas-02.md`] | |
| Frequência de implantação (merges na `master`) | — | [ver `metricas-02.md`] | |
| Tempo de espera da mudança (DORA) | — | [ver `metricas-02.md`] | |
| Taxa de falha de mudança | — | [ver `metricas-02.md`] | |
| PRs com revisão de outro integrante | 6 de 8 (75%) | [contar no fim da S2] | |
| CI automatizado | inexistente | lint + build + testes em todo push/PR, e `docker compose` do zero ([`ci.yml`](../.github/workflows/ci.yml)) | Antes, nada impedia integrar código quebrado — o próprio teste do frontend estava falhando na `master` sem ninguém perceber |
| Testes automatizados | 1 teste de backend (`contextLoads`) e 2 de frontend (1 quebrado) | +9 testes de backend, +7 de frontend, todos rodando no CI | |
| Como rodar o projeto | instalar Java, Node e PostgreSQL manualmente | `docker compose up --build` | [GRUPO: tempo medido com alguém de fora: __ min] |

**O que mudou de fato no processo:** [GRUPO — 3 a 5 linhas, ligando aos números acima.]

---

## 3. Uso de IA

Registro completo em [`docs/uso-de-ia.md`](uso-de-ia.md). Resumo:

* **Onde ajudou:** [GRUPO]
* **Onde atrapalhou:** [GRUPO]
* **O que faríamos diferente:** [GRUPO]

---

## 4. Fatos, causas e ações para um próximo projeto

### Fatos
1. Nenhuma das 3 ações da retro 01 foi concluída no prazo combinado (Seção 1).
2. O repositório ficou sem nenhum commit de 18/10 a 23/11; o trabalho da S2 aconteceu na última semana — o mesmo padrão de lote que a retro 01 apontou na S1 (5 PRs em 13 minutos no dia 15/10).
3. A `master` tinha um teste quebrado desde a Sprint 0 e ninguém percebeu, porque não havia CI.
4. [GRUPO]

### Causas
[GRUPO — 5 Porquês sobre o fato 2 (por que o trabalho se acumula no fim?). Hipótese a confirmar: a sprint não tinha marcos intermediários com entrega, e o quadro não era olhado entre as aulas.]

### Ações para um próximo projeto (até 3)
| # | Ação | Como saber se funcionou |
| :---: | :--- | :--- |
| 1 | [GRUPO] Ex.: CI e proteção de branch configurados na **semana 1**, antes de qualquer feature | Primeiro PR do projeto já passa pelo gate |
| 2 | [GRUPO] Ex.: cada ação de retrospectiva entra direto em `In progress` com data, e é revista na daily seguinte | 100% das ações concluídas até a metade da sprint seguinte |
| 3 | [GRUPO] Ex.: meta de pelo menos 1 PR integrado por integrante por semana | Distribuição semanal de merges em `metricas-02.md` sem semanas zeradas |
