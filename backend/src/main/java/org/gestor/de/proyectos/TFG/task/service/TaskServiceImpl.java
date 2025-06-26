package org.gestor.de.proyectos.TFG.task.service;

import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.task.model.Tarea;
import org.gestor.de.proyectos.TFG.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Tarea getTaskById(Long taskId) throws InstanceNotFoundException {
        return this.taskRepository.findById(taskId)
                .orElseThrow(() -> new InstanceNotFoundException("Task not found with id: {}", taskId));
    }
}
