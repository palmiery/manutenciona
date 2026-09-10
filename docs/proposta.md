# 📋 Proposta do Projeto - Manutenciona

---

## 1. Visão do Produto

- **Para** gestores operacionais e equipes de manutenção predial e acadêmica
- **Que** enfrentam desorganização, lentidão e falta de rastreabilidade no atendimento de falhas em infraestruturas e equipamentos
- **O Manutenciona** é um sistema web integrado de gestão e acompanhamento de chamados de manutenção
- **Que** centraliza solicitações, prioriza atendimentos e oferece acompanhamento de status em tempo real
- **Diferente de** controles manuais fragmentados por planilhas, e-mails ou mensagens informais de aplicativo
- **Nosso produto** oferece um dashboard intuitivo categorizado por status, integrado ao cadastro de salas/equipamentos e com abertura simplificada de chamados.

### 📌 Contextualização e Evidência do Problema
Em instituições acadêmicas e prediais com múltiplos blocos, salas de aula e laboratórios, falhas em equipamentos essenciais (como ar-condicionado, projetores e fiação elétrica) são frequentemente notificadas de maneira informal e não estruturada. Isso provoca perda de histórico, duplicidade de solicitações, falta de transparência sobre quem está trabalhando em cada chamado e atrasos críticos no reparo. O **Manutenciona** resolve esse problema ao fornecer uma plataforma centralizada e de fácil uso onde o usuário final reporta a falha e a equipe técnica realiza a triagem, acompanhamento e resolução de forma clara e mensurável.

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

*O backlog detalhado e dinâmico encontra-se no [GitHub Projects do Projeto](https://github.com/orgs/palmiery/projects/10).*

| ID | História de Usuário (Resultado) | Estimativa | Prioridade |
| :--- | :--- | :--- | :--- |
| **US01** | Como gestor, quero visualizar o total de chamados em cada status (Pendente, Em Espera, Concluído) no dashboard para acompanhar a carga de trabalho. | 5 pts | Alta |
| **US02** | Como usuário, quero registrar uma solicitação de manutenção informando sala, equipamento e problema para que a equipe possa resolver. | 3 pts | Alta |
| **US03** | Como técnico, quero alterar o status e a prioridade de uma solicitação para organizar a fila de atendimento. | 3 pts | Alta |
| **US04** | Como administrador, quero cadastrar salas e equipamentos para manter a base de ativos atualizada. | 5 pts | Média |
| **US05** | Como técnico, quero filtrar os chamados por status e sala para otimizar minhas rotas de atendimento. | 2 pts | Média |

---

## 4. Stack Tecnológico e Justificativa

* **Frontend (Angular + Tailwind CSS):** Escolhido pela arquitetura baseada em componentes, tipagem forte com TypeScript e facilidade na construção de layouts responsivos com Tailwind CSS.
* **Backend (Spring Boot + Java 21):** Escolhido pelo alto desempenho, ecossistema maduro para criação de APIs REST robustas e integração simples com o banco de dados via Spring Data JPA.
* **Banco de Dados (PostgreSQL):** Banco relacional confiável para garantir a integridade referencial entre salas, equipamentos e chamados.

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

| Nome do Integrante | Matrícula | Papel no Scrum |
| :--- | :--- | :--- |
| **Allyson Carmo** | 20260001535 | Product Owner / Developer |
| **Allane Oliveira** | 20230046474 | Scrum Master / Developer |
| **Renato Campos** | 20250050779 | Developer |

---

## 7. Informações da Apresentação

* **Coorte:** 2026.2
* **Quadro Kanban:** [Link do GitHub Projects](https://github.com/users/palmiery/projects/10)
* **Integração:** Projeto desenvolvido para a disciplina de Processos de Software.