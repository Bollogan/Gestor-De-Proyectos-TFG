package org.gestor.de.proyectos.TFG.project.repository;

import java.util.List;
import java.util.Optional;
import org.gestor.de.proyectos.TFG.project.model.MiembroProyecto;
import org.gestor.de.proyectos.TFG.project.model.MiembroProyectoId;
import org.gestor.de.proyectos.TFG.project.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends JpaRepository<MiembroProyecto, MiembroProyectoId> {

    Optional<MiembroProyecto> findByUsuarioIdAndProyectoId(Long userId, Long projectId);

    @Query("""
        SELECT mp.proyecto FROM MiembroProyecto mp
        WHERE mp.usuario.id = :userId
    """)
    List<Proyecto> findProjectIdsByUsuarioId(Long userId);
}
