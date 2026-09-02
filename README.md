# 🔧 Manutenciona (Monorepo)

> Sistema centralizado para acompanhamento, priorização e gestão de solicitações de manutenção em salas e equipamentos.

---

## 🎯 Visão do Produto

- **Para** gestores operacionais e equipes de manutenção predial/acadêmica
- **Que** enfrentam desorganização, lentidão e falta de rastreabilidade no atendimento de falhas em infraestruturas e equipamentos
- **O Manutenciona** é um sistema web de gestão e acompanhamento de chamados de manutenção
- **Que** centraliza solicitações, prioriza atendimentos e oferece acompanhamento de status em tempo real
- **Diferente de** controles manuais por planilhas, e-mails ou mensagens informais
- **Nosso produto** oferece um dashboard intuitivo categorizado por status, integrado ao cadastro de salas/equipamentos e abertura simplificada de chamados.

---

## 👥 Equipe & Processo
- **Coorte:** 2026.1
- **Disciplina:** Processos de Software
- **Quadro de Tarefas (Kanban):** [Acessar GitHub Projects](https://github.com/orgs/seu-usuario/projects/1)
- **Documentação de Proposta:** [Acessar docs/proposta.md](docs/proposta.md)

---

## 📁 Estrutura do Projeto

```text
manutenciona/
├── docs/               # Documentação do projeto (Proposta, Visão, Processos)
│   └── proposta.md
├── frontend/           # Aplicação Angular 22 + Tailwind CSS
│   ├── src/
│   ├── package.json
│   └── angular.json
├── backend/            # API REST Spring Boot 4 + PostgreSQL
│   ├── src/
│   ├── pom.xml
│   └── mvnw
├── .gitignore          # Gitignore unificado
└── README.md

```
---


## 🚀 Como Executar

### 1. Pré-requisitos
- **Node.js** (v20+) e **npm**
- **Java JDK** (v21+)
- **PostgreSQL** rodando na porta 5432 com banco criado:
  ```sql
  CREATE DATABASE manutenciona;
  ```

---

### 2. Backend (Spring Boot)
Entre na pasta `backend/` e execute:
```bash
cd backend
./mvnw spring-boot:run
```
> A API estará disponível em: `http://localhost:8080`

---

### 3. Frontend (Angular)
Em outro terminal, entre na pasta `frontend/` e execute:
```bash
cd frontend
npm install
npm start
```
> O sistema estará acessível em: `http://localhost:4200`
