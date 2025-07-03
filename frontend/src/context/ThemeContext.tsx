import React, {
  createContext,
  ReactNode,
  useContext,
  useEffect,
  useState,
} from 'react';
import { themes, ThemePalette } from '../styles/themes';

interface ThemeContextProps {
  themeName: string;
  setThemeName: (name: string) => void;
}
export const ThemeContext = createContext<ThemeContextProps>({
  themeName: 'default',
  setThemeName: () => {},
});

export const ThemeProvider: React.FC<{children: ReactNode}> = ({ children }) => {
  const [themeName, setThemeName] = useState<string>(
    () => window.localStorage.getItem('theme') || 'default'
  );

  useEffect(() => {
    window.localStorage.setItem('theme', themeName);
    const palette: ThemePalette = themes[themeName] || themes.default;
    Object.entries(palette).forEach(([varName, color]) => {
      document.documentElement.style.setProperty(varName, color);
    });
  }, [themeName]);

  return (
    <ThemeContext.Provider value={{ themeName, setThemeName }}>
      {children}
    </ThemeContext.Provider>
  );
};

export const useTheme = () => useContext(ThemeContext);
