# Proposta do Projeto - Manutenciona

---

## 1. Visão do Produto

- **Para** gestores operacionais e equipes de manutenção predial e acadêmica.
- **Que** enfrentam desorganização, lentidão e falta de rastreabilidade no atendimento de falhas em infraestruturas e equipamentos.
- **O Manutenciona** é um sistema web integrado de gestão e acompanhamento de chamados de manutenção.
- **Que** centraliza solicitações, prioriza atendimentos e oferece acompanhamento de status em tempo real.
- **Diferente de** controles manuais fragmentados por planilhas, e-mails ou mensagens informais de aplicativo.
- **Nosso produto** oferece um dashboard intuitivo categorizado por status, integrado ao cadastro de salas/equipamentos e com abertura simplificada de chamados.

### Contextualização e Evidência do Problema
Em instituições acadêmicas e prediais com múltiplos blocos, salas de aula e laboratórios, falhas em equipamentos essenciais (como ar-condicionado, projetores e fiação elétrica) são frequentemente notificadas de maneira informal e não estruturada. Isso provoca perda de histórico, duplicidade de solicitações, falta de transparência sobre quem está trabalhando em cada chamado e atrasos críticos no reparo. O **Manutenciona** resolve esse problema ao fornecer uma plataforma centralizada e de fácil uso onde o usuário final reporta a falha e a equipe técnica realiza a triagem, acompanhamento e resolução de forma clara e mensurável.

---

## 2. Definição do MVP

### Hipótese de Valor
> **Acreditamos que** gestores operacionais, técnicos e usuários solicitantes **vão** centralizar 100% dos chamados e acompanhamentos de manutenção pelo sistema web **porque** terão visibilidade imediata do status de cada solicitação, redução drástica no tempo de triagem e histórico rastreável de todas as manutenções realizadas.

### Dentro do Escopo (MVP)
* **Painel Geral (Dashboard):** Visão consolidada de chamados divididos por status (`PENDENTE`, `EM_ESPERA`, `CONCLUIDO`).
* **Gestão de Infraestrutura:** Cadastro, listagem e remoção de Salas e Equipamentos associados.
* **Abertura de Chamados:** Formulário para registro de novas solicitações com seleção de sala, equipamento vinculado, descrição do problema e nível de prioridade inicial.
* **Notas de comunicação:** Comunicação entre o solicitante e responsável sobre alguma pendência do chamado, ou seja, algum informativo do chamado.
* **Gestão do Ciclo de Vida do Chamado:** Alteração de status e prioridade das solicitações pela equipe responsável pela manutenção.
* **Filtros Básicos:** Filtragem de solicitações por status e sala para otimizar a fila de trabalho.

### Fora do Escopo (Pós-MVP)
* Notificações automáticas via e-mail, push ou SMS.
* Módulo financeiro, controle de estoque de peças e custos de manutenção.
* Autenticação avançada e controle de acesso por múltiplos perfis hierárquicos (RBAC complexo).
* Aplicativo móvel nativo (iOS / Android).
* Relatórios analíticos avançados e exportação de dados para PDF/Excel.

### Critérios de Sucesso do MVP
1. O usuário consegue registrar um chamado em menos de 1 minuto sem auxílio técnico.
2. O técnico consegue atualizar o status de um chamado e a alteração reflete imediatamente no Dashboard.
3. Todas as 5 histórias de usuário do backlog inicial implementadas, testadas e validadas ao longo de 4 sprints.

---

## 3. Backlog Inicial e Histórias de Usuário

*O backlog detalhado, estimado e dinâmico encontra-se no [GitHub Projects do Manutenciona](https://github.com/users/palmiery/projects/10).*

| ID | História de Usuário (Resultado de Valor) | Estimativa | Prioridade |
| :--- | :--- | :---: | :---: |
| **US01** | Como gestor, quero visualizar o total de chamados em cada status (Pendente, Em Espera, Concluído) no dashboard para acompanhar a carga de trabalho. | 5 pts | **P1** |
| **US02** | Como usuário, quero registrar uma solicitação de manutenção informando sala, equipamento e problema para que a equipe possa resolver. | 3 pts | **P1** |
| **US03** | Como técnico, quero alterar o status e a prioridade de uma solicitação para organizar a fila de atendimento. | 3 pts | **P1** |
| **US04** | Como administrador, quero cadastrar salas e equipamentos para manter a base de ativos atualizada. | 5 pts | **P2** |
| **US05** | Como técnico, quero filtrar os chamados por status e sala para otimizar minhas rotas de atendimento. | 2 pts | **P2** |

---

## 4. Stack Tecnológico e Justificativa

* **Frontend (Angular 22 + Tailwind CSS):** Escolhido pela arquitetura robusta baseada em componentes, tipagem estática e segura com TypeScript, ecossistema completo de formulários reativos e rapidez no desenvolvimento de interfaces limpas e responsivas via Tailwind CSS.
* **Backend (Spring Boot 4 + Java 21):** Escolhido pelo alto desempenho, estabilidade empresarial, facilidade na modelagem de APIs RESTful e integração com o banco de dados via Spring Data JPA com Hibernate.
* **Banco de Dados (PostgreSQL):** Banco de dados relacional confiável, ideal para assegurar a integridade referencial entre as entidades (Salas, Equipamentos e Solicitações).
* **Infraestrutura Local (Docker / Docker Compose):** Utilizado para padronização do ambiente de banco de dados PostgreSQL entre todos os membros da equipe.

---

## 5. Acordo de Processo

* **Cadência de Sprints:** O projeto será executado em **4 Sprints de 1 semana cada**, iniciando às segundas-feiras e finalizando aos domingos.
* **Cerimônias do Scrum:**
  * **Sprint Planning:** Segundas-feiras, às 19h00 (Duração: 45 min). Planejamento das metas, seleção de histórias do Backlog e alocação.
  * **Daily Sync:** Terça a Sexta-feira, às 12h00 (Duração: 10 min, assíncrono via grupo da equipe). Cada integrante reporta: o que fez, o que fará e se há bloqueios.
  * **Sprint Review & Retrospectiva:** Domingos, às 20h00 (Duração: 45 min). Demonstração das funcionalidades concluídas, validação da DoD e levantamento de melhorias no processo de trabalho.
* **Definição de Pronto (Definition of Done - DoD):**
  1. Código versionado em branch descritiva (`feat/...`, `fix/...`, `chore/...`) e mesclado via Pull Request direcionado à branch `develop`.
  2. Pull Request revisado e aprovado por pelo menos 1 outro desenvolvedor conforme a política de revisão cruzada.
  3. Funcionalidade testada manualmente no ambiente local (integração Frontend + Backend + Banco).
  4. Código limpo, sem logs de depuração desnecessários ou erros no console do navegador e backend.
  5. Card correspondente atualizado e movido para a coluna **Pronto** no GitHub Projects.
* **Política de Revisão Cruzada de Código (Code Review):**
  * **Allyson Carmo** revisa os Pull Requests de **Renato Campos**.
  * **Renato Campos** revisa os Pull Requests de **Allane Oliveira**.
  * **Allane Oliveira** revisa os Pull Requests de **Allyson Carmo**.
  *(Em caso de ausência de um membro por mais de 24h, qualquer outro desenvolvedor assume a revisão para não bloquear o fluxo).*
* **Limites de Trabalho em Progresso (WIP Limits):**
  * *Sprint Backlog:* Contém os itens selecionados para a sprint corrente.
  * *Em Progresso:* Máximo de **2 tarefas por desenvolvedor** (limite global: 6 tarefas).
  * *Em Revisão (Code Review):* Máximo de **3 tarefas simultâneas** para toda a equipe.
* **Ferramentas Adotadas:**
  * **Gestão e Versionamento:** GitHub (Repositório, GitHub Projects com Kanban, Issues e Pull Requests).
  * **Comunicação:** Discord (reuniões síncronas) e WhatsApp (Daily assíncrona e avisos operacionais).
  * **Ambiente de Desenvolvimento:** Visual Studio Code / IntelliJ IDEA, Git e Docker.

---

## 6. Equipe

| Nome do Integrante | Matrícula | Usuário GitHub | Papel no Scrum |
| :--- | :---: | :---: | :--- |
| **Allyson Carmo** | 20260001535 | [@AllysonGustavo1](https://github.com/AllysonGustavo1)| Product Owner / Developer |
| **Allane Oliveira** | 20230046474 | [@AllaneE](https://github.com/AllaneE) | Scrum Master / Developer |
| **Renato Campos** | 20250050779 | [@palmiery](https://github.com/palmiery) | Tech Lead / Developer |

---

## 7. Informações da Apresentação

* **Disciplina:** DIM0510 - Processos de Software
* **Integração com outras disciplinas:** Não há integração; trata-se de projeto exclusivo para DIM0510.
* **Quadro Kanban (GitHub Projects):** [https://github.com/users/palmiery/projects/10](https://github.com/users/palmiery/projects/10)
* **Vídeo de Apresentação (Sprint 0 - ~5 min):** *(A ser gravado pela equipe)*
