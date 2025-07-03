package org.gestor.de.proyectos.TFG.user.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.gestor.de.proyectos.TFG.project.model.Proyecto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    private Boolean activo = true;

    @OneToMany(mappedBy = "creadoPor")
    private List<Proyecto> proyectosCreados = new ArrayList<>();

    private String tema = "default";

    @Column(name = "avatar_url")
    private String avatarUrl;

    public Usuario(Long id, String usuario, String nombre, String password, String email, Boolean activo, String tema) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = password;
        this.activo = activo;
        this.tema = tema;
    }
}
