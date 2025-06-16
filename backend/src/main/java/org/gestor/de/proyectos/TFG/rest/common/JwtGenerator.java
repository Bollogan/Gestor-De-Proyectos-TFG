package org.gestor.de.proyectos.TFG.rest.common;

public interface JwtGenerator {
    String generate(JwtInfo info);

    JwtInfo getInfo(String token);
}
