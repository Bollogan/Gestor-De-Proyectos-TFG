package org.gestor.de.proyectos.TFG.project.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectCreationDTO {

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate estimatedEndDate;
}
