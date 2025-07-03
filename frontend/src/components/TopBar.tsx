import React, { useContext } from 'react';
import {
  Navbar,
  Nav,
  NavDropdown,
  Dropdown,
  Button,
  Container,
  Image
} from 'react-bootstrap';
import { FaBell, FaPlus } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import { useTheme } from '../context/ThemeContext';
import { themes } from '../styles/themes';
import { setUserTheme } from '../services/auth';
import avatarPlaceholder from '../images/defaultAvatar.png'

export const TopBar: React.FC = () => {
  const { user, logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const { themeName, setThemeName } = useTheme();
  const themeNames = Object.keys(themes);

  const handleThemeSelect = async (newTheme: string) => {
    setThemeName(newTheme);
    if (user?.id) {
      try {
        await setUserTheme(user.id, newTheme);
      } catch (err) {
        console.error('Error guardando tema:', err);
      }
    }
  };

  return (
    <Navbar expand="md" className="border-bottom px-3">
      <Container fluid>
        <h2>Breo PM</h2>
        
        <Nav className="ms-auto d-flex align-items-center gap-3">

          <Button
            className='primary-btn'
            size="sm"
            onClick={() => navigate('/tasks/new')}
          >
            <FaPlus /> Tarea
          </Button>

          <Dropdown
            align="end"
            as={Nav.Item}
            className="theme-dropdown d-flex align-items-center"
            container="body"
          >
            <Dropdown.Toggle
              as={Nav.Link}
              className="theme-dropdown px-2 nav-link"
            >
              Tema: {themeName}
            </Dropdown.Toggle>
            <Dropdown.Menu>
              {themeNames.map(name => (
                <Dropdown.Item
                  key={name}
                  active={name === themeName}
                  onClick={() => handleThemeSelect(name)}
                >
                  {name}
                </Dropdown.Item>
              ))}
            </Dropdown.Menu>
          </Dropdown>

          <Nav.Link
            onClick={() => navigate('/notifications')}
            className="position-relative px-2 main-text"
          >
            <FaBell size={20} />
          </Nav.Link>

          <NavDropdown
            title={
              <Image
                src={user?.avatarUrl || avatarPlaceholder}
                roundedCircle
                width={32}
                height={32}
              />
            }
            id="user-nav-dropdown"
            align="end"
            menuVariant="light"
          >
            <NavDropdown.Item onClick={() => navigate('/profile')}>
              Modificar perfil
            </NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item onClick={logout}>
              Cerrar sesión
            </NavDropdown.Item>
          </NavDropdown>

        </Nav>
      </Container>
    </Navbar>
  );
};