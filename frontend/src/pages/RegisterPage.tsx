import React, { useState } from "react";
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
import logo from "../images/logo.png";
import "../index.css";
import { registerService } from "../services/auth/auth";

export function RegisterPage() {
  const navigate = useNavigate();
  const [username, setUsername] = useState<string>("");
  const [firstName, setFirstName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");

  const [usernameError, setUsernameError] = useState<string | null>(null);
  const [firstNameError, setFirstNameError] = useState<string | null>(null);
  const [emailError, setEmailError] = useState<string | null>(null);
  const [passwordError, setPasswordError] = useState<string | null>(null);
  const [confirmPasswordError, setConfirmPasswordError] = useState<
    string | null
  >(null);

  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const validateUsername = (): string | null => {
    if (username.trim().length < 4) {
      return "El usuario debe tener al menos 4 caracteres";
    }
    return null;
  };

  const validateFirstName = (): string | null => {
    if (firstName.trim() === "") {
      return "El nombre no puede estar vacío";
    }
    return null;
  };

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const validateEmail = (): string | null => {
    if (!emailRegex.test(email)) {
      return "Introduce un email con formato válido";
    }
    return null;
  };

  const validatePassword = (): string | null => {
    if (password.length < 8) {
      return "La contraseña debe tener al menos 8 caracteres";
    }
    if (!/[A-Za-z]/.test(password) || !/[0-9]/.test(password)) {
      return "La contraseña debe contener al menos una letra y un número";
    }
    return null;
  };

  const validateConfirmPassword = (): string | null => {
    if (confirmPassword !== password) {
      return "Las contraseñas no coinciden";
    }
    return null;
  };

  const validateAll = (): boolean => {
    const uErr = validateUsername();
    const fErr = validateFirstName();
    const eErr = validateEmail();
    const pErr = validatePassword();
    const cpErr = validateConfirmPassword();

    setUsernameError(uErr);
    setFirstNameError(fErr);
    setEmailError(eErr);
    setPasswordError(pErr);
    setConfirmPasswordError(cpErr);

    return !(uErr || fErr || eErr || pErr || cpErr);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    if (!validateAll()) {
      return;
    }

    setLoading(true);
    try {
      await registerService(null, username, password, firstName, email, true);
      navigate("/login");
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
                backgroundColor: "rgba(255,255,255,0.6)",
                borderRadius: "12px",
              }}
            >
              <Card.Body className="p-4">
                <div className="text-center mb-4">
                  <Image src={logo} alt="BreoPM" width={300} />
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
                      onChange={(e) => setUsername(e.target.value)}
                      onBlur={() => setUsernameError(validateUsername())}
                      isInvalid={!!usernameError}
                      required
                    />
                    <Form.Control.Feedback type="invalid">
                      {usernameError}
                    </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group controlId="formFirstName" className="mb-3">
                    <Form.Label>Nombre</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="Tu nombre"
                      value={firstName}
                      onChange={(e) => setFirstName(e.target.value)}
                      onBlur={() => setFirstNameError(validateFirstName())}
                      isInvalid={!!firstNameError}
                      required
                    />
                    <Form.Control.Feedback type="invalid">
                      {firstNameError}
                    </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group controlId="formEmail" className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control
                      type="email"
                      placeholder="tucorreo@ejemplo.com"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      onBlur={() => setEmailError(validateEmail())}
                      isInvalid={!!emailError}
                      required
                    />
                    <Form.Control.Feedback type="invalid">
                      {emailError}
                    </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group controlId="formPassword" className="mb-3">
                    <Form.Label>Contraseña</Form.Label>
                    <Form.Control
                      type="password"
                      placeholder="••••••••"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      onBlur={() => setPasswordError(validatePassword())}
                      isInvalid={!!passwordError}
                      required
                    />
                    <Form.Control.Feedback type="invalid">
                      {passwordError}
                    </Form.Control.Feedback>
                  </Form.Group>

                  <Form.Group controlId="formConfirmPassword" className="mb-3">
                    <Form.Label>Confirmar Contraseña</Form.Label>
                    <Form.Control
                      type="password"
                      placeholder="Repite tu contraseña"
                      value={confirmPassword}
                      onChange={(e) => setConfirmPassword(e.target.value)}
                      onBlur={() =>
                        setConfirmPasswordError(validateConfirmPassword())
                      }
                      isInvalid={!!confirmPasswordError}
                      required
                    />
                    <Form.Control.Feedback type="invalid">
                      {confirmPasswordError}
                    </Form.Control.Feedback>
                  </Form.Group>

                  <Button
                    type="submit"
                    variant="success"
                    className="w-100 mb-2"
                    disabled={loading}
                  >
                    {loading ? "Registrando…" : "Registrarse"}
                  </Button>
                </Form>

                <div className="text-center">
                  ¿Ya tienes cuenta?{" "}
                  <Button variant="link" onClick={() => navigate("/login")}>
                    Iniciar Sesión
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
