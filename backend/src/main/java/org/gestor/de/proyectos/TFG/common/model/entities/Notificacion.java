package org.gestor.de.proyectos.TFG.common.model.entities;

import java.time.LocalDateTime;
import org.gestor.de.proyectos.TFG.project.model.Proyecto;
import org.gestor.de.proyectos.TFG.task.model.Tarea;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "notificacion")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario destinatario;

    private String tipo;

    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tarea tarea;

    @ManyToOne
    @JoinColumn(name = "emitido_por")
    private Usuario emitidoPor;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private Boolean leido = false;
}
