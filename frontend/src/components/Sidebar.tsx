import React from 'react';
import { ListGroup, Badge, Button } from 'react-bootstrap'
import { ProjectOverview } from '../services/home/homeService'

type Props = {
  projects: ProjectOverview[]
  activeId: string | null
  onSelect: (id: string) => void
}

export function Sidebar({ projects, activeId, onSelect }: Props) {
  return (
    <aside className="bg-white border-end" style={{ width: 240 }}>
      <div className="p-3 border-bottom fw-bold">Proyectos</div>
      <ListGroup variant="flush">
        {projects.map(p => (
          <ListGroup.Item
            key={p.id}
            action
            active={p.id === activeId}
            onClick={() => onSelect(p.id)}
            className="d-flex justify-content-between align-items-center"
          >
            {p.name}
            <Badge bg={p.status === 'active' ? 'success' : p.status === 'paused' ? 'warning' : 'secondary'}>
              {p.status}
            </Badge>
          </ListGroup.Item>
        ))}
      </ListGroup>
      <div className="p-3">
        <Button variant="link" onClick={() => onSelect('new')}>
          + Nuevo proyecto
        </Button>
      </div>
    </aside>
  )
}