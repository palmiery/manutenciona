# Uso de IA no projeto

> Registro de onde a equipe usou ferramentas de IA, para a avaliação na [Retrospectiva Final](retrospectiva-02.md).
> **RASCUNHO:** a Seção 1 registra o uso de IA na preparação da Sprint 2 (24/11/2026). Cada integrante completa a Seção 2 com o próprio uso ao longo do semestre, e o grupo preenche a Seção 3.

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
| Métricas DORA e mapa de fluxo | Coletou os dados dos PRs/issues com `gh`, calculou as métricas e escreveu `metricas-02.md`, `fluxo-de-valor.md` e `scripts/dora.sh` | Conferir as definições, rodar os comandos de novo e validar a interpretação; decidir a melhoria e o responsável | PR de docs da Sprint 2 |
| Rascunhos de retrospectiva e deste arquivo | Preencheu os fatos verificáveis (ações da retro 01, períodos sem commit) e deixou marcados os trechos do grupo | Fazer a reunião e escrever causas, avaliação e ações | Trechos **[GRUPO]** |

**Observações registradas durante o uso:**
* A IA encontrou problemas que o grupo não tinha visto: o teste `should render title` falhava desde o primeiro commit (02/09) e o `./mvnw` não funcionava porque `backend/.mvn/` estava no `.gitignore`.
* Ela **não conseguiu** validar o `docker compose` na máquina local (Docker sem WSL) nem o *build* de produção do Angular (falha de segmentação do Node no Windows). A validação ficou para o CI no GitHub, ou seja, a IA entregou código que ela mesma não tinha visto rodar localmente.
* A IA não tem permissão de admin, então a proteção de branch (`docs/protecao-de-branch.md`) e a evidência do PR bloqueado dependem do Renato.

## 2. Uso individual ao longo do semestre

| Integrante | Ferramenta(s) | Onde usou (sprint / tarefa / PR) | Ajudou em quê | Atrapalhou em quê |
| :--- | :--- | :--- | :--- | :--- |
| Allyson | [GRUPO] | | | |
| Allane | [GRUPO] | | | |
| Renato | [GRUPO] | | | |

## 3. Avaliação do grupo

* **Onde a IA ajudou:** [GRUPO]
* **Onde atrapalhou:** [GRUPO — ex.: volume grande de mudanças de uma vez, que dificulta a revisão; risco de aprovar código sem entender; dependência no fim da sprint]
* **O que faríamos diferente:** [GRUPO]
