package org.gestor.de.proyectos.TFG.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.project.dto.ProjectDTO;
import org.gestor.de.proyectos.TFG.project.dto.ProjectOverviewDTO;
import org.gestor.de.proyectos.TFG.project.mapper.ProjectMapper;
import org.gestor.de.proyectos.TFG.project.service.ProjectService;
import org.gestor.de.proyectos.TFG.user.dto.PasswordChangeDTO;
import org.gestor.de.proyectos.TFG.user.dto.UserDTO;
import org.gestor.de.proyectos.TFG.user.dto.UserUpdateDTO;
import org.gestor.de.proyectos.TFG.user.errors.IncorrectLoginException;
import org.gestor.de.proyectos.TFG.user.mapper.UserMapper;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import org.gestor.de.proyectos.TFG.user.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
      summary = "Get user by ID",
      description = "Retrieves the username, full name, email and avatar URL for the given user ID."
    )
    @ApiResponses({
      @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
      @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(
        @Parameter(in = ParameterIn.PATH, description = "ID of the user to fetch", required = true)
        @PathVariable Long userId
    ) {
        Usuario u = userService.findById(userId);
        return ResponseEntity.ok(UserMapper.toUserDTO(u));
    }

    @Operation(
      summary = "Update user profile",
      description = "Updates the username, full name and email for the specified user."
    )
    @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
      @ApiResponse(responseCode = "404", description = "User not found"),
      @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping(
      value = "/{userId}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDTO> updateProfile(
        @Parameter(in = ParameterIn.PATH, description = "ID of the user to update", required = true)
        @PathVariable Long userId,
        @RequestBody UserUpdateDTO dto
    ) throws InstanceNotFoundException {
        Usuario updated = userService.updateProfile(userId, dto);
        return ResponseEntity.ok(UserMapper.toUserDTO(updated));
    }

    @Operation(
      summary = "Change user password",
      description = "Changes the password for the given user. Requires current and new password."
    )
    @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Password changed successfully"),
      @ApiResponse(responseCode = "404", description = "User not found"),
      @ApiResponse(responseCode = "400", description = "Current password is incorrect")
    })
    @PostMapping(
      value = "/{userId}/password",
      consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> changePassword(
        @Parameter(in = ParameterIn.PATH, description = "ID of the user whose password to change", required = true)
        @PathVariable Long userId,
        @RequestBody PasswordChangeDTO dto
    ) throws InstanceNotFoundException, IncorrectLoginException {
        userService.changePassword(userId, dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(
      summary = "Upload or update user avatar",
      description = "Uploads a new avatar image for the user. Returns 200 and a Content-Location header pointing to the new avatar URL."
    )
    @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Avatar uploaded successfully"),
      @ApiResponse(responseCode = "404", description = "User not found"),
      @ApiResponse(responseCode = "415", description = "Unsupported media type")
    })
    @PostMapping(
      value = "/{userId}/avatar",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> uploadAvatar(
        @Parameter(in = ParameterIn.PATH, description = "ID of the user whose avatar to upload", required = true)
        @PathVariable Long userId,
        @RequestPart("file") MultipartFile file
    ) throws IOException, InstanceNotFoundException {
        String filename = userService.uploadAvatar(userId, file);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_LOCATION, "/users/avatar/" + filename)
            .build();
    }
}
