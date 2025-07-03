export interface HomeStats {
  totalProjects: number;
  pendingTasks: number;
  completedToday: number;
}

export async function fetchStats(): Promise<HomeStats> {
  const res = await fetch('/api/home/stats');
  if (!res.ok) throw new Error('Error al cargar estadísticas');
  return res.json();
}