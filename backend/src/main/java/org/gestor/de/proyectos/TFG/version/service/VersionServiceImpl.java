package org.gestor.de.proyectos.TFG.version.service;

import org.gestor.de.proyectos.TFG.common.model.entities.Version;
import org.gestor.de.proyectos.TFG.common.model.exceptions.commons.InstanceNotFoundException;
import org.gestor.de.proyectos.TFG.version.repository.VersionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VersionServiceImpl implements VersionService {

    private VersionRepository versionRepository;

    public VersionServiceImpl(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Version getVersionById(Long versionId) throws InstanceNotFoundException {
        return this.versionRepository.findById(versionId)
                .orElseThrow(() -> new InstanceNotFoundException("Version not found with id: {}", versionId));
    }
}
