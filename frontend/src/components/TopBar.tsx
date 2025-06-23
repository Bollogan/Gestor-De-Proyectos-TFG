import React, { useContext } from 'react'
import { Navbar, Nav, Dropdown, Image } from 'react-bootstrap'
import { FaBell } from 'react-icons/fa'
import { AuthContext } from '../context/AuthContext'
import { useNavigate } from 'react-router-dom'

export function TopBar() {
  const { user, logout } = useContext(AuthContext);
  const navigate = useNavigate();

  return (
    <Navbar bg="light" expand={false} className="border-bottom px-3">
      <Navbar.Brand className="me-auto">Breo PM</Navbar.Brand>
      <Nav className="align-items-center">
        <Nav.Link onClick={() => {/* mostrar notificaciones */}}>
          <FaBell size={20} />
        </Nav.Link>
        <Dropdown align="end">
          <Dropdown.Toggle variant="link" id="user-menu" bsPrefix="p-0">
            <Image src={user.avatarUrl} roundedCircle width={32} height={32} />
          </Dropdown.Toggle>
          <Dropdown.Menu>
            <Dropdown.Item onClick={() => navigate('/profile')}>
              Modificar perfil
            </Dropdown.Item>
            <Dropdown.Divider />
            <Dropdown.Item onClick={logout}>Cerrar sesión</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </Nav>
    </Navbar>
  )
}
