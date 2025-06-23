export interface ProjectOverview {
  id: string;
  name: string;
  status: 'active' | 'paused' | 'completed';
}

export interface HomeStats {
  totalProjects: number;
  pendingTasks: number;
  completedToday: number;
}

export async function fetchProjects(): Promise<ProjectOverview[]> {
  const res = await fetch('/api/projects');
  if (!res.ok) throw new Error('Error al cargar proyectos');
  return res.json();
}

export async function fetchStats(): Promise<HomeStats> {
  const res = await fetch('/api/home/stats');
  if (!res.ok) throw new Error('Error al cargar estadísticas');
  return res.json();
}