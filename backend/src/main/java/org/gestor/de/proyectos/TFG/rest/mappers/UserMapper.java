package org.gestor.de.proyectos.TFG.rest.mappers;

import java.util.List;
import org.gestor.de.proyectos.TFG.model.entities.Usuario;
import org.gestor.de.proyectos.TFG.rest.dtos.AuthenticatedUserDTO;
import org.gestor.de.proyectos.TFG.rest.dtos.UserDTO;

public class UserMapper {

    public UserMapper() {}

    public static final UserDTO toUserDTO(Usuario u) {
        return new UserDTO(u.getId(), u.getNombre(), u.getEmail(), u.getNombre(), u.getEmail(), u.getActivo());
    }

    public static final List<UserDTO> toUserDTOs(List<Usuario> usuarios) {
        return usuarios.stream().map(UserMapper::toUserDTO).toList();
    }

    public static final Usuario toUsuario(UserDTO userDTO) {
        return new Usuario(
            userDTO.getId(),
            userDTO.getUsername(),
            userDTO.getFirstName(),
            userDTO.getPassword(),
            userDTO.getEmail(),
            userDTO.getActive()
        );
    }

    public static final List<Usuario> toUsuarios(List<UserDTO> userDTOs) {
        return userDTOs.stream().map(UserMapper::toUsuario).toList();
    }

    public static final AuthenticatedUserDTO toAuthenticatedUserDto(String serviceToken, Usuario user) {

        return new AuthenticatedUserDTO(serviceToken, UserMapper.toUserDTO(user));

    }
}
