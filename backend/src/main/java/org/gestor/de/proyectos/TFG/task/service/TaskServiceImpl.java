package org.gestor.de.proyectos.TFG.task.service;

import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.project.model.MiembroProyecto;
import org.gestor.de.proyectos.TFG.project.repository.ProjectMemberRepository;
import org.gestor.de.proyectos.TFG.project.service.ProjectService;
import org.gestor.de.proyectos.TFG.task.dto.TaskCreationDTO;
import org.gestor.de.proyectos.TFG.task.mapper.TaskAssignationMapper;
import org.gestor.de.proyectos.TFG.task.model.Tarea;
import org.gestor.de.proyectos.TFG.task.repository.TaskRepository;
import org.gestor.de.proyectos.TFG.user.errors.PermissionException;
import org.gestor.de.proyectos.TFG.version.service.VersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    private ProjectService projectService;

    private VersionService versionService;

    private ProjectMemberRepository projectMemberRepository;

    public TaskServiceImpl(TaskRepository taskRepository, 
                           ProjectService projectService, 
                           VersionService versionService, 
                           ProjectMemberRepository projectMemberRepository) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.versionService = versionService;
        this.projectMemberRepository = projectMemberRepository;
    }

    @Override
    public Tarea getTaskById(Long taskId) throws InstanceNotFoundException {
        return this.taskRepository.findById(taskId)
                .orElseThrow(() -> new InstanceNotFoundException("Task not found with id: {}", taskId));
    }

    @Override
    public Tarea createTask(TaskCreationDTO task, Long projectId, Long userId) throws InstanceNotFoundException, PermissionException {

        MiembroProyecto projectMember = this.projectMemberRepository.findByUsuarioIdAndProyectoId(userId, projectId)
                .orElseThrow(() -> new InstanceNotFoundException("User not found in project with id: {}", projectId));

        if (!projectMember.getRol().getPermisos().getGestionarTareas()) {
            throw new PermissionException();
        }

        Tarea newTask = new Tarea();
        newTask.setTitulo(task.getTitle());
        newTask.setProyecto(this.projectService.getProjectById(projectId));
        newTask.setDescripcion(task.getDescription());
        newTask.setPuntosHistoria(task.getHistoryPoints());
        newTask.setEsfuerzoEstimado(task.getEstimatedEffort());
        newTask.setFechaInicioPlan(task.getPlannedStartDate());
        newTask.setFechaFinPlan(task.getPlannedEndDate());
        newTask.setFechaFinReal(task.getActualEndDate());
        newTask.setEstado(task.getStatus());
        newTask.setVersion(this.versionService.getVersionById(task.getVersionId()));
        newTask.setAsignaciones(TaskAssignationMapper.toAsignacionTareas(task.getTaskAssignations()));

        newTask = this.taskRepository.save(newTask);
        return newTask;
    }
}
