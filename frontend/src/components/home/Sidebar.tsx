import React, { useState, ChangeEvent } from 'react';
import { Card, Badge, Button, InputGroup, FormControl } from 'react-bootstrap';
import { motion } from 'framer-motion';
import { ProjectOverview } from '../../services/project/projectTypes';
import './Sidebar.css';

type Props = {
  projects: ProjectOverview[]
  activeId: string | null
  onSelect: (id: string) => void
}

export function Sidebar({ projects, activeId, onSelect }: Props) {
  const [filter, setFilter] = useState('');

  const filtered = projects.filter(p =>
    p.name.toLowerCase().includes(filter.toLowerCase())
  );

  const handleSearch = (e: ChangeEvent<HTMLInputElement>) => {
    setFilter(e.target.value);
  };

  const statusVariant = (status: ProjectOverview['status']) => {
    switch (status) {
      case 'ACTIVO':           return 'success';
      case 'EN_CURSO':         return 'primary';
      case 'COMPLETADO':       return 'secondary';
      case 'EN_ESPERA':        return 'warning';
      case 'EN_MANTENIMIENTO': return 'info';
      case 'CANCELADO':        return 'danger';
      case 'ARCHIVADO':        return 'dark';
      default:                 return 'light';
    }
  };
  
  return (
    <aside
      className="sidebar border-end d-flex flex-column"
      style={{ width: 300, height: '100vh' }}
    >
      <div className="sidebar-header px-3 py-2">
        <h2 className="mb-2">Tus Proyectos</h2>
        <InputGroup size="sm">
          <FormControl
            placeholder="Buscar..."
            value={filter}
            onChange={handleSearch}
          />
        </InputGroup>
      </div>
      <div className="project-list">
        {filtered.map((p) => (
          <motion.div
            key={p.id}
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
          >
            <Card
              className={`project-card ${p.id.toString() === activeId ? 'active' : ''}`}
              onClick={() => onSelect(p.id.toString())}
            >
              <Card.Body className="d-flex flex-column align-items-center">
                <Card.Title className="text-center mb-1">{p.name}</Card.Title>
                <Badge bg={statusVariant(p.status)} className="mt-1">
                  {p.status.replace('_', ' ')}
                </Badge>
              </Card.Body>
            </Card>
          </motion.div>
        ))}

        {filtered.length === 0 && (
          <div className="no-projects">No hay proyectos</div>
        )}
      </div>

      <div className="sidebar-footer px-3 py-2">
        <Button
          size="sm"
          className="primary-btn w-100"
          onClick={() => onSelect('new')}
        >
          + Nuevo proyecto
        </Button>
      </div>
    </aside>
  )
}