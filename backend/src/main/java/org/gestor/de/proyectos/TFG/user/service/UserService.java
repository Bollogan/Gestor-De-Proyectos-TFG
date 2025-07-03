package org.gestor.de.proyectos.TFG.user.service;

import java.io.IOException;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.user.dto.PasswordChangeDTO;
import org.gestor.de.proyectos.TFG.user.dto.UserUpdateDTO;
import org.gestor.de.proyectos.TFG.user.errors.DuplicateInstanceException;
import org.gestor.de.proyectos.TFG.user.errors.IncorrectLoginException;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    void signUp(Usuario user) throws DuplicateInstanceException;

    Usuario login(String userName, String password) throws IncorrectLoginException;

    Usuario loginFromId(Long id) throws InstanceNotFoundException;

    Usuario findById(Long id);
    
    void updateUserTheme(Long userId, String theme) throws InstanceNotFoundException;

    UserDetails loadUserById(Long userId);

    Usuario updateProfile(Long userId, UserUpdateDTO dto) throws InstanceNotFoundException;

    void changePassword(Long userId, PasswordChangeDTO dto) throws InstanceNotFoundException, IncorrectLoginException;

    String uploadAvatar(Long userId, MultipartFile file) throws IOException, InstanceNotFoundException;
}
