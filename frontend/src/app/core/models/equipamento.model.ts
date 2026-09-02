export interface Equipamento {
  id: number;
  nome: string;
  descricao?: string;
  numeroSerie?: string;
  salaId?: number;
  salaNome?: string;
  createdAt: string;
}

export interface EquipamentoCreate {
  nome: string;
  descricao?: string;
  numeroSerie?: string;
  salaId?: number;
}
