package org.gestor.de.proyectos.TFG.project.service;

import java.util.List;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.project.dto.ProjectDTO;
import org.gestor.de.proyectos.TFG.project.model.Proyecto;

public interface ProjectService {

    List<Proyecto> getAllProjectsFromUserId(Long userId);
    
    Proyecto getProjectById(Long projectId) throws InstanceNotFoundException;

    Proyecto createProject(ProjectDTO project);

    Proyecto updateProject(ProjectDTO project, Long projectId) throws InstanceNotFoundException;

    void deleteProject(Long projectId) throws InstanceNotFoundException;

    Proyecto addTaskToProject(Long projectId, Long taskId) throws InstanceNotFoundException;
    
    Proyecto addVersionToProject(Long projectId, Long versionId) throws InstanceNotFoundException;
}
