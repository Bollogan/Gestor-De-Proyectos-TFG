package org.gestor.de.proyectos.TFG.model.entities;

import java.time.LocalDate;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "metrica_chart")
public class MetricaChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "version_id")
    private Version version;

    @Column(name = "pendiente_pts")
    private Integer pendientePts;

    @Column(name = "completado_pts")
    private Integer completadoPts;

    @Column(name = "alcance_pts")
    private Integer alcancePts;

    @Column(name = "fecha_medicion")
    private LocalDate fechaMedicion;
}
