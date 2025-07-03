import React, { useContext, useState } from "react";
import {
  Alert,
  Button,
  Card,
  Col,
  Container,
  Form,
  Image,
  Row,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { LoginBackground } from "../components/LoginBackground";
import { AuthContext } from "../context/AuthContext";
import logo from '../images/logo.png';
import { loginService } from "../services/auth";
import { useTheme } from '../context/ThemeContext';
import '../index.css';

export function LoginPage() {
  const { setUser } = useContext(AuthContext);
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
  const { setThemeName } = useTheme();

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    try {
      const user = await loginService(username, password);
      setUser(user);
      setThemeName(user.theme);
      navigate("/home");
    } catch (err: any) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ position: "relative", minHeight: "100vh" }}>
      <LoginBackground />
      <Container
        fluid
        className="d-flex align-items-center justify-content-center"
        style={{
          position: "absolute",
          inset: 0,
          zIndex: 1,
        }}
      >
        <Row className="w-100 justify-content-center">
          <Col xs={10} sm={8} md={6} lg={4}>
            <Card
              className="shadow-lg border-0"
              style={{
                backdropFilter: "blur(10px)",
                backgroundColor: "rgba(255, 255, 255, 0.6)",
                borderRadius: "12px",
              }}
            >
              <Card.Body className="pl-4 pr-4 pt-5 pb-4">
                <div className="text-center mb-4">
                  <Image
                    src={logo}
                    alt="BreoPM"
                    width={300}
                  />
                </div>
                <h3 className="text-center mb-2">Iniciar Sesión</h3>
                {error && <Alert variant="danger">{error}</Alert>}

                <Form onSubmit={onSubmit}>
                  <Form.Group controlId="formUsername" className="mb-3">
                    <Form.Label>Usuario</Form.Label>
                    <Form.Control
                      type="text"
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                      required
                    />
                  </Form.Group>

                  <Form.Group controlId="formPassword" className="mb-3">
                    <Form.Label>Contraseña</Form.Label>
                    <Form.Control
                      type="password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                    />
                  </Form.Group>

                  <Button
                    type="submit"
                    variant="primary"
                    className="w-100"
                    disabled={loading}
                  >
                    {loading ? "Cargando…" : "Entrar"}
                  </Button>
                </Form>

                <div className="text-center mt-3">
                  ¿No tienes cuenta?{" "}
                  <Button variant="link" onClick={() => navigate("/register")}>
                    Regístrate
                  </Button>
                </div>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
}
