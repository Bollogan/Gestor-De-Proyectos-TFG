package org.gestor.de.proyectos.TFG.project.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permisos")
public class Permisos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean administrarProyecto;

    private Boolean gestionarMiembros;

    private Boolean gestionarTareas;

    private Boolean gestionarVersiones;

    private Boolean gestionarPermisos;

    private Boolean gestionarRoles;
}
