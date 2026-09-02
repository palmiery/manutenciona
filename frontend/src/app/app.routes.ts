import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./dashboard/dashboard.routes').then((m) => m.dashboardRoutes),
  },
  {
    path: 'configuracoes',
    loadChildren: () =>
      import('./configuracoes/configuracoes.routes').then((m) => m.configuracoesRoutes),
  },
  {
    path: 'nova-solicitacao',
    loadComponent: () =>
      import('./public/nova-solicitacao/nova-solicitacao.component').then(
        (m) => m.NovaSolicitacaoComponent
      ),
  },
  { path: '**', redirectTo: 'dashboard' },
];
