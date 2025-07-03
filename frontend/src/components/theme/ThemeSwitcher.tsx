import React, { useContext } from 'react';
import { Dropdown } from 'react-bootstrap';
import { useTheme } from '../../context/ThemeContext';
import { themes } from '../../styles/themes';
import { setUserTheme } from '../../services/auth';
import { AuthContext } from '../../context/AuthContext';

export const ThemeSwitcher: React.FC = () => {
  const { themeName, setThemeName } = useTheme();
  const { user, setUser } = useContext(AuthContext);
  const themeNames = Object.keys(themes);

  const handleSelect = async (newTheme: string) => {
    setThemeName(newTheme);
    if (user) {
      await setUserTheme(user.id, newTheme);
      setUser({ ...user, theme: newTheme });
    }
  };

  return (
    <Dropdown align="end" className="me-3">
      <Dropdown.Toggle variant="outline-secondary" size="sm">
        Tema: {themeName}
      </Dropdown.Toggle>
      <Dropdown.Menu>
        {themeNames.map((name) => (
          <Dropdown.Item
            key={name}
            active={name === themeName}
            onClick={() => handleSelect(name)}
          >
            {name}
          </Dropdown.Item>
        ))}
      </Dropdown.Menu>
    </Dropdown>
  );
};