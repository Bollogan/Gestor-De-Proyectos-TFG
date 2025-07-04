import React from 'react'
import { Card, Row, Col } from 'react-bootstrap'
import { HomeStats } from '../../services/home/homeService'

type Props = {
  stats: HomeStats
}

export function StatsPanel({ stats }: Props) {
  return (
    <Row className="g-3 mb-4">
      <Col>
        <Card className="text-center">
          <Card.Body>
            <Card.Title>Total proyectos</Card.Title>
            <h3>{stats.totalProjects}</h3>
          </Card.Body>
        </Card>
      </Col>
      <Col>
        <Card className="text-center">
          <Card.Body>
            <Card.Title>Tareas pendientes</Card.Title>
            <h3>{stats.pendingTasks}</h3>
          </Card.Body>
        </Card>
      </Col>
      <Col>
        <Card className="text-center">
          <Card.Body>
            <Card.Title>Completadas hoy</Card.Title>
            <h3>{stats.completedToday}</h3>
          </Card.Body>
        </Card>
      </Col>
    </Row>
  )
}