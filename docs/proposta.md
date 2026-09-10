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