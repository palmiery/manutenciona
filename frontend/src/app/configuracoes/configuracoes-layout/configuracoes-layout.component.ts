import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarComponent } from '../../shared/components/sidebar/sidebar.component';

@Component({
  selector: 'app-configuracoes-layout',
  standalone: true,
  imports: [RouterOutlet, SidebarComponent],
  templateUrl: './configuracoes-layout.component.html',
})
export class ConfiguracoesLayoutComponent {}
