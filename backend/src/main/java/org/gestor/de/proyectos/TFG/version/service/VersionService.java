package org.gestor.de.proyectos.TFG.version.service;

import org.gestor.de.proyectos.TFG.common.model.entities.Version;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;

public interface VersionService {

    Version getVersionById(Long versionId) throws InstanceNotFoundException;
}
