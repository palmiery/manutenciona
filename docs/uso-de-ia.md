# Uso de IA no projeto

> Registro de onde a equipe usou ferramentas de IA, para a avaliação na [Retrospectiva Final](retrospectiva-02.md).
> A Seção 1 registra o uso de IA na Sprint 2 (24/11/2026). A Seção 2 traz o uso de cada integrante ao longo do semestre.

Commits e PRs feitos com ajuda de IA levam a linha `Co-Authored-By: Claude …` na mensagem — dá para listá-los com:

```bash
git log --all --grep="Co-Authored-By: Claude" --format='%h %ad %an %s' --date=short
```

## 1. Sprint 2 — Claude Code (agente de código), conduzido por Allyson

| Tarefa | O que a IA fez | O que o humano fez / precisa fazer | Resultado verificável |
| :--- | :--- | :--- | :--- |
| Ler as instruções da Sprint 2 e auditar o repositório | Leu `SPRINT-2.md`, `SPRINT-2-TAREFAS.md` e a rubrica, e comparou com a `master` via `gh` (workflows, proteção, PRs, issues, docs) | Pediu o diagnóstico e decidiu o que fazer | Lista do que faltava: sem CI, compose só com o banco, docs da S2 inexistentes |
| CI, lint e testes | Escreveu `.github/workflows/ci.yml`, configurou Checkstyle e Prettier, corrigiu o teste do frontend que falhava desde a Sprint 0 e escreveu 16 testes novos | Revisar o PR, conferir se os testes testam o comportamento certo das US | PR [#66](https://github.com/palmiery/manutenciona/pull/66) e execuções em *Actions* |
| Docker | Escreveu os Dockerfiles, `nginx.conf` e o `docker-compose.yml` com health checks | Testar `docker compose up --build` num clone limpo e cronometrar com alguém de fora | Job `docker compose (sobe do zero)` no CI |
| Métricas DORA e mapa de fluxo | Coletou os dados dos PRs/issues com `gh`, calculou as métricas e escreveu `metricas-02.md`, `fluxo-de-valor.md` e `scripts/dora.sh` | Conferir as definições, rodar os comandos de novo e validar a interpretação; decidir a melhoria e o responsável | PR [#68](https://github.com/palmiery/manutenciona/pull/68) |
| Planejamento e ações da retro 01 | Criou os cartões T1–T10 da Sprint 2 ligados aos PRs e o template de PR (ação 2 da retro 01) | Conferir responsáveis e datas no quadro | Issues [#69](https://github.com/palmiery/manutenciona/issues/69)–[#78](https://github.com/palmiery/manutenciona/issues/78), PR [#79](https://github.com/palmiery/manutenciona/pull/79) |
| Retrospectiva e este arquivo | Escreveu a retrospectiva a partir dos dados (ações da retro 01, períodos sem commit, métricas) | Validar as causas com o grupo e registrar o uso individual | [`retrospectiva-02.md`](retrospectiva-02.md) |

**Observações registradas durante o uso:**
* A IA encontrou problemas que o grupo não tinha visto: o teste `should render title` falhava desde o primeiro commit (02/09) e o `./mvnw` não funcionava porque `backend/.mvn/` estava no `.gitignore`.
* Ela **não conseguiu** validar o `docker compose` na máquina local (Docker sem WSL) nem o *build* de produção do Angular (falha de segmentação do Node no Windows). A validação ficou para o CI no GitHub, ou seja, a IA entregou código que ela mesma não tinha visto rodar localmente.
* A IA não tem permissão de admin, então a proteção de branch (`docs/protecao-de-branch.md`) e a evidência do PR bloqueado dependem do Renato.

## 2. Uso individual ao longo do semestre

| Integrante | Ferramenta(s) | Onde usou (sprint / tarefa / PR) | Ajudou em quê | Atrapalhou em quê |
| :--- | :--- | :--- | :--- | :--- |
| Allyson | Claude Code | Sprint 2: CI, Docker, testes, métricas, docs (PRs #66–#68, #79) | Fazer em horas a infraestrutura que eu não dominava; achar problemas antigos | Volume grande de mudanças para revisar; não validou localmente |
| Allane | *(preencher)* | | | |
| Renato | *(preencher)* | | | |

## 3. Avaliação do grupo

* **Onde a IA ajudou:** na auditoria do repositório contra a rubrica e no trabalho de infraestrutura (CI, Docker, testes), que o grupo não dominava. Também coletou as métricas pela API do GitHub com comandos reproduzíveis, e achou dois problemas antigos que ninguém tinha visto.
* **Onde atrapalhou:** gerou muita mudança de uma vez, o que dificulta revisar e repete o padrão de lote do fim da sprint. Entregou código que só foi validado no CI, não localmente. E facilita concentrar o trabalho em uma pessoa: quem não escreveu precisa estudar o código para conseguir explicá-lo.
* **O que faríamos diferente:** usar a IA desde o início da sprint, em PRs pequenos de um assunto cada. Quem revisa precisa entender cada linha antes de aprovar. E o uso deve ser registrado aqui no mesmo dia.
