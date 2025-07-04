import React from 'react';
import { ListGroup } from 'react-bootstrap';
import '../home/SideBar.css'

type Props = {
  selected: 'profile' | 'password';
  onSelect: (view: 'profile' | 'password') => void;
};

export function ProfileSidebar({ selected, onSelect }: Props) {
  return (
    <aside 
      className="sidebar border-end d-flex flex-column"
      style={{ width: 300, height: '100vh' }}
    >
      <div className="sidebar-header px-3 py-2">
        <h2 className="mb-2">Tu Cuenta</h2>
      </div>
      <ListGroup variant="flush" className="flex-grow-1 m-2">
        <ListGroup.Item
          className='mb-2 rounded-3'
          action
          active={selected === 'profile'}
          onClick={() => onSelect('profile')}
        >
          Perfil
        </ListGroup.Item>
        <ListGroup.Item
          className='mb-2 rounded-3'
          action
          active={selected === 'password'}
          onClick={() => onSelect('password')}
        >
          Contraseña
        </ListGroup.Item>
      </ListGroup>
    </aside>
  );
}
