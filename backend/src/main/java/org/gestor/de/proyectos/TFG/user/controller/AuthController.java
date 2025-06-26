package org.gestor.de.proyectos.TFG.user.controller;

import java.net.URI;
import java.time.Duration;
import java.util.Locale;
import org.gestor.de.proyectos.TFG.common.model.exceptions.DuplicateInstanceException;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.common.utils.ErrorsDTO;
import org.gestor.de.proyectos.TFG.jwt.model.JwtInfo;
import org.gestor.de.proyectos.TFG.jwt.service.JwtGenerator;
import org.gestor.de.proyectos.TFG.user.dto.AuthenticatedUserDTO;
import org.gestor.de.proyectos.TFG.user.dto.LoginParamsDTO;
import org.gestor.de.proyectos.TFG.user.dto.UserDTO;
import org.gestor.de.proyectos.TFG.user.errors.IncorrectLoginException;
import org.gestor.de.proyectos.TFG.user.mapper.UserMapper;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import org.gestor.de.proyectos.TFG.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication endpoints for user sign up and login")
public class AuthController {

    private static final String INCORRECT_LOGIN_EXCEPTION_CODE = "project.exceptions.IncorrectLoginException";

    private UserService userService;

    private JwtGenerator jwtGenerator;

    private MessageSource messageSource;

    public AuthController(UserService userService, JwtGenerator jwtGenerator, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @ExceptionHandler(IncorrectLoginException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDTO handleIncorrectLoginException(IncorrectLoginException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INCORRECT_LOGIN_EXCEPTION_CODE, null,
                INCORRECT_LOGIN_EXCEPTION_CODE, locale);

        return new ErrorsDTO(errorMessage);

    }

    @Operation(
        summary = "Sign up a new user",
        description = "Creates a new user account with the provided user details. Returns the created user's information."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request, possibly due to duplicate user or validation errors"),
    })
    @PostMapping("/signUp")
    public ResponseEntity<AuthenticatedUserDTO> signUp(@RequestBody UserDTO userDTO) throws DuplicateInstanceException {
        
        Usuario user = UserMapper.toUsuario(userDTO);

        userService.signUp(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(UserMapper.toAuthenticatedUserDto(generateServiceToken(user), user));
    }

    @Operation(
        summary = "User login",
        description = "Authenticates a user with the provided credentials and returns an authentication token."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User logged in successfully"),
        @ApiResponse(responseCode = "404", description = "Unauthorized, invalid credentials")
    })
    @PostMapping("/logIn")
    public ResponseEntity<AuthenticatedUserDTO> login(@RequestBody LoginParamsDTO params, HttpServletResponse response) throws IncorrectLoginException {

        Usuario user = userService.login(params.getUserName(), params.getPassword());

        String token = generateServiceToken(user);

        ResponseCookie cookie = ResponseCookie.from("SESSION", token)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(Duration.ofHours(2))
            .sameSite("None")
            .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        AuthenticatedUserDTO dto = UserMapper.toAuthenticatedUserDto(token, user);
        dto.setServiceToken(null);
        return ResponseEntity.ok(dto);
    }
    
    @Operation(
        summary = "Get authenticated user details",
        description = "Retrieves the details of the currently authenticated user based on the provided session token."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Authenticated user details retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Unauthorized, no valid session token provided")
    })
    @GetMapping("/me")
    public AuthenticatedUserDTO me(@CookieValue(name = "SESSION", required = false) String token) throws InstanceNotFoundException {
        if (token == null || token.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No autenticado");
        }

        JwtInfo info = jwtGenerator.getInfo(token);
        if (info == null || info.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido o expirado");
        }

        Usuario user = userService.loginFromId(info.getUserId());
        return UserMapper.toAuthenticatedUserDto(token, user);
    }

    @Operation(
        summary = "User logout",
        description = "Logs out the user by clearing the session token."
    )
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie deleteCookie = ResponseCookie.from("SESSION", "")
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(0)
            .sameSite("None")
            .build();

        response.setHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());
        return ResponseEntity.ok().build();
    }

    private String generateServiceToken(Usuario user) {

        JwtInfo jwtInfo = new JwtInfo(user.getId(), user.getUsuario());

        return jwtGenerator.generate(jwtInfo);
    }

}
