export interface Sala {
  id: number;
  nome: string;
  descricao?: string;
  localizacao?: string;
  createdAt: string;
}

export interface SalaCreate {
  nome: string;
  descricao?: string;
  localizacao?: string;
}
