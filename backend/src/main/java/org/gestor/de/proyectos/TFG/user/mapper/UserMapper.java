package org.gestor.de.proyectos.TFG.user.mapper;

import java.util.List;
import org.gestor.de.proyectos.TFG.user.dto.AuthenticatedUserDTO;
import org.gestor.de.proyectos.TFG.user.dto.UserDTO;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserMapper {

    public static final UserDTO toUserDTO(Usuario u) {
        return new UserDTO(u.getId(), u.getNombre(), u.getEmail(), u.getNombre(), u.getEmail(), u.getActivo(), u.getTema());
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
            userDTO.getActive(),
            userDTO.getTheme()
        );
    }

    public static final List<Usuario> toUsuarios(List<UserDTO> userDTOs) {
        return userDTOs.stream().map(UserMapper::toUsuario).toList();
    }

    public static final AuthenticatedUserDTO toAuthenticatedUserDto(String serviceToken, Usuario user) {

        return new AuthenticatedUserDTO(serviceToken, UserMapper.toUserDTO(user));

    }
}
