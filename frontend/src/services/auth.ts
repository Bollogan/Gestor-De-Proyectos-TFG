const API_BASE = import.meta.env.VITE_API_URL || '';

export interface User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  email: string;
  active: boolean;
}

export async function loginService(username: string, password: string): Promise<User> {
  const res = await fetch(`${API_BASE}/auth/logIn`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ 
      userName: username, 
      password: password 
    }),
  });
  if (!res.ok) throw new Error('Credenciales inválidas');
  return res.json();
}

export async function registerService(id: null, username: string, password: string, firstName: string, email: string, active: boolean): Promise<void> {
  const res = await fetch(`${API_BASE}/auth/signUp`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      id: id,
      username: username,
      password: password,
      firstName: firstName,
      email:email, 
      active: active
    }),
  });
  if (!res.ok) throw new Error('Registro fallido');
}
