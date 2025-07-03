package org.gestor.de.proyectos.TFG.task.service;

import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.task.dto.TaskCreationDTO;
import org.gestor.de.proyectos.TFG.task.model.Tarea;
import org.gestor.de.proyectos.TFG.user.errors.PermissionException;

public interface TaskService {

    Tarea getTaskById(Long taskId) throws InstanceNotFoundException;

    Tarea createTask(TaskCreationDTO task, Long projectId, Long userId) throws InstanceNotFoundException, PermissionException;
}
