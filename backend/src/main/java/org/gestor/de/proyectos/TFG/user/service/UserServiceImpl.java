package org.gestor.de.proyectos.TFG.user.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.common.model.services.PermissionChecker;
import org.gestor.de.proyectos.TFG.user.dto.PasswordChangeDTO;
import org.gestor.de.proyectos.TFG.user.dto.UserUpdateDTO;
import org.gestor.de.proyectos.TFG.user.errors.DuplicateInstanceException;
import org.gestor.de.proyectos.TFG.user.errors.IncorrectLoginException;
import org.gestor.de.proyectos.TFG.user.model.Usuario;
import org.gestor.de.proyectos.TFG.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private PermissionChecker permissionChecker;

    public UserServiceImpl(UserRepository userRepository, 
                            PermissionChecker permissionChecker, 
                            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.permissionChecker = permissionChecker;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(Usuario user) throws DuplicateInstanceException {

        if (userRepository.existsByUsuario(user.getUsuario())) {
            throw new DuplicateInstanceException("project.entities.user", user.getUsuario());
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);

    }

    @Override
    @Transactional(readOnly = true)
    public Usuario login(String username, String password) throws IncorrectLoginException {

        Optional<Usuario> user = userRepository.findByUsuario(username);

        if (!user.isPresent()) {
            throw new IncorrectLoginException(username, password);
        }

        if (!passwordEncoder.matches(password, user.get().getPasswordHash())) {
            throw new IncorrectLoginException(username, password);
        }

        return user.get();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario loginFromId(Long id) throws InstanceNotFoundException {
        return permissionChecker.checkUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void updateUserTheme(Long userId, String theme) throws InstanceNotFoundException {
        Usuario user = this.userRepository.findById(userId)
                .orElseThrow(() -> new InstanceNotFoundException("User not found with id: {}", userId));
        user.setTema(theme);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserById(Long userId) {
        Usuario u = userRepository.findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no hallado: " + userId));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        return new User(
            u.getUsuario(),
            u.getPasswordHash(),
            Collections.singletonList(authority)
        );
    }

    @Override
    public Usuario updateProfile(Long userId, UserUpdateDTO dto) throws InstanceNotFoundException {
        Usuario u = userRepository.findById(userId)
            .orElseThrow(() -> new InstanceNotFoundException("Usuario no hallado: {}", userId));
        u.setUsuario(dto.getUser());
        u.setNombre(dto.getName());
        u.setEmail(dto.getEmail());
        return userRepository.save(u);
    }

    @Override
    public void changePassword(Long userId, PasswordChangeDTO dto) throws InstanceNotFoundException, IncorrectLoginException {
        Usuario u = userRepository.findById(userId)
            .orElseThrow(() -> new InstanceNotFoundException("Usuario no hallado: {}", userId));
        if (!passwordEncoder.matches(dto.getCurrentPassword(), u.getPasswordHash())) {
            throw new IncorrectLoginException(u.getUsuario(), dto.getCurrentPassword());
        }
        u.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(u);
    }

    @Override
    public String uploadAvatar(Long userId, MultipartFile file) throws IOException, InstanceNotFoundException {
        Usuario u = userRepository.findById(userId)
            .orElseThrow(() -> new InstanceNotFoundException("Usuario no hallado: {}", userId));
        String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path target = Paths.get("uploads/avatars/").resolve(filename);
        Files.createDirectories(target.getParent());
        file.transferTo(target);
        u.setAvatarUrl(filename);
        userRepository.save(u);
        return filename;
    }
}
