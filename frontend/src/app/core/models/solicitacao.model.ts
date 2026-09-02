export type StatusSolicitacao = 'PENDENTE' | 'EM_ESPERA' | 'CONCLUIDO';
export type PrioridadeSolicitacao = 'BAIXA' | 'MEDIA' | 'ALTA' | 'URGENTE';

export interface Solicitacao {
  id: number;
  titulo: string;
  descricao: string;
  solicitanteNome: string;
  solicitanteContato: string;
  status: StatusSolicitacao;
  prioridade: PrioridadeSolicitacao;
  salaId?: number;
  salaNome?: string;
  equipamentoId?: number;
  equipamentoNome?: string;
  createdAt: string;
  updatedAt: string;
  resolvedAt?: string;
}

export interface SolicitacaoCreate {
  titulo: string;
  descricao: string;
  solicitanteNome: string;
  solicitanteContato: string;
  prioridade: PrioridadeSolicitacao;
  salaId?: number;
  equipamentoId?: number;
}

export interface DashboardStats {
  pendentes: number;
  emEspera: number;
  concluidos: number;
  total: number;
}
