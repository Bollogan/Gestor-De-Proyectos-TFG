package org.gestor.de.proyectos.TFG.user.controller;

import java.util.List;
import java.util.Map;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.project.dto.ProjectDTO;
import org.gestor.de.proyectos.TFG.project.dto.ProjectOverviewDTO;
import org.gestor.de.proyectos.TFG.project.mapper.ProjectMapper;
import org.gestor.de.proyectos.TFG.project.service.ProjectService;
import org.gestor.de.proyectos.TFG.user.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints for managing users")
public class UserController {

    private ProjectService projectService;

    private UserService userService;

    public UserController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @Operation(
        summary = "Get all projects for a user",
        description = "Retrieves all projects associated with a specific user ID."
    )
    @ApiResponse(responseCode = "200",description = "Successfully retrieved projects")
    @GetMapping("/{userId}/projects")
    public ResponseEntity<List<ProjectOverviewDTO>> getUserProjects(
        @PathVariable Long userId
        ) {
            return ResponseEntity.ok().body(ProjectMapper.toProjectOverviewDTOs(this.projectService.getAllProjectsFromUserId(userId)));
    }

    @Operation(
        summary = "Set theme for user",
        description = "Sets the theme for a user based on their ID."
    )
    @ApiResponse(responseCode = "200", description = "Successfully set user theme")
    @PutMapping(
        value ="/{userId}/theme",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setUserTheme(
        @PathVariable Long userId,
        @RequestBody Map<String,String> body
    ) throws InstanceNotFoundException {
        this.userService.updateUserTheme(userId, body.get("theme"));
        return ResponseEntity.noContent().build();
    }
}
