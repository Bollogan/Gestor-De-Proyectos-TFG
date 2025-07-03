import React, { createContext, useCallback, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import type { User } from "../services/auth/auth";
import { fetchCurrentUser, logoutService } from "../services/auth/auth";
import { useTheme } from "./ThemeContext";

interface AuthContextType {
  user: User | null;
  setUser: (u: User | null) => void;
  logout: () => void;
  initializing: boolean;
}

export const AuthContext = createContext<AuthContextType>({
  user: null,
  setUser: () => {},
  logout: () => {},
  initializing: true,
});

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const navigate = useNavigate();
  const [user, setUser] = useState<User | null>(null);
  const [initializing, setInitializing] = useState(true);
  const { setThemeName } = useTheme();

  const logout = useCallback(async () => {
    try {
      await logoutService(); // borra la cookie en el servidor
    } catch (err) {
      console.error("Logout fallido:", err);
    }
    setUser(null);
    navigate("/login");
  }, [navigate]);

  useEffect(() => {
    fetchCurrentUser()
      .then((u: User) => {
        setUser(u);
        setThemeName(u.theme ?? "default");
      })
      .catch(() => {
        setUser(null);
      })
      .finally(() => {
        setInitializing(false);
      });
  }, []);

  return (
    <AuthContext.Provider value={{ user, setUser, logout, initializing }}>
      {children}
    </AuthContext.Provider>
  );
};
