package org.gestor.de.proyectos.TFG.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
    return new OpenAPI()
      .components(new Components()
        .addSecuritySchemes("cookieAuth", new SecurityScheme()
          .type(SecurityScheme.Type.APIKEY)
          .in(SecurityScheme.In.COOKIE)
          .name("SESSION")
        )
      )
      .addSecurityItem(new SecurityRequirement().addList("cookieAuth"));
  }
  
}
