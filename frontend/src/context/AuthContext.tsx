import React, { createContext, useState, useCallback, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import type { User } from '../services/auth';
import { fetchCurrentUser, AuthenticatedUserDTO, logoutService } from '../services/auth'

interface AuthContextType {
  user: User | null
  setUser: (u: User | null) => void
  logout: () => void
  initializing: boolean
}

export const AuthContext = createContext<AuthContextType>({
  user: null,
  setUser: () => {},
  logout: () => {},
  initializing: true,
})

export const AuthProvider: React.FC<{children: React.ReactNode}> = ({ children }) => {
  const navigate = useNavigate()
  const [user, setUser] = useState<User | null>(null)
  const [initializing, setInitializing] = useState(true)

  const logout = useCallback(async () => {
    try {
      await logoutService();    // borra la cookie en el servidor
    } catch (err) {
      console.error('Logout fallido:', err);
    }
    setUser(null);
    navigate('/login');
  }, [navigate]);

  useEffect(() => {
    fetchCurrentUser()
      .then(dto => setUser(dto))
      .catch(() => setUser(null))
      .finally(() => setInitializing(false))
  }, [])

  return (
    <AuthContext.Provider value={{ user, setUser, logout, initializing }}>
      {children}
    </AuthContext.Provider>
  )
}