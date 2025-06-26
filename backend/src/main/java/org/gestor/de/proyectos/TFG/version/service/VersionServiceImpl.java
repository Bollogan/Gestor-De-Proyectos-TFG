package org.gestor.de.proyectos.TFG.version.service;

import org.gestor.de.proyectos.TFG.common.model.entities.Version;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.version.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class VersionServiceImpl implements VersionService {

    @Autowired
    private VersionRepository versionRepository;

    @Override
    public Version getVersionById(Long versionId) throws InstanceNotFoundException {
        return this.versionRepository.findById(versionId)
                .orElseThrow(() -> new InstanceNotFoundException("Version not found with id: {}", versionId));
    }
}
