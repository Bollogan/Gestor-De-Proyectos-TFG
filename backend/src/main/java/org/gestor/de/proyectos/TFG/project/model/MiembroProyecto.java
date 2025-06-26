package org.gestor.de.proyectos.TFG.project.model;

import java.time.LocalDateTime;
import org.gestor.de.proyectos.TFG.common.model.entities.Rol;
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
@Table(name = "miembro_proyecto")
public class MiembroProyecto {

    @EmbeddedId
    private final MiembroProyectoId id = new MiembroProyectoId();

    @MapsId("usuarioId")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @MapsId("proyectoId")
    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion;
}
