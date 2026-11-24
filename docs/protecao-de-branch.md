# Proteção de branch e gate do CI

> Critério da Sprint 2: *"A branch principal exige PR e o job do CI como status check obrigatório, inclusive para administradores. Um PR que quebra um teste deve ficar bloqueado."*
> Também cumpre a **Ação 1 da Retrospectiva 01** ([#61](https://github.com/palmiery/manutenciona/issues/61)): estender a proteção para a `develop` e acabar com o *bypass* sem revisão.

## 1. Regras (configuradas pelo admin do repositório — `@palmiery`)

Só o dono/admin consegue alterar as regras. Elas valem para **`master` e `develop`**:

| Regra | Valor |
| :--- | :--- |
| Exigir pull request antes do merge | sim, **1 aprovação** de outro integrante |
| Descartar aprovações quando houver novos commits | sim |
| Status checks obrigatórios | `backend (lint, build, testes)`, `frontend (lint, build, testes)`, `docker compose (sobe do zero)` |
| Exigir branch atualizada antes do merge | sim |
| **Aplicar também a administradores** (`enforce_admins`) | **sim** — sem *bypass* |
| Permitir *force push* / exclusão | não |

### Pela interface
*Settings → Branches → Add classic branch protection rule* (`master`, depois `develop`): marcar **Require a pull request before merging** (1 approval, dismiss stale approvals), **Require status checks to pass** (buscar os 3 jobs acima — eles só aparecem na busca depois de rodarem pelo menos uma vez), **Require branches to be up to date** e **Do not allow bypassing the above settings**.

### Pelo terminal (equivalente, para o admin)
```bash
for BRANCH in master develop; do
gh api -X PUT repos/palmiery/manutenciona/branches/$BRANCH/protection --input - <<'JSON'
{
  "required_status_checks": {
    "strict": true,
    "contexts": [
      "backend (lint, build, testes)",
      "frontend (lint, build, testes)",
      "docker compose (sobe do zero)"
    ]
  },
  "enforce_admins": true,
  "required_pull_request_reviews": {
    "required_approving_review_count": 1,
    "dismiss_stale_reviews": true
  },
  "restrictions": null,
  "allow_force_pushes": false,
  "allow_deletions": false
}
JSON
done
```

## 2. Verificação

```bash
gh api repos/palmiery/manutenciona/branches/master/protection \
  --jq '{checks: .required_status_checks.contexts, admins: .enforce_admins.enabled, reviews: .required_pull_request_reviews.required_approving_review_count}'
```

Saída esperada:
```json
{"admins":true,"checks":["backend (lint, build, testes)","frontend (lint, build, testes)","docker compose (sobe do zero)"],"reviews":1}
```

> Resultado obtido em __/11/2026: _colar aqui a saída real_

## 3. Evidência: PR que quebra um teste fica bloqueado

1. Criar a branch `test/gate-ci` a partir da `develop`, alterar um teste para falhar de propósito (ex.: em `SolicitacaoServiceTest.dashboardSomaOsContadoresNoTotal`, esperar `11L` em vez de `10L`) e abrir PR para a `develop`.
2. O job `backend (lint, build, testes)` fica vermelho e o botão de merge fica desabilitado com *"Required statuses must pass before merging"* — **inclusive para o admin**.
3. Tirar print da tela do PR e fechar o PR sem merge.

| Evidência | Link |
| :--- | :--- |
| PR bloqueado pelo CI | _#__ (link do PR)_ |
| Print | _`docs/img/pr-bloqueado.png`_ |
| CI verde na `master` no prazo | [Actions → CI → branch master](https://github.com/palmiery/manutenciona/actions/workflows/ci.yml?query=branch%3Amaster) |
