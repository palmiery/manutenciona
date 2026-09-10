# 📋 Proposta do Projeto - Manutenciona

---

## 1. Visão do Produto

- **Para** gestores operacionais e equipes de manutenção predial/acadêmica
- **Que** enfrentam desorganização, lentidão e falta de rastreabilidade no atendimento de falhas em infraestruturas e equipamentos
- **O Manutenciona** é um sistema web de gestão e acompanhamento de chamados de manutenção
- **Que** centraliza solicitações, prioriza atendimentos e oferece acompanhamento de status em tempo real
- **Diferente de** controles manuais por planilhas, e-mails ou mensagens informais
- **Nosso produto** oferece um dashboard intuitivo categorizado por status, integrado ao cadastro de salas/equipamentos e abertura simplificada de chamados.

---

## 2. Definição do MVP

### ✅ Dentro do Escopo
* **Painel Geral (Dashboard):** Visão consolidada de chamados divididos por status (`PENDENTE`, `EM_ESPERA`, `CONCLUIDO`).
* **Gestão de Infraestrutura:** Cadastro, listagem e remoção de Salas e Equipamentos associados.
* **Abertura de Chamados:** Formulario para registro de novas solicitações com escolha de sala, equipamento, descrição e nível de prioridade.
* **Gestão do Ciclo de Vida do Chamado:** Alteração de status e prioridade das solicitações pela equipe responsável.

### ❌ Fora do Escopo
* Notificações automáticas via e-mail ou SMS.
* Módulo financeiro ou controle de custos de manutenção.
* Autenticação avançada e controle de acesso por múltiplos perfis (RBAC complexo).
* Aplicativo móvel nativo (iOS/Android).

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

* **Cadência:** Projeto executado em **4 Sprints** de 1 semana cada.
* **Cerimônias:**
  * *Sprint Planning:* Início de cada sprint para puxar histórias do Backlog.
  * *Daily Sync:* Acompanhamento diário assíncrono via grupo da equipe.
  * *Sprint Review & Retrospective:* Validação dos entregáveis e ajuste contínuo do processo ao final de cada sprint.
* **Definição de Pronto (Definition of Done - DoD):**
  1. Código versionado em branch própria e mesclado via Pull Request com revisão de pelo menos 1 membro.
  2. Funcionalidade testada manualmente no ambiente local (front + back).
  3. Sem erros no console do navegador ou exceções não tratadas no backend.
  4. Tarefa atualizada no quadro do GitHub Projects.
* **WIP Limits (Work in Progress):**
  * *In Progress:* Máximo de 2 tarefas por desenvolvedor.
  * *Code Review:* Máximo de 3 tarefas no total da equipe.

---

## 6. Equipe

| Nome do Integrante | Matrícula | Usuário GitHub | Papel no Scrum |
| :--- | :---: | :---: | :--- |
| **Allyson Carmo** | 20260001535 | [@palmiery](https://github.com/palmiery) | Product Owner / Developer |
| **Allane Oliveira** | 20230046474 | [@allane-oliveira](https://github.com/allane-oliveira) | Scrum Master / Developer |
| **Renato Campos** | 20250050779 | [@renatocampos](https://github.com/renatocampos) | Tech Lead / Developer |

---

## 7. Informações da Apresentação

* **Coorte de Apresentação:** 2026.2
* **Disciplina:** DIM0510 - Processos de Software
* **Integração com outras disciplinas:** Não há integração; trata-se de projeto exclusivo para DIM0510.
* **Quadro Kanban (GitHub Projects):** [https://github.com/users/palmiery/projects/10](https://github.com/users/palmiery/projects/10)
* **Vídeo de Apresentação (Sprint 0 - ~5 min):** *(A ser gravado pela equipe)*