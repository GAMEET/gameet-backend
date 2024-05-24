package gameet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gameet.filter.JwtFilter;


@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login").permitAll()  // Permitir acceso al endpoint de login sin autenticación
                .requestMatchers("/auth/registro").permitAll()
                .anyRequest().authenticated()  // Proteger todas las demás rutas
            )
            .addFilterAfter(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    JwtFilter jwtFilter() {
        return new JwtFilter();
    }
}