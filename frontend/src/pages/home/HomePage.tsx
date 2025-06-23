import React, { useEffect, useState } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import { Sidebar } from '../../components/Sidebar';
import { TopBar } from '../../components/TopBar';
import { StatsPanel } from '../../components/StatsPanel';
import { fetchProjects, fetchStats, ProjectOverview, HomeStats } from '../../services/home/homeService';
import './HomePage.css';

export function HomePage() {
  const [projects, setProjects] = useState<ProjectOverview[]>([]);
  const [stats, setStats]       = useState<HomeStats>({ totalProjects:0, pendingTasks:0, completedToday:0 });
  const [activeId, setActiveId] = useState<string | null>(null);

  useEffect(() => {
    fetchProjects().then(setProjects).catch(console.error)
    fetchStats().then(setStats).catch(console.error)
  }, []);

  return (
    <div className="d-flex home-background">
      <Sidebar projects={projects} activeId={activeId} onSelect={setActiveId} />

      <div className="flex-grow-1 d-flex flex-column">
        <TopBar />
        <Container fluid className="p-4 flex-grow-1 overflow-auto">
          <StatsPanel stats={stats} />
        </Container>
      </div>
    </div>
  );
}