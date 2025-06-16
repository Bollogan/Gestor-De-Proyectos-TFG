package org.gestor.de.proyectos.TFG.rest.controllers;

import java.net.URI;
import java.util.Locale;
import org.gestor.de.proyectos.TFG.model.entities.Usuario;
import org.gestor.de.proyectos.TFG.model.exceptions.DuplicateInstanceException;
import org.gestor.de.proyectos.TFG.model.exceptions.IncorrectLoginException;
import org.gestor.de.proyectos.TFG.model.services.UserService;
import org.gestor.de.proyectos.TFG.rest.common.ErrorsDTO;
import org.gestor.de.proyectos.TFG.rest.common.JwtGenerator;
import org.gestor.de.proyectos.TFG.rest.common.JwtInfo;
import org.gestor.de.proyectos.TFG.rest.dtos.AuthenticatedUserDTO;
import org.gestor.de.proyectos.TFG.rest.dtos.LoginParamsDTO;
import org.gestor.de.proyectos.TFG.rest.dtos.UserDTO;
import org.gestor.de.proyectos.TFG.rest.mappers.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
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

    @PostMapping("/signup")
    public ResponseEntity<AuthenticatedUserDTO> signUp(@RequestBody @Validated UserDTO userDTO) throws DuplicateInstanceException {
        
        Usuario user = UserMapper.toUsuario(userDTO);

        userService.signUp(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(UserMapper.toAuthenticatedUserDto(generateServiceToken(user), user));
    }

    @PostMapping("/login")
    public AuthenticatedUserDTO login(@RequestBody @Validated LoginParamsDTO params) throws IncorrectLoginException {

        Usuario user = userService.login(params.getUserName(), params.getPassword());

        return UserMapper.toAuthenticatedUserDto(generateServiceToken(user), user);
    }
    
    private String generateServiceToken(Usuario user) {

        JwtInfo jwtInfo = new JwtInfo(user.getId(), user.getUsuario());

        return jwtGenerator.generate(jwtInfo);
    }

}
