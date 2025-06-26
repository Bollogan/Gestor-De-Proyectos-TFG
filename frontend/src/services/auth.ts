const API_BASE = import.meta.env.VITE_API_URL || '';

export interface User {
  id: number
  username: string
  password: string
  firstName: string
  email: string
  active: boolean
  avatarUrl?: string
}

export interface AuthenticatedUserDTO extends User {
  serviceToken?: string
}

export async function loginService(
  username: string, 
  password: string
): Promise<User> {
  const res = await fetch(`${API_BASE}/auth/logIn`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    credentials: 'include',      // ← envía/recibe cookies
    body: JSON.stringify({ userName: username, password }),
  });
  if (!res.ok) throw new Error('Credenciales inválidas');
  return res.json() as Promise<User>;
}

export async function loginFromServiceToken(
  serviceToken: string
): Promise<AuthenticatedUserDTO> {
  console.log('[Auth] intentando loginFromServiceToken con:', serviceToken)
  const res = await fetch(`${API_BASE}/auth/logInFromServiceToken`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Service-Token': serviceToken,
    },
  })
  if (!res.ok) {
    const text = await res.text()
    throw new Error(`Login con token fallido: ${text}`)
  }
  return res.json() as Promise<AuthenticatedUserDTO>
}

export async function registerService(
  id: null,
  username: string,
  password: string,
  firstName: string,
  email: string,
  active: boolean
): Promise<void> {
  const res = await fetch(`${API_BASE}/auth/signUp`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      id,
      username,
      password,
      firstName,
      email,
      active,
    }),
  })
  if (!res.ok) throw new Error('Registro fallido')
}

export async function fetchCurrentUser() {
  const res = await fetch(`${API_BASE}/auth/me`, {
    method: 'GET',
    credentials: 'include',
  });
  if (!res.ok) throw new Error('No autenticado');
  return res.json();
}

export async function logoutService(): Promise<void> {
  const res = await fetch(`${API_BASE}/auth/logout`, {
    method: 'POST',
    credentials: 'include',
  });
  if (!res.ok) {
    throw new Error('Error al cerrar sesión');
  }
}