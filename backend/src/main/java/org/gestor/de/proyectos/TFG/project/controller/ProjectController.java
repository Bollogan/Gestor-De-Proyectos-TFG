package org.gestor.de.proyectos.TFG.project.controller;

import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.project.dto.ProjectCreationDTO;
import org.gestor.de.proyectos.TFG.project.dto.ProjectDTO;
import org.gestor.de.proyectos.TFG.project.mapper.ProjectMapper;
import org.gestor.de.proyectos.TFG.project.service.ProjectService;
import org.gestor.de.proyectos.TFG.user.errors.PermissionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/projects")
@Tag(name = "Projects", description = "Endpoints for managing projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(
        summary = "Get project by ID",
        description = "Retrieves a project by its unique ID."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved project")
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectById(
        @PathVariable Long projectId
    ) throws InstanceNotFoundException {
        return ResponseEntity.ok().body(ProjectMapper.toProjectDTO(this.projectService.getProjectById(projectId)));
    }

    @Operation(
        summary = "Create a new project",
        description = "Retrieves all projects associated with a specific user ID."
    )
    @ApiResponse(responseCode = "201", description = "Successfully created project")
    @PostMapping("")
    public ResponseEntity<ProjectDTO> createProject(
        @RequestBody ProjectCreationDTO project,
        @RequestBody Long userId,
        @RequestBody(required = false) Long parentProjectId
    ) throws InstanceNotFoundException {
        return ResponseEntity.ok().body(ProjectMapper.toProjectDTO(this.projectService.createProject(project, parentProjectId, userId)));
    }

    @Operation(
        summary = "Update a project",
        description = "Retrieves all projects associated with a specific user ID."
    )
    @ApiResponse(responseCode = "200", description = "Successfully updated project")
    @PutMapping("/{projectId}/update")
    public ResponseEntity<ProjectDTO> updateProject(
        @PathVariable Long projectId,
        @RequestBody ProjectCreationDTO project,
        @RequestParam Long userId
    ) throws InstanceNotFoundException, PermissionException {

        return ResponseEntity.ok().body(ProjectMapper.toProjectDTO(this.projectService.updateProject(project, projectId, userId)));
    }
}
