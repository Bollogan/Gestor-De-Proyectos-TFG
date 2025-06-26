package org.gestor.de.proyectos.TFG.jwt.service;

import org.gestor.de.proyectos.TFG.jwt.model.JwtInfo;

public interface JwtGenerator {
    String generate(JwtInfo info);

    JwtInfo getInfo(String token);
}
