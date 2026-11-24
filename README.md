<div align="center">

<!-- Badges centralizadas e limpas -->
<p>
  <img src="https://img.shields.io/badge/Angular-22-red?style=flat-square&logo=angular&logoColor=white" alt="Angular" height="28">
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white" alt="Spring Boot" height="28">
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white" alt="PostgreSQL" height="28">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java" height="28">
</p>

# Projeto Manutenciona

[![CI](https://github.com/palmiery/manutenciona/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/palmiery/manutenciona/actions/workflows/ci.yml)
[![Licença: MIT](https://img.shields.io/badge/licen%C3%A7a-MIT-blue.svg)](LICENSE)

> Sistema centralizado para acompanhamento, priorização e gestão de solicitações de manutenção em salas e equipamentos.

</div>

---

## Visão do Produto

- **Para** gestores operacionais e equipes de manutenção predial/acadêmica
- **Que** enfrentam desorganização, lentidão e falta de rastreabilidade no atendimento de falhas em infraestruturas e equipamentos
- **O Manutenciona** é um sistema web de gestão e acompanhamento de chamados de manutenção
- **Que** centraliza solicitações, prioriza atendimentos e oferece acompanhamento de status em tempo real
- **Diferente de** controles manuais por planilhas, e-mails ou mensagens informais
- **Nosso produto** oferece um dashboard intuitivo categorizado por status, integrado ao cadastro de salas/equipamentos e abertura simplificada de chamados.

---

## Equipe & Processo

* **Disciplina:** DIM0510 — Processos de Software
* **Coorte:** 2026.2
* **Integração:** Projeto independente (sem integração com Web II ou Sistemas Móveis)
* **Quadro de Tarefas (Kanban):** [GitHub Projects #10](https://github.com/users/palmiery/projects/10)
* **Documentação Completa da Proposta:** [docs/proposta.md](docs/proposta.md)
* **Vídeos das sprints:** [Sprint 0](https://drive.google.com/file/d/1xyWuTEPhsBFx8A9Q8vdMAbQfeu0ffisO/view?usp=sharing) · [Sprint 1](https://drive.google.com/file/d/1PpU1KvrZdvxzb_to7XDu38sT94b7hbza/view?usp=drive_link) · **Sprint 2 (final): _LINK DO VÍDEO AQUI_**

### Sprint 2 (entrega final) — onde está cada coisa

| Critério | Evidência |
| :--- | :--- |
| Pipeline de CI | [`.github/workflows/ci.yml`](.github/workflows/ci.yml) — lint, build e testes em todo push e PR · [execuções](https://github.com/palmiery/manutenciona/actions) |
| Proteção da `master` | [`docs/protecao-de-branch.md`](docs/protecao-de-branch.md) — regras e o PR bloqueado por teste quebrado |
| Containerização | [`docker-compose.yml`](docker-compose.yml) + `backend/Dockerfile` + `frontend/Dockerfile` |
| Métricas DORA | [`docs/metricas-02.md`](docs/metricas-02.md) |
| Mapeamento de Fluxo de Valor | [`docs/fluxo-de-valor.md`](docs/fluxo-de-valor.md) |
| Retrospectiva final | [`docs/retrospectiva-02.md`](docs/retrospectiva-02.md) · uso de IA em [`docs/uso-de-ia.md`](docs/uso-de-ia.md) |
| Licença | [MIT](LICENSE) |

### Integrantes da Equipe

| Nome do Integrante | Matrícula | Usuário GitHub | Papel no Scrum |
| :--- | :---: | :---: | :--- |
| **Allyson Carmo** | 20260001535 | [@AllysonGustavo1](https://github.com/AllysonGustavo1) | Product Owner / Developer |
| **Allane Emanoelle** | 20230046474 | [@AllaneE](https://github.com/AllaneE) | Scrum Master / Developer |
| **Renato Campos** | 20250050779 | [@palmiery](https://github.com/palmiery) | Tech Lead / Developer |

---

## Estrutura do Projeto

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
├── .github/workflows/  # CI (lint, build, testes, docker compose)
├── docker-compose.yml  # Sobe banco, API e frontend do zero
├── LICENSE             # MIT
├── .gitignore          # Gitignore unificado
└── README.md

```
---


## Como Executar

### Opção recomendada: Docker (≈ 5 minutos)

**Pré-requisito:** apenas [Docker](https://docs.docker.com/get-docker/) com o Docker Compose (Docker Desktop no Windows/macOS). Não precisa instalar Java, Node nem PostgreSQL.

```bash
git clone https://github.com/palmiery/manutenciona.git
cd manutenciona
docker compose up --build
```

O primeiro build baixa as dependências e leva alguns minutos. Quando os três serviços ficarem `healthy`:

| Serviço | Endereço |
| :--- | :--- |
| Sistema (frontend) | http://localhost:4200 |
| API (backend) | http://localhost:8080/api/salas |
| Saúde da API | http://localhost:8080/actuator/health |
| PostgreSQL | `localhost:5432` — usuário `postgres`, senha `postgres`, base `manutenciona` |

**O que esperar:** o sistema abre no **Dashboard** com os contadores zerados. Para ver o fluxo completo do MVP:

1. Em **Configurações → Salas**, cadastre uma sala; em **Configurações → Equipamentos**, cadastre um equipamento nessa sala.
2. Em **Nova Solicitação**, abra um chamado (descrição com no mínimo 10 caracteres).
3. Volte ao **Dashboard**: o chamado aparece como *Pendente*; altere status e prioridade direto na lista e filtre por status e sala.

Para parar: `Ctrl+C` e `docker compose down` (acrescente `-v` para apagar também os dados do banco).

> As portas 4200, 8080 e 5432 precisam estar livres. Se você já tem um PostgreSQL local na 5432, pare-o antes.

### Rodando os testes e o lint (como no CI)

```bash
# backend (JDK 21 + Maven; os testes de integração precisam do Postgres: docker compose up -d postgres)
cd backend && mvn checkstyle:check && mvn verify

# frontend (Node 22)
cd frontend && npm ci && npm run lint && npm run test:ci && npm run build
```

---

## Desenvolvimento local sem Docker


### 1. Pré-requisitos
- **Node.js** (v20+) e **npm**
- **Java JDK** (v21+) e **Maven**
- **PostgreSQL** rodando na porta 5432 com banco criado:

#### 1.1. Configuração do Banco de Dados PostgreSQL (Recomendação inicial)

A aplicação está configurada no arquivo `application.properties` com os seguintes dados:

- **Usuário**: postgres 

- **Senha**: postgres 

- **Banco de Dados**: manutenciona 

**1.2. Instale e inicie o serviço do PostgreSQL Nativo**: 

```bash
sudo apt install postgresql postgresql-contrib
```

```bash
sudo systemctl start postgresql
```

**1.3. Acesse o console do PostgreSQL**: 

```bash
sudo -u postgres psql
```


**1.4. Defina a senha do usuário postgres e crie a base de dados do projeto**: 

```sql
ALTER USER postgres WITH PASSWORD 'postgres';
 ```

  ```sql
  CREATE DATABASE manutenciona;
  ```

**1.5 Outra opção é usar PostgreSQL via Docker Compose**

```bash
docker compose up -d 
```

---

### 2. Backend (Spring Boot)

Entre na pasta `backend/` e execute:

```bash
cd backend
./mvnw spring-boot:run
```
ou

```bash
mvn spring-boot:run
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
