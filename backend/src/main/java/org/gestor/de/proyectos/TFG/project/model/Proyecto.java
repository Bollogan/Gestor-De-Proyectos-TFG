package org.gestor.de.proyectos.TFG.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.gestor.de.proyectos.TFG.common.model.entities.Version;
import org.gestor.de.proyectos.TFG.common.model.enums.EstadoProyecto;
import org.gestor.de.proyectos.TFG.task.model.Tarea;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin_estimada")
    private LocalDate fechaFinEstimada;

    @Enumerated(EnumType.STRING)
    private EstadoProyecto estado;

    /* Self‑reference para subproyectos */
    @ManyToOne
    @JoinColumn(name = "proyecto_padre_id")
    private Proyecto proyectoPadre;

    @OneToMany(mappedBy = "proyectoPadre")
    private List<Proyecto> subproyectos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "creado_por")
    private Usuario creadoPor;

    /* Otras relaciones */
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarea> tareas = new ArrayList<>();

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Version> versiones = new ArrayList<>();
}
