import React, { useContext, useEffect, useState } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import { Sidebar } from '../../components/Sidebar';
import { AuthContext } from '../../context/AuthContext';
import { TopBar } from '../../components/TopBar';
import { StatsPanel } from '../../components/StatsPanel';
import { fetchStats, HomeStats } from '../../services/home/homeService';
import { fetchUserProjects } from '../../services/project/projectService';
import { ProjectOverview } from '../../services/project/projectTypes';
import { useNavigate, useParams } from 'react-router-dom';
import '../../index.css';
import './HomePage.css'; 

export function HomePage(){
  const { user } = useContext(AuthContext);
  const [projects, setProjects] = useState<ProjectOverview[]>([]);
  const [stats, setStats]       = useState<HomeStats>({ totalProjects:0, pendingTasks:0, completedToday:0 });
  const [activeId, setActiveId] = useState<string | null>(null);
  const navigate = useNavigate();
  const { projectId } = useParams<{ projectId?: string }>();

  useEffect(() => {
    if (!user) return;
    fetchUserProjects(user.id).then(setProjects).catch(console.error);
    fetchStats().then(setStats).catch(console.error);
  }, []);

  useEffect(() => {
    setActiveId(projectId ?? null);
  }, [projectId]);

  const handleSelect = (id: string) => {
    if (id === 'new') {
      navigate('/projects/new');
    } else {
      navigate(`/projects/${id}`);
    }
    setActiveId(id);
  };

  return (
    <div className="d-flex home-background">
      <Sidebar projects={projects} activeId={activeId} onSelect={handleSelect} />

      <div className="flex-grow-1 d-flex flex-column">
        <TopBar />
        <Container fluid className="p-4 flex-grow-1 overflow-auto">
          <StatsPanel stats={stats} />
        </Container>
      </div>
    </div>
  );
}