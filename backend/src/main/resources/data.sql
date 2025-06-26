INSERT INTO usuario (
  usuario,
  nombre,
  email,
  password_hash,
  fecha_registro,
  activo
) VALUES (
  'dev',
  'Desarrollador Dev',
  'dev@example.com',
  '$2a$10$p32Ehmqve9eE.cgkbdnRJug859qblNxt1Y1/NvrDMM8OWTsc2fePK',
  CURRENT_TIMESTAMP,
  TRUE
);

-- 2) Proyectos de prueba creados por el usuario “dev” (id = 1)
INSERT INTO proyecto (
  nombre,
  descripcion,
  fecha_inicio,
  fecha_fin_estimada,
  estado,
  proyecto_padre_id,
  creado_por
) VALUES
  (
    'Proyecto Alpha',
    'Un proyecto de prueba Alpha para desarrollo.',
    '2025-01-01',
    '2025-06-30',
    'ACTIVE',
    NULL,
    1
  ),
  (
    'Proyecto Beta',
    'Un segundo proyecto de ejemplo, Beta.',
    '2025-02-15',
    '2025-12-31',
    'PAUSED',
    NULL,
    1
  );

-- Asociar al dev a un rol y miembro de proyecto:
INSERT INTO rol (proyecto_id, nombre, descripcion) VALUES (1, 'Owner', 'Propietario del proyecto');
INSERT INTO miembro_proyecto (usuario_id, proyecto_id, rol_id, fecha_asignacion) VALUES (1, 1, 1, CURRENT_TIMESTAMP)