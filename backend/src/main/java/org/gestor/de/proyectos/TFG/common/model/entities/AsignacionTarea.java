package org.gestor.de.proyectos.TFG.common.model.entities;

import java.time.LocalDateTime;
import org.gestor.de.proyectos.TFG.task.model.Tarea;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "asignacion_tarea")
public class AsignacionTarea {
    @EmbeddedId
    private AsignacionTareaId id = new AsignacionTareaId();

    @MapsId(value = "tareaId")
    @ManyToOne
    @JoinColumn(name = "tareaId")
    private Tarea tarea;

    @MapsId(value = "usuarioId")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion;

    @Column(name = "rol_en_tarea")
    private String rolEnTarea;
}
