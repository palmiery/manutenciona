# Mapeamento de Fluxo de Valor — Sprint 2 (final)

* **Projeto:** Manutenciona · **Repositório:** [`palmiery/manutenciona`](https://github.com/palmiery/manutenciona) · **Quadro:** [GitHub Projects #10](https://github.com/users/palmiery/projects/10)
* **Itens medidos:** as 5 histórias do MVP concluídas na Sprint 1 (US01–US05), da criação do cartão até chegar à `master`.
* **Unidade:** **horas corridas** (relógio de parede, incluindo noites e fins de semana), fuso UTC-3.
* **"No ar"** = merge na `master`, a mesma definição de implantação de [`metricas-02.md`](metricas-02.md).
* **Como reproduzir as filas principais:** `SINCE=2026-10-15 UNTIL=2026-10-15 bash scripts/fluxo.sh`. Os demais horários vêm dos comandos da seção 5.

---

## 1. Fluxo real (da ideia ao "no ar")

As etapas **em laranja não aparecem no quadro**: não existe coluna para elas, mas o item espera ali.

```mermaid
flowchart LR
    A["💡 Ideia / cartão criado<br/>(Backlog)"] -->|"espera<br/>6,8 dias"| B["Comprometido na sprint<br/>(milestone / Ready)"]
    B -->|"espera<br/>28,0 dias"| C["In progress<br/>desenvolvimento local"]
    C -->|"processamento<br/>37 min"| D["PR aberto<br/>(In review)"]
    D -->|"espera<br/>4 h 24 min"| E["Revisão + merge<br/>na develop"]
    E -->|"processamento<br/>9 min"| F["Espera pela release<br/>develop → master"]
    F -->|"espera<br/>1 h 06 min"| G["Revisão do PR de release<br/>(#57)"]
    G -->|"processamento<br/>25 min"| H["🚀 No ar<br/>(merge na master)"]
    H -.->|"build quebrado<br/>60 min"| I["Correção pós-implantação<br/>(#58 / #59)"]

    classDef hidden fill:#ffe0b2,stroke:#e65100,color:#000;
    classDef board fill:#e3f2fd,stroke:#1565c0,color:#000;
    class A,B,C,D board;
    class E,F,G,I hidden;
```

*Médias dos 5 itens. A etapa pontilhada só aconteceu nesta release; ela não entra no lead time, que termina no merge na `master`.*

---

## 2. Tempos medidos por item

### 2.1 Linha do tempo (UTC-3) e fonte de cada horário

| Evento | US01 (#6 / PR #50) | US02 (#7 / PR #51) | US03 (#8 / PR #55) | US04 (#9 / PR #56) | US05 (#10 / PR #54) | Fonte |
| :--- | :---: | :---: | :---: | :---: | :---: | :--- |
| Cartão criado / entra no quadro | 10/09 15:14 | 10/09 15:15 | 10/09 15:15 | 10/09 15:15 | 10/09 15:15 | Issue `createdAt` e evento `added_to_project_v2` |
| Comprometido na Sprint 1 (milestone) | 17/09 11:30 | 17/09 11:30 | 17/09 11:30 ¹ | 17/09 11:31 | 17/09 11:31 | Evento `milestoned` da issue |
| Início do trabalho ² | 15/10 11:12 | 15/10 11:20 | 15/10 11:22 | 15/10 11:16 | 15/10 11:42 | US01: mudança de status no quadro (= "In progress", confirmado na retro 01); demais: 1º commit do PR |
| PR aberto | 11:53 | 11:55 | 12:04 | 12:06 | 12:00 | PR `createdAt` |
| 1ª revisão (outro integrante) | 16:20 | 16:24 | 16:29 | 16:30 | 16:27 | PR `reviews[].submittedAt` |
| Merge na `develop` | 16:23 | 16:24 | 16:29 | 16:30 | 16:57 ³ | PR `mergedAt` |
| Release `#57`: aberta / 1ª revisão / merge | 17:02 / 17:39 / **18:04** | ← igual | ← igual | ← igual | ← igual | PR `#57` |
| Build restaurado (`#59`) | 19:04 | ← igual | ← igual | ← igual | ← igual | PR `#59` `mergedAt` |

¹ A US03 também foi movida para `Ready` em 27/09 12:28 (evento `project_v2_item_status_changed`, registrado como "Ready" na retro 01).
² O token usado não tem o escopo `read:project`, então a API não devolve o **nome** da coluna nos eventos do quadro, só o horário. Nas US02–US05, a mudança de status no quadro (12:16–12:17) aconteceu **depois** do 1º commit, por isso usamos o 1º commit como início.
³ A US05 levou 30 min entre a revisão e o merge porque teve conflito com as US01–US04 em `home.component.ts` e `SolicitacaoService.java` (commit de resolução `2709e3e`).

### 2.2 Processamento (P) e espera (E) por etapa

| Etapa | Tipo | US01 | US02 | US03 | US04 | US05 | **Média** | Método |
| :--- | :---: | ---: | ---: | ---: | ---: | ---: | ---: | :--- |
| 1. Backlog (criação → compromisso na sprint) | E | 6,84 d | 6,84 d | 6,84 d | 6,84 d | 6,84 d | **6,84 d** | Medido |
| 2. Fila da sprint (compromisso → início) | E | 27,99 d | 27,99 d | 27,99 d | 27,99 d | 28,01 d | **28,0 d** | Medido |
| 3. Desenvolvimento (início → PR aberto) | P | 40,5 min | 35,3 min | 41,6 min | 49,6 min | 17,3 min | **36,9 min** | Medido, **limite inferior** ⁴ |
| 4. Fila de revisão (PR aberto → início da revisão) | E | 4 h 25 | 4 h 26 | 4 h 23 | 4 h 24 | 4 h 25 | **4 h 24** | Medido (PR aberto → 1ª revisão) menos a duração estimada da revisão ⁵ |
| 5. Revisão + merge na `develop` | P | 4,9 min | 4,0 min | 2,3 min | 0,7 min | 32,7 min | **8,9 min** | Duração da revisão **estimada** ⁵ + medido (1ª revisão → merge) |
| 6. Espera pela release (merge develop → 1ª revisão do `#57`) | E | 1 h 16 | 1 h 14 | 1 h 10 | 1 h 09 | 42 min | **1 h 06** | Medido |
| 7. Revisão da release (1ª revisão do `#57` → merge na `master`) | P | 25 min | 25 min | 25 min | 25 min | 25 min | **25 min** | Medido (compartilhado pelos 5 itens) |

⁴ **Limite inferior:** o relógio começa no 1º commit ou na mudança de status no quadro, mas as histórias foram codificadas localmente antes. Os 5 primeiros commits foram feitos em 26 minutos (11:16–11:42), o que mostra que o código já estava pronto. Esse tempo local **não foi registrado** na Sprint 1. Ver a análise de sensibilidade na seção 3.
⁵ **Duração da revisão, estimada:** a mesma revisora (`@AllaneE`) enviou as 5 revisões em sequência (16:20:55, 16:24:43, 16:27:31, 16:29:39, 16:30:08). Usamos o intervalo entre uma revisão e a anterior como duração de cada uma: 3,8 min (US02), 2,8 min (US05), 2,1 min (US03) e 0,5 min (US04). Para a US01, a primeira da sequência, usamos a mediana dos outros intervalos: **2,5 min (estimado)**.

---

## 3. Lead time e eficiência de fluxo

**Lead time** = cartão criado → merge na `master`. **Processamento** = etapas 3 + 5 + 7. **Eficiência de fluxo** = processamento ÷ lead time.

| Item | Processamento | Lead time | **Eficiência de fluxo** | Lead time de entrega ⁶ | Eficiência na entrega ⁶ |
| :--- | ---: | ---: | ---: | ---: | ---: |
| US01 | 70,4 min | 842,8 h (35,1 d) | **0,14%** | 6,86 h | 17,1% |
| US02 | 64,3 min | 842,8 h (35,1 d) | **0,13%** | 6,74 h | 15,9% |
| US03 | 68,9 min | 842,8 h (35,1 d) | **0,14%** | 6,69 h | 17,2% |
| US04 | 75,2 min | 842,8 h (35,1 d) | **0,15%** | 6,80 h | 18,4% |
| US05 | 75,0 min | 842,8 h (35,1 d) | **0,15%** | 6,36 h | 19,7% |
| **Média** | **70,8 min** | **842,8 h (35,1 d)** | **0,14%** | **6,69 h** | **17,7%** |

⁶ Do início do trabalho até a `master`, sem as filas 1 e 2. Mostra a eficiência da parte que o time controla no dia a dia.

Lead time até o **build funcionando** (merge do `#59`, 19:04): 35,2 dias para os 5 itens.

**Análise de sensibilidade:** o desenvolvimento local não foi medido. Mesmo supondo **8 horas** de desenvolvimento por história, em vez dos 37 min medidos, a eficiência média subiria apenas para **1,0%**. A conclusão não muda: o item passa mais de 98% do tempo esperando.

---

## 4. Gargalo

**Onde o tempo foi gasto (média dos 5 itens, % do lead time de 842,8 h):**

| Etapa | % do lead time |
| :--- | ---: |
| 1. Backlog | 19,5% |
| **2. Fila da sprint (comprometido, sem começar)** | **79,7%** |
| 3. Desenvolvimento | 0,07% |
| 4. Fila de revisão | 0,52% |
| 5. Revisão + merge | 0,02% |
| 6. Espera pela release | 0,13% |
| 7. Revisão da release | 0,05% |

**Gargalo principal: a espera antes do início.** Em média, os itens ficaram **34,8 dias (99,2% do lead time)** no quadro antes de alguém começar. Foram **28,0 dias** (79,7%) já comprometidos na Sprint 1 e parados até 15/10, quando todas as 5 histórias foram puxadas **no mesmo dia**, em 26 minutos. A retro 01 identificou a causa: os campos *Start/Target date* estavam vazios e não havia puxada sequencial.

**Gargalo secundário, dentro da entrega: a fila de revisão.** Foram **4 h 24 min**, ou **65,9% do lead time de entrega** de 6,69 h. Os 5 PRs foram abertos em 13 minutos (11:53–12:06) e revisados em sequência pela mesma pessoa 4 horas depois. Esse lote ainda causou o conflito da US05 (30 min a mais) e a falha de build da release `#57` (ver `metricas-02.md`).

---

## 5. Melhoria proposta e métrica-alvo

> **Proposta para o grupo confirmar** (responsável e prazo sugeridos a partir das ações da retro 01, issue [#63](https://github.com/palmiery/manutenciona/issues/63)).

| Campo | Proposta |
| :--- | :--- |
| **Mudança** | **Puxada contínua de um item por pessoa.** (1) Todo cartão da Sprint 2 recebe responsável e *Start/Target date* ao ser criado. (2) Cada integrante tem no máximo **1 cartão em `In progress`**. (3) O PR é aberto no mesmo dia em que o trabalho começa (rascunho, se preciso), para que o trabalho apareça no GitHub desde o início. (4) Revisão em até 2 h depois de o PR ser marcado como pronto. |
| **Responsável** | `@AllysonGustavo1`, dono da ação #63 da retro 01 *(a confirmar)* |
| **Prazo** | 25/11 a 30/11/2026, com medição final em 30/11 |
| **Métrica-alvo (a mesma do gargalo)** | **Espera antes do início**: criação do cartão → 1º commit do PR que o fecha. **Antes: 34,8 dias** (média US01–US05). **Meta: ≤ 2 dias** em média para os cartões da Sprint 2. |
| **Métrica de acompanhamento** | Espera por revisão (PR aberto → 1ª revisão de outro integrante). **Antes: 4,4 h.** **Meta: ≤ 2 h.** |

**Como registrar o tempo de processamento na Sprint 2:** quem trabalha no cartão comenta no PR, ao fim de cada dia, `⏱ processamento: X h`. Assim a próxima medição deixa de depender de limite inferior.

### Resultado (antes × depois)

Comandos:

```bash
# Antes (US01–US05, Sprint 1)
SINCE=2026-10-15 UNTIL=2026-10-15 bash scripts/fluxo.sh
# Depois (Sprint 2): rodar em 30/11
SINCE=2026-11-24 bash scripts/fluxo.sh
```

| Métrica | Antes (Sprint 1) | Meta | Depois (Sprint 2, medido em 30/11) |
| :--- | ---: | ---: | :--- |
| Espera antes do início (média) | 34,8 dias | ≤ 2 dias | ⏳ *pendente: rodar o comando em 30/11* |
| Espera por revisão (média) | 4,4 h | ≤ 2 h | ⏳ *pendente* |
| PRs abertos simultaneamente (máximo no mesmo dia) | 5 | ≤ 1 por integrante | ⏳ *pendente* |

Saída do comando "antes", coletada em 24/11/2026:

```text
PR   cartão  espera antes do início (dias)  espera por revisão (h)
#56  #9      34.83                          4.40
#55  #8      34.84                          4.40
#54  #10     34.85                          4.50
#51  #7      34.84                          4.50
#50  #6      34.85                          4.50
```

*(O script mede a espera até o 1º commit. Por isso a US01 aparece com 34,85 d, e não com os 34,8 d calculados a partir da mudança de status no quadro: o status não pode ser lido sem o escopo `read:project`.)*

**Limitações da comparação:** a janela "depois" tem só 6 dias, e os cartões da Sprint 2 foram criados perto do início do trabalho, o que favorece a meta. O resultado deve ser registrado **mesmo que a meta não seja atingida**, explicando o motivo.

---

## 6. Limitações gerais

* **Nome das colunas do quadro:** a API só devolve o nome do status com o escopo `read:project`, que o token usado não tinha. Usamos os horários dos eventos, mais as colunas já registradas na [retrospectiva 01](retrospectiva-01.md). Para conferir: `gh auth refresh -s read:project`, depois `gh project item-list 10 --owner palmiery`.
* **Tempo de desenvolvimento local** não registrado na Sprint 1 (ver nota ⁴ e a análise de sensibilidade).
* **Duração da revisão** estimada pelo intervalo entre revisões consecutivas (nota ⁵).
* **Não havia CI**, então não há etapa de build/teste automatizado no fluxo da Sprint 1. Na Sprint 2, o CI entra entre "PR aberto" e "Revisão".
* Os 5 itens compartilham as mesmas datas de criação, compromisso e release. Isso reflete o processo em lote, mas também deixa pouca variação entre eles.
