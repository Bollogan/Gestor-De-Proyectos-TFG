import { useCallback } from 'react'
import Particles from 'react-tsparticles';
import { loadFull } from 'tsparticles';

export function LoginBackground() {
  const particlesInit = useCallback(async engine => {
    await loadFull(engine)
  }, [])
  return (
    <Particles
      id="login-background"
      init={particlesInit}
      options={{
        fullScreen: { enable: true, zIndex: 0 },
        background: { 
            color: { value: 'transparent' }
        },
        particles: {
          number: { value: 100 },
          color: { value: '#06d6a0' },
          shape: { type: 'circle' },
          opacity: { value: 0.5 },
          size: { value: { min: 1, max: 4 } },
          move: { enable: true, speed: 1, direction: 'none' },
          links: {
            enable: true,
            distance: 150,
            color: '#118ab2',
            opacity: 0.4,
            width: 1,
          },
        },
        interactivity: {
          events: {
            onHover: { enable: true, mode: 'grab' },
            onClick: { enable: true, mode: 'push' },
          },
          modes: {
            grab: { distance: 140, links: { opacity: 0.6 } },
            push: { quantity: 4 },
          },
        },
      }}
    />
  )
}