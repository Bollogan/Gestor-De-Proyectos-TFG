INSERT INTO usuario (
  usuario,
  nombre,
  email,
  password_hash,
  fecha_registro,
  activo,
  tema
) VALUES (
  'dev',
  'Desarrollador Dev',
  'dev@example.com',
  '$2a$10$p32Ehmqve9eE.cgkbdnRJug859qblNxt1Y1/NvrDMM8OWTsc2fePK',
  CURRENT_TIMESTAMP,
  TRUE,
  'default'
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
  ('Desarrollo Plataforma', 
 'Implementación del backend y API REST', 
 '2025-01-10', 
 '2025-06-30', 
 'ACTIVO', 
 NULL, 
 1),

-- 2. Subproyecto en curso
('Diseño UI/UX', 
 'Creación de mockups y prototipos de la interfaz', 
 '2025-02-01', 
 '2025-04-15', 
 'EN_CURSO', 
 NULL, 
 1),

-- 3. Proyecto ya completado
('Migración a la nube', 
 'Traslado de infraestructuras on-premise a AWS', 
 '2024-05-01', 
 '2024-10-31', 
 'COMPLETADO', 
 NULL, 
 1),

-- 4. Proyecto en espera
('Integración ERP', 
 'Conectar sistema ERP con CRM existente', 
 '2025-03-15', 
 '2025-09-15', 
 'EN_ESPERA', 
 NULL, 
 1),

-- 5. Proyecto en mantenimiento
('Mantenimiento Web', 
 'Soporte y actualizaciones de la web corporativa', 
 '2024-11-01', 
 '2025-12-31', 
 'EN_MANTENIMIENTO', 
 NULL, 
 1),

-- 6. Proyecto cancelado
('Desarrollo App Móvil', 
 'Aplicación mobile para usuarios finales', 
 '2025-04-01', 
 '2025-08-01', 
 'CANCELADO', 
 NULL, 
 1),

-- 7. Proyecto archivado
('Auditoría de Seguridad', 
 'Revisión y mejora de controles de seguridad', 
 '2023-09-01', 
 '2023-12-15', 
 'ARCHIVADO', 
 NULL, 
 1);

-- Asociar al dev a un rol y miembro de proyecto:
INSERT INTO permisos (id, administrar_proyecto, gestionar_miembros, gestionar_tareas, gestionar_versiones, gestionar_permisos, gestionar_roles) VALUES (1, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE);
INSERT INTO rol (id, proyecto_id, nombre, descripcion, permisos_id) VALUES (1, 1, 'Owner', 'Propietario del proyecto', 1);
INSERT INTO miembro_proyecto (usuario_id, proyecto_id, rol_id, fecha_asignacion) VALUES
(1, 1, 1, CURRENT_TIMESTAMP),
(1, 2, 1, CURRENT_TIMESTAMP),
(1, 3, 1, CURRENT_TIMESTAMP),
(1, 4, 1, CURRENT_TIMESTAMP),
(1, 5, 1, CURRENT_TIMESTAMP),
(1, 6, 1, CURRENT_TIMESTAMP),
(1, 7, 1, CURRENT_TIMESTAMP);
