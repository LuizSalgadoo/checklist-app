export enum DiaDaSemana {
  DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO
}

export const DiasDaSemanaArray: string[] = [
  'DOMINGO', 'SEGUNDA', 'TERCA', 'QUARTA', 'QUINTA', 'SEXTA', 'SABADO'
];

export interface Tarefa {
  id: string;
  nome: string;
  prioridade: string;
  horamarcada: string;
  status: string;
  referencia: string
  diasDaSemana: DiaDaSemana[];
  repeticaoHoras?: number;
}
