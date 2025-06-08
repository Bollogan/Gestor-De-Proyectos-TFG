package org.gestor.de.proyectos.TFG.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.gestor.de.proyectos.TFG.model.enums.EstadoTarea;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarea")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "puntos_historia")
    private Integer puntosHistoria;

    @Column(name = "esfuerzo_estimado")
    private Integer esfuerzoEstimado;

    @Column(name = "fecha_inicio_plan")
    private LocalDate fechaInicioPlan;

    @Column(name = "fecha_fin_plan")
    private LocalDate fechaFinPlan;

    @Column(name = "fecha_fin_real")
    private LocalDate fechaFinReal;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estado;

    @ManyToOne
    @JoinColumn(name = "version_id")
    private Version version;

    @ManyToOne
    @JoinColumn(name = "responsable_principal_id")
    private Usuario responsablePrincipal;

    /* Asignaciones adicionales */
    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AsignacionTarea> asignaciones = new ArrayList<>();
}
