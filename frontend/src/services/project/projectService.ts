import { ProjectOverview } from "./projectTypes";

const API_BASE = import.meta.env.VITE_API_URL || '';

export async function fetchUserProjects(userId: number): Promise<ProjectOverview[]> {
  const res = await fetch(`${API_BASE}/users/${userId}/projects`, {
    method: 'GET',
    credentials: 'include',
  });
  if (!res.ok) {
    const txt = await res.text();
    throw new Error(txt || res.statusText);
  }
  return res.json() as Promise<ProjectOverview[]>;
}