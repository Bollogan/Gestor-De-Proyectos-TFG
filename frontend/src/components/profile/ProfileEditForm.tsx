import React, { ChangeEvent, FormEvent, useState, useEffect, useContext } from 'react';
import { Card, Form, Button, Alert, Spinner, Image } from 'react-bootstrap';
import { FaPencilAlt } from 'react-icons/fa';
import { fetchUserById, updateUser, UserUpdateData, uploadAvatar } from '../../services/user/userService';
import avatarPlaceholder from "../../images/defaultAvatar.png";
import { AuthContext } from '../../context/AuthContext';
import '../../pages/profile/ProfilePage.css';

export function ProfileEditForm() {
  const { user, setUser } = useContext(AuthContext)!;
  const [data, setData] = useState<UserUpdateData>({});
  const [error, setError] = useState<string|null>(null);
  const [success, setSuccess] = useState(false);
  const [loading, setLoading] = useState(false);

  // Avatar
  const [avatarFile, setAvatarFile] = useState<File|null>(null);
  const [preview, setPreview] = useState<string>(`/users/avatar/${user?.avatarUrl}` || '');
  const [avatarLoading, setAvatarLoading] = useState(false);

  useEffect(() => {
    if (!user) return;
    fetchUserById(user.id)
      .then(u => {
        setData({ username: u.username, name: u.firstName, email: u.email });
        setPreview(u.avatarUrl ? `/users/avatar/${u.avatarUrl}` : '');
      })
      .catch(err => setError(err.message));
  }, [user]);

  const onChange = (e: ChangeEvent<HTMLInputElement>) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setError(null);
    setSuccess(false);
    setLoading(true);
    try {
      const updated = await updateUser(user!.id, data);
      setUser(updated);
      setSuccess(true);
    } catch (err: any) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const onAvatarSelect = (e: ChangeEvent<HTMLInputElement>) => {
    const f = e.target.files?.[0] ?? null;
    if (f) {
      setAvatarFile(f);
      setPreview(URL.createObjectURL(f));
    }
  };

  const onAvatarUpload = async () => {
    if (!avatarFile) return;
    setAvatarLoading(true);
    try {
      await uploadAvatar(user!.id, avatarFile);
      // recarga user
      const u = await fetchUserById(user!.id);
      setUser(u);
    } catch (err: any) {
      setError(err.message);
    } finally {
      setAvatarLoading(false);
    }
  };

  return (
    <Card className="p-4 profile-form">
      {error && <Alert variant="danger">{error}</Alert>}
      {success && <Alert variant="success">Perfil guardado.</Alert>}

      <div className="avatar-preview-wrapper mb-4">
        <Image src={preview != null ? preview : avatarPlaceholder} roundedCircle className="avatar-preview" />
        <label className="avatar-overlay">
          <FaPencilAlt />
          <input type="file" accept="image/*" onChange={onAvatarSelect} hidden />
        </label>
      </div>

      <Form onSubmit={onSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>Usuario</Form.Label>
          <Form.Control
            name="usuario"
            value={data.username || ''}
            onChange={onChange}
            required
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Nombre completo</Form.Label>
          <Form.Control
            name="nombre"
            value={data.name || ''}
            onChange={onChange}
            required
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label>Email</Form.Label>
          <Form.Control
            name="email"
            type="email"
            value={data.email || ''}
            onChange={onChange}
            required
          />
        </Form.Group>
        <Button type="submit" disabled={loading}>
          {loading ? <Spinner animation="border" size="sm"/> : 'Guardar perfil'}
        </Button>
        {' '}
        <Button variant="secondary" disabled={!avatarFile || avatarLoading} onClick={onAvatarUpload}>
          {avatarLoading ? <Spinner animation="border" size="sm"/> : 'Subir avatar'}
        </Button>
      </Form>
    </Card>
  );
}
