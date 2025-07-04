import React, { ChangeEvent, FormEvent, useState, useContext } from 'react';
import { Card, Form, Button, Alert, Spinner } from 'react-bootstrap';
import { changePassword, PasswordChangeData } from '../../services/user/userService';
import { AuthContext } from '../../context/AuthContext';

export function PasswordChangeForm() {
  const { user } = useContext(AuthContext)!;
  const [data, setData] = useState<PasswordChangeData>({ currentPassword: '', newPassword: '' });
  const [error, setError] = useState<string|null>(null);
  const [success, setSuccess] = useState(false);
  const [loading, setLoading] = useState(false);

  const onChange = (e: ChangeEvent<HTMLInputElement>) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError(null); setSuccess(false); setLoading(true);
    try {
      await changePassword(user!.id, data);
      setSuccess(true);
      setData({ currentPassword: '', newPassword: '' });
    } catch (err: any) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Card className="p-4 profile-form">
      {error && <Alert variant="danger">{error}</Alert>}
      {success && <Alert variant="success">Contraseña cambiada.</Alert>}
      <Form onSubmit={onSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>Contraseña actual</Form.Label>
          <Form.Control
            name="currentPassword"
            type="password"
            value={data.currentPassword}
            onChange={onChange}
            required
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Nueva contraseña</Form.Label>
          <Form.Control
            name="newPassword"
            type="password"
            value={data.newPassword}
            onChange={onChange}
            required
          />
        </Form.Group>
        <Button type="submit" disabled={loading}>
          {loading ? <Spinner animation="border" size="sm"/> : 'Cambiar contraseña'}
        </Button>
      </Form>
    </Card>
  );
}
