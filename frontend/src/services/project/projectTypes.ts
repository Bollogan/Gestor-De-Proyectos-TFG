export type ProjectStatus =
  | 'ACTIVO'
  | 'EN_CURSO'
  | 'COMPLETADO'
  | 'EN_ESPERA'
  | 'EN_MANTENIMIENTO'
  | 'CANCELADO'
  | 'ARCHIVADO';

export interface ProjectOverview {
  id: number;
  name: string;
  status: ProjectStatus;
}