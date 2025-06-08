package org.gestor.de.proyectos.TFG.model.entities;

import java.time.LocalDateTime;
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
@Table(name = "historial_cambios")
public class HistorialCambios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entidad;

    @Column(name = "entidad_id")
    private Long entidadId;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String accion;

    @Column(columnDefinition = "TEXT")
    private String detalles;

    private LocalDateTime fecha;
}
