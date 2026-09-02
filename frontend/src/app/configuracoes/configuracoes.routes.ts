import { Routes } from '@angular/router';
import { ConfiguracoesLayoutComponent } from './configuracoes-layout/configuracoes-layout.component';

export const configuracoesRoutes: Routes = [
  {
    path: '',
    component: ConfiguracoesLayoutComponent,
    children: [
      { path: '', redirectTo: 'salas', pathMatch: 'full' },
      {
        path: 'salas',
        loadComponent: () =>
          import('./salas/salas.component').then((m) => m.SalasComponent),
      },
      {
        path: 'equipamentos',
        loadComponent: () =>
          import('./equipamentos/equipamentos.component').then((m) => m.EquipamentosComponent),
      },
    ],
  },
];
