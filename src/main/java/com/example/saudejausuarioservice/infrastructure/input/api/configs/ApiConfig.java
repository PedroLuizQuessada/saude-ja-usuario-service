package com.example.saudejausuarioservice.infrastructure.input.api.configs;

import com.example.saudejausuarioservice.infrastructure.input.api.security.CustomAccessDeniedHandler;
import com.example.saudejausuarioservice.infrastructure.input.api.security.CustomAuthenticationEntryPoint;
import enums.TipoUsuarioEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("api")
public class ApiConfig {

    private static final String[] WHITE_LIST_URL = { "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs",
            "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html", "/api/auth/**",
            "/api/test/**", "/api/v1/solicitacoes-conta/**" };

    private static final String[] ADMIN_LIST_URL = { "/api/v1/usuarios/*" };

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers(ADMIN_LIST_URL).hasAuthority(TipoUsuarioEnum.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "/api/v1/usuarios").hasAuthority(TipoUsuarioEnum.ADMIN.name())
                                .anyRequest().authenticated())

                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler))

                .oauth2ResourceServer(
                        conf -> conf.jwt(Customizer.withDefaults()));

        return http.build();
    }

}
