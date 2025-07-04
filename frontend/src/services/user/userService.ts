import { User } from '../auth/auth';

const API_BASE = import.meta.env.VITE_API_URL || '';

export interface UserUpdateData {
  username?: string;
  name?: string;
  email?: string;
}

export interface PasswordChangeData {
  currentPassword: string;
  newPassword: string;
}

export async function fetchUserById(userId: number): Promise<User> {
  const res = await fetch(`${API_BASE}/users/${userId}`, { credentials: 'include' });
  if (!res.ok) throw new Error('No se pudo cargar usuario');
  return res.json();
}

export async function updateUser(
  userId: number,
  data: UserUpdateData
): Promise<User> {
  const res = await fetch(`${API_BASE}/users/${userId}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    credentials: 'include',
    body: JSON.stringify(data)
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}

export async function changePassword(
  userId: number,
  data: PasswordChangeData
): Promise<void> {
  const res = await fetch(`${API_BASE}/users/${userId}/password`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    credentials: 'include',
    body: JSON.stringify(data)
  });
  if (!res.ok) throw new Error(await res.text());
}

export async function uploadAvatar(
  userId: number,
  file: File
): Promise<void> {
  const form = new FormData();
  form.append('file', file);
  const res = await fetch(`${API_BASE}/users/${userId}/avatar`, {
    method: 'POST',
    credentials: 'include',
    body: form
  });
  if (!res.ok) throw new Error(await res.text());
}