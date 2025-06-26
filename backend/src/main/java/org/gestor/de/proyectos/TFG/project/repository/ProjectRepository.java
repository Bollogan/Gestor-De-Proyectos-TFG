package org.gestor.de.proyectos.TFG.project.repository;

import java.util.List;
import org.gestor.de.proyectos.TFG.project.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Proyecto, Long> {

    @Query("""
        SELECT p FROM Proyecto p
        WHERE p.id IN :ids
        """)
    List<Proyecto> findByIds(List<Long> ids);
}
