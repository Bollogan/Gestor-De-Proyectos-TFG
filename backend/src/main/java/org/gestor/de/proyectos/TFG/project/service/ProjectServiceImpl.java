package org.gestor.de.proyectos.TFG.project.service;

import java.util.List;
import org.gestor.de.proyectos.TFG.common.model.enums.EstadoProyecto;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.project.dto.ProjectCreationDTO;
import org.gestor.de.proyectos.TFG.project.errors.UserNotInProjectException;
import org.gestor.de.proyectos.TFG.project.model.MiembroProyecto;
import org.gestor.de.proyectos.TFG.project.model.Proyecto;
import org.gestor.de.proyectos.TFG.project.repository.ProjectMemberRepository;
import org.gestor.de.proyectos.TFG.project.repository.ProjectRepository;
import org.gestor.de.proyectos.TFG.task.repository.TaskRepository;
import org.gestor.de.proyectos.TFG.user.errors.PermissionException;
import org.gestor.de.proyectos.TFG.user.repository.UserRepository;
import org.gestor.de.proyectos.TFG.version.repository.VersionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    private ProjectMemberRepository projectMemberRepository;

    private UserRepository userRepository;

    private TaskRepository taskRepository;

    private VersionRepository versionRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, 
                               ProjectMemberRepository projectMemberRepository, 
                               UserRepository userRepository, 
                               TaskRepository taskRepository, 
                               VersionRepository versionRepository) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.versionRepository = versionRepository;
    }
        
    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> getAllProjectsFromUserId(Long userId){

        return this.projectMemberRepository.findProjectIdsByUsuarioId(userId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Proyecto getProjectById(Long projectId) throws InstanceNotFoundException{
        return this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
    }

    @Override
    @Transactional
    public Proyecto createProject(ProjectCreationDTO project, Long parentProjectId, Long userId) throws InstanceNotFoundException{
        Proyecto newProject = new Proyecto();
        newProject.setNombre(project.getName());
        newProject.setDescripcion(project.getDescription());
        newProject.setFechaInicio(project.getStartDate());
        newProject.setFechaFinEstimada(project.getEstimatedEndDate());
        newProject.setEstado(EstadoProyecto.EN_ESPERA);
        newProject.setProyectoPadre(this.projectRepository.findById(parentProjectId)
                .orElse(null));
        newProject.setCreadoPor(this.userRepository.findById(userId).orElseThrow(() -> new InstanceNotFoundException("User not found with id: {}", userId)));

        newProject = this.projectRepository.save(newProject);
        return newProject;
    }

    @Override
    @Transactional
    public Proyecto updateProject(ProjectCreationDTO project, Long projectId, Long userId) throws InstanceNotFoundException, PermissionException{
        
        Proyecto existingProject = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
        
        if(!existingProject.getCreadoPor().getId().equals(userId)) {
            throw new PermissionException();
        }

        MiembroProyecto member = this.projectMemberRepository.findByUsuarioIdAndProyectoId(userId, projectId)
                .orElseThrow(() -> new UserNotInProjectException());

        if (!member.getRol().getPermisos().getAdministrarProyecto()){
            throw new PermissionException();
        }
        
        existingProject.setNombre(project.getName());
        existingProject.setDescripcion(project.getDescription());
        existingProject.setFechaInicio(project.getStartDate());
        existingProject.setFechaFinEstimada(project.getEstimatedEndDate());
 
        existingProject = this.projectRepository.save(existingProject);
        return existingProject;
    }

    @Override
    @Transactional
    public Proyecto changeProjectStatus(Long projectId, EstadoProyecto status) throws InstanceNotFoundException {
        Proyecto project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
        project.setEstado(status);
        project = this.projectRepository.save(project);
        return project;
    }
    
    @Override
    @Transactional
    public void deleteProject(Long projectId) throws InstanceNotFoundException{

        Proyecto project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
        this.projectRepository.delete(project);
    }

    @Override
    @Transactional
    public Proyecto addTaskToProject(Long projectId, Long taskId) throws InstanceNotFoundException {
        Proyecto project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
        project.getTareas().add(this.taskRepository.findById(taskId).orElseThrow(() -> new InstanceNotFoundException("Task not found with id: {}", taskId)));
        project = this.projectRepository.save(project);
        return project;
    }

    @Override
    @Transactional
    public Proyecto addVersionToProject(Long projectId, Long versionId) throws InstanceNotFoundException {
        Proyecto project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new InstanceNotFoundException("Project not found with id: {}", projectId));
        project.getVersiones().add(this.versionRepository.findById(versionId)
                            .orElseThrow(() -> new InstanceNotFoundException("Version not found with id: {}", versionId)));
        project = this.projectRepository.save(project);
        return project;
    }
}
