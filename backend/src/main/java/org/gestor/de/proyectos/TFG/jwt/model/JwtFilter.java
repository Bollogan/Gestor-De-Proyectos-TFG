package org.gestor.de.proyectos.TFG.jwt.model;

import java.io.IOException;
import org.gestor.de.proyectos.TFG.jwt.service.JwtGenerator;
import org.gestor.de.proyectos.TFG.user.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtGenerator jwtGenerator;
    private final UserService userService;

    public JwtFilter(JwtGenerator jwtGenerator, UserService userService) {
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain chain)
            throws ServletException, IOException {

         String token = null;

        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) {
                if ("SESSION".equals(c.getName())) {
                    token = c.getValue();
                    break;
                }
            }
        }
        
        if (token == null) {
            String authHeader = req.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
        }

        if (token != null) {
            var info = jwtGenerator.getInfo(token);
            if (info != null && info.getUserId() != null) {
                // carga detalles de usuario y seta autenticación
                UserDetails userDetails = userService.loadUserById(info.getUserId());
                var authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        chain.doFilter(req, res);

    }

}
