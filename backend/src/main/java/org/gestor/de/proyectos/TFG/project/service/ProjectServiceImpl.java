package org.gestor.de.proyectos.TFG.project.service;

import java.util.ArrayList;
import java.util.List;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.common.repository.ProjectMemberRepository;
import org.gestor.de.proyectos.TFG.project.dto.ProjectDTO;
import org.gestor.de.proyectos.TFG.project.model.Proyecto;
import org.gestor.de.proyectos.TFG.project.repository.ProjectRepository;
import org.gestor.de.proyectos.TFG.task.dto.TaskDTO;
import org.gestor.de.proyectos.TFG.task.service.TaskService;
import org.gestor.de.proyectos.TFG.user.service.UserService;
import org.gestor.de.proyectos.TFG.version.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private VersionService versionService;
        
    @Override
    public List<Proyecto> getAllProjectsFromUserId(Long userId){

        return this.projectMemberRepository.findProjectIdsByUsuarioId(userId);
    }
    
    @Override
    public Proyecto getProjectById(Long projectId) throws InstanceNotFoundException{
        return this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
    }

    @Override
    public Proyecto createProject(ProjectDTO project){

        Proyecto newProject = new Proyecto();
        newProject.setNombre(project.getName());
        newProject.setDescripcion(project.getDescription());
        newProject.setFechaInicio(project.getStartDate());
        newProject.setFechaFinEstimada(project.getEstimatedEndDate());
        newProject.setEstado(project.getStatus());
        newProject.setProyectoPadre(this.projectRepository.findById(project.getParentProject().getId())
                .orElse(null));
        newProject.setCreadoPor(this.userService.findById(project.getCreatedBy().getId()));

        newProject = this.projectRepository.save(newProject);
        return newProject;
    }

    @Override
    public Proyecto updateProject(ProjectDTO project, Long projectId) throws InstanceNotFoundException{
        Proyecto existingProject = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
        existingProject.setNombre(project.getName());
        existingProject.setDescripcion(project.getDescription());
        existingProject.setFechaInicio(project.getStartDate());
        existingProject.setFechaFinEstimada(project.getEstimatedEndDate());
        existingProject.setEstado(project.getStatus());
        existingProject.setProyectoPadre(this.projectRepository.findById(project.getParentProject().getId())
                .orElse(null));
        existingProject.setCreadoPor(this.userService.findById(project.getCreatedBy().getId()));
        List<Long> taskIds = new ArrayList<>();
        for (TaskDTO task : project.getTasks()) {
            taskIds.add(task.getId());
        }

        existingProject = this.projectRepository.save(existingProject);
        return existingProject;
    }

    @Override
    public void deleteProject(Long projectId) throws InstanceNotFoundException{

        Proyecto project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
        this.projectRepository.delete(project);
    }

    @Override
    public Proyecto addTaskToProject(Long projectId, Long taskId) throws InstanceNotFoundException {
        Proyecto project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
        project.getTareas().add(this.taskService.getTaskById(taskId));
        project = this.projectRepository.save(project);
        return project;
    }

    @Override
    public Proyecto addVersionToProject(Long projectId, Long versionId) throws InstanceNotFoundException {
        Proyecto project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
        project.getVersiones().add(this.versionService.getVersionById(versionId));
        project = this.projectRepository.save(project);
        return project;
    }
}
