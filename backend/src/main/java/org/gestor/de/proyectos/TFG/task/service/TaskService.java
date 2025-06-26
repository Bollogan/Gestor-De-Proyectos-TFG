package org.gestor.de.proyectos.TFG.task.service;

import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.task.model.Tarea;

public interface TaskService {

    Tarea getTaskById(Long taskId) throws InstanceNotFoundException;
}
