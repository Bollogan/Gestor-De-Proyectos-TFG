package org.gestor.de.proyectos.TFG.version.repository;

import org.gestor.de.proyectos.TFG.common.model.entities.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {
    
}
