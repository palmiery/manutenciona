# Retrospectiva Final (Sprint 2) — Manutenciona

* **Data de realização:** __/11/2026 *(preencher)*
* **Participantes:** Allyson Carmo (`@AllysonGustavo1`), Allane Oliveira (`@AllaneE`), Renato Campos (`@palmiery`)
* **Referências:** [Retrospectiva 01](retrospectiva-01.md) · [Métricas DORA](metricas-02.md) · [Fluxo de valor](fluxo-de-valor.md) · [Uso de IA](uso-de-ia.md) · [Proteção de branch](protecao-de-branch.md) · [Quadro (Projects #10)](https://github.com/users/palmiery/projects/10) · cartões da Sprint 2: [#69](https://github.com/palmiery/manutenciona/issues/69)–[#78](https://github.com/palmiery/manutenciona/issues/78)

---

## 1. As ações da Retrospectiva 01 foram cumpridas?

Cada ação foi conferida contra a forma de verificação combinada na própria Retrospectiva 01 (Seção 3).

| Ação (cartão) | Responsável / prazo | Verificação combinada | Evidência | Cumprida? |
| :--- | :--- | :--- | :--- | :---: |
| **1** — Proteção também na `develop`, sem *bypass* ([#61](https://github.com/palmiery/manutenciona/issues/61)) | Renato · 19/10 | Regras ativas em `master` e `develop`; 100% dos PRs da S2 com review aprovado | Em 24/11, `gh api repos/palmiery/manutenciona/branches/develop --jq .protected` → `false`. As regras e o comando foram escritos em [`protecao-de-branch.md`](protecao-de-branch.md) em 24/11 e dependem do admin. *(Atualizar com a saída de `gh api .../branches/develop/protection` depois de aplicar.)* | ❌ no prazo · ⏳ em andamento |
| **2** — Template de PR com checklist XP ([#62](https://github.com/palmiery/manutenciona/issues/62)) | Allane · 20/10 | 100% dos PRs da S2 usando o template, com comentário técnico | O template só foi criado em 24/11 (PR [#79](https://github.com/palmiery/manutenciona/pull/79)), 35 dias depois do prazo. Os PRs #66–#68, abertos antes dele, não o usam. | ❌ no prazo · parcial |
| **3** — Datas no Projects, puxada sequencial e WIP uniforme ([#63](https://github.com/palmiery/manutenciona/issues/63)) | Allyson · 19/10 | 100% dos cartões da S2 com `Start date`/`Target date`; entregas distribuídas por semana | Nenhum commit em nenhuma branch entre 18/10 e 23/11 (`git log --all --since=2026-10-18 --until=2026-11-24` vazio). Os cartões da S2 (#69–#78) só foram criados em 24/11, e as entregas se concentraram na última semana. | ❌ |

**Por que não foram feitas?** Os dados mostram três causas, que se somam:

1. **As ações ficaram no `Backlog`.** Viraram cartões, mas nenhum foi puxado para `In progress` nem tinha data de início. O mesmo gargalo que o mapa de fluxo mostrou para as histórias (99,2% do lead time esperando antes do início) aconteceu com as próprias ações de melhoria.
2. **Não houve nenhum trabalho no repositório por 5 semanas** (18/10 a 23/11). A sprint coincidiu com as provas e com outras disciplinas *(confirmar o motivo real)*, e o grupo não tinha um marco intermediário que obrigasse a integrar algo antes do fim.
3. **Duas das três ações dependiam de uma pessoa específica e de permissão de admin** (proteção de branch). Sem um lembrete fixo (daily, prazo no quadro), ninguém cobrou.

---

## 2. Evolução do processo: Sprint 1 × Sprint 2 (com números)

| Indicador | Sprint 1 | Sprint 2 / fim do semestre | Fonte |
| :--- | :--- | :--- | :--- |
| Lead time médio das histórias | 26,2 dias corridos (janela da S1); 35,1 dias desde a criação do cartão | 35,1 dias (842,8 h) para as US01–US05, a base do MVP | [retro 01](retrospectiva-01.md), [fluxo de valor §3](fluxo-de-valor.md) |
| Eficiência de fluxo | não medida | **0,14%** (17,7% contando só do início do trabalho até a `master`) | [fluxo de valor §3](fluxo-de-valor.md) |
| Maior espera | Backlog/Ready (>90% do tempo) | Espera antes do início, **99,2%** do lead time; fila de revisão, 4 h 24 (65,9% da entrega) | [fluxo de valor §4](fluxo-de-valor.md) |
| Frequência de implantação | — | 1,0 por semana em média, **mediana 0**; só 4 de 13 semanas com implantação | [DORA §2](metricas-02.md) |
| Tempo de espera da mudança | — | mediana 0,6 h, média 6,1 h (22 PRs) | [DORA §3](metricas-02.md) |
| Taxa de falha de mudança | — | 7,7% (1 de 13); **33%** entre as implantações com código, por causa do build quebrado da release #57 | [DORA §4](metricas-02.md) |
| Tempo de recuperação | — | 60 min (#57 → #59), via merge do admin sem revisão | [DORA §5](metricas-02.md) |
| PRs com revisão de outro integrante | 6 de 8 (75%) | *(contar em 30/11: PRs da S2 aprovados ÷ PRs da S2)* | `gh pr list --state merged` |
| CI automatizado | inexistente | lint + build + testes em todo push e PR, e `docker compose` do zero ([`ci.yml`](../.github/workflows/ci.yml)) | Actions |
| Testes automatizados | 1 de backend e 2 de frontend, **1 quebrado desde 02/09** sem ninguém perceber | 10 de backend e 9 de frontend, todos verdes no CI | PR #66 |
| Como rodar o projeto | instalar Java, Node e PostgreSQL manualmente | `docker compose up --build` | README · *tempo medido com alguém de fora: __ min* |

**O que mudou de fato no processo.** Na Sprint 1, nada impedia integrar código quebrado: a release #57 quebrou o build e um teste ficou falhando por 2 meses sem ninguém ver. Agora o CI e a proteção de branch fazem esse controle de forma automática, em vez de depender de alguém lembrar. A taxa de falha de 33% nas implantações de código é o número que essa mudança ataca. O que **não** mudou é o ritmo: a Sprint 2 repetiu o lote no fim (5 semanas sem commit, tudo na última). As métricas de fluxo mostram que o problema do time nunca foi a velocidade de codificar (cerca de 70 min por história), e sim o tempo que o trabalho espera para começar.

---

## 3. Uso de IA

Registro completo em [`docs/uso-de-ia.md`](uso-de-ia.md). Resumo:

* **Onde ajudou:** na auditoria do repositório contra a rubrica e no trabalho de infraestrutura (CI, Docker, testes), que o grupo não dominava. Também coletou e calculou as métricas DORA e de fluxo a partir da API do GitHub, com comandos que qualquer um pode rodar de novo. Ela achou dois problemas antigos que ninguém tinha visto: o teste quebrado desde 02/09 e o `./mvnw` inutilizável.
* **Onde atrapalhou:** gerou muita mudança de uma vez (PR #66 com dezenas de arquivos), o que dificulta a revisão e repete o padrão de lote. Ela também não conseguiu rodar o Docker nem o build de produção na máquina local, então entregou código que só foi validado no CI. Por fim, a facilidade de "deixar a IA fazer" reforça a concentração do trabalho em uma pessoa no fim da sprint.
* **O que faríamos diferente:** usar a IA desde o início da sprint e em pedaços pequenos (um PR por assunto). Exigir que quem não escreveu o código revise linha a linha e saiba explicar cada parte. E registrar o uso no `uso-de-ia.md` no mesmo dia, não no fim.

---

## 4. Fatos, causas e ações para um próximo projeto

### Fatos
1. Nenhuma das 3 ações da retro 01 foi concluída no prazo combinado (Seção 1).
2. O repositório ficou sem nenhum commit de 18/10 a 23/11; o trabalho da S2 aconteceu na última semana. É o mesmo lote que a retro 01 apontou na S1 (5 PRs em 13 minutos no dia 15/10).
3. A `master` tinha um teste quebrado desde 02/09, e a única release com código (#57) quebrou o build, porque não havia CI.
4. As histórias levam cerca de 70 min de trabalho efetivo, mas ficam em média 35 dias no quadro (eficiência de fluxo de 0,14%).

### Causas (5 Porquês sobre o fato 2)
1. **Por que o trabalho se acumulou no fim?** Porque ninguém puxou cartões entre 18/10 e 23/11.
2. **Por que ninguém puxou?** Porque os cartões da S2 nem existiam: o planejamento (T1) só aconteceu em 24/11.
3. **Por que o planejamento atrasou?** Porque a sprint não tinha um ponto de controle intermediário com entrega: as aulas foram canceladas ou viraram revisão para a prova, e a daily só acontece no fim.
4. **Por que isso não foi percebido?** Porque o quadro não era olhado fora das aulas e nenhuma métrica era acompanhada durante a sprint, só calculada na retrospectiva.
5. **Causa raiz:** o processo dependia de eventos externos (aula, prazo) para começar o trabalho, em vez de uma cadência própria do time.

### Ações para um próximo projeto (até 3)
| # | Ação | Como saber se funcionou |
| :---: | :--- | :--- |
| 1 | Configurar CI e proteção de branch na **semana 1**, antes de qualquer feature | O primeiro PR do projeto já passa pelo gate; taxa de falha de mudança com código = 0 |
| 2 | Toda ação de retrospectiva entra direto em `In progress`, com data e dono, e é cobrada no primeiro encontro do time após a retro | 100% das ações concluídas até a metade da sprint seguinte |
| 3 | Cadência própria: pelo menos 1 PR integrado por integrante por semana, com o quadro revisado em 15 min toda segunda | A distribuição semanal de merges em `metricas-02.md` sem semanas zeradas |
