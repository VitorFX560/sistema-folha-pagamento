export type TipoColaborador = 'PADRAO' | 'COMISSIONADO' | 'PRODUCAO';
export interface Colaborador { matricula: number; nome: string; salarioBase: number; tipo_colaborador: TipoColaborador; valorVendas?: number; percentualComissao?: number; quantidadeProduzida?: number; valorPorUnidade?: number }
export interface ItemFolha { matricula: number; nome: string; tipoColaborador: string; salarioBase: number; salarioFinal: number }
export interface ResumoFolha { quantidadeColaboradores: number; valorTotalFolha: number }
export type NovoColaborador = Omit<Colaborador, 'matricula'>;
