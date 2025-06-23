import React, { useState, useContext } from 'react'
import {
  Container,
  Row,
  Col,
  Card,
  Form,
  Button,
  Alert,
  Image,
} from 'react-bootstrap'
import { LoginBackground } from '../components/LoginBackground'
import { useNavigate } from "react-router-dom"
import { registerService } from "../services/auth"
import logo from '../images/logo.png'

export function RegisterPage() {
  const navigate = useNavigate()
  const [username, setUsername] = useState<string>("")
  const [firstName, setFirstName] = useState<string>("")
  const [email, setEmail] = useState<string>("")
  const [password, setPassword] = useState<string>("")
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading]     = useState(false)

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setLoading(true)
    setError(null)
    try {
      await registerService(null, username, password, firstName, email, true)
      navigate("/login");
    } catch (err: any) {
      setError(err.message);
    } finally {
      setLoading(false)
    }
  }

  return (
    <div style={{ position: 'relative', minHeight: '100vh' }}>
      <LoginBackground />
      <Container
        fluid
        className="d-flex align-items-center justify-content-center"
        style={{
          position: 'absolute',
          inset: 0,
          zIndex: 1,
        }}
      >
        <Row className="w-100 justify-content-center">
          <Col xs={10} sm={8} md={6} lg={4}>
            <Card
              className="shadow-lg border-0"
              style={{
                backdropFilter: 'blur(10px)',
                backgroundColor: 'rgba(255,255,255,0.6)',
                borderRadius: '12px',
              }}
            >
              <Card.Body className="p-4">
                <div className="text-center mb-4">
                  <Image
                    src={logo}
                    alt="BreoPM"
                    width={300}
                  />
                </div>

                <h3 className="text-center mb-3">Crear Cuenta</h3>
                {error && <Alert variant="danger">{error}</Alert>}

                <Form onSubmit={handleSubmit}>
                  <Form.Group controlId="formUsername" className="mb-3">
                    <Form.Label>Usuario</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="Tu usuario"
                      value={username}
                      onChange={e => setUsername(e.target.value)}
                      required
                    />
                  </Form.Group>

                  <Form.Group controlId="formFirstName" className="mb-3">
                    <Form.Label>Nombre</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="Tu nombre"
                      value={firstName}
                      onChange={e => setFirstName(e.target.value)}
                      required
                    />
                  </Form.Group>

                  <Form.Group controlId="formEmail" className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                      type="email"
                      placeholder="tucorreo@ejemplo.com"
                      value={email}
                      onChange={e => setEmail(e.target.value)}
                      required
                    />
                  </Form.Group>

                  <Form.Group controlId="formPassword" className="mb-3">
                    <Form.Label>Contraseña</Form.Label>
                    <Form.Control
                      type="password"
                      placeholder="••••••••"
                      value={password}
                      onChange={e => setPassword(e.target.value)}
                      required
                    />
                  </Form.Group>

                  <Button
                    type="submit"
                    variant="success"
                    className="w-100 mb-2"
                    disabled={loading}
                  >
                    {loading ? 'Registrando…' : 'Registrarse'}
                  </Button>
                </Form>

                <div className="text-center">
                  ¿Ya tienes cuenta?{' '}
                  <Button
                    variant="link"
                    onClick={() => navigate('/login')}
                  >
                    Iniciar Sesión
                  </Button>
                </div>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  )
}
