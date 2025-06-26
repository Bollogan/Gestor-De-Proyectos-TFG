package org.gestor.de.proyectos.TFG.common.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.gestor.de.proyectos.TFG.common.model.enums.EstadoVersion;
import org.gestor.de.proyectos.TFG.project.model.Proyecto;
import org.gestor.de.proyectos.TFG.task.model.Tarea;
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
@Table(name = "version")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Enumerated(EnumType.STRING)
    private EstadoVersion estado;

    @OneToMany(mappedBy = "version")
    private List<Tarea> tareas = new ArrayList<>();
}
