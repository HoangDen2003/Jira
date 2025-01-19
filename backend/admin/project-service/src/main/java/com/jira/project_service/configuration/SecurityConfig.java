package com.jira.project_service.configuration;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS_POST_ADMIN = {"/add/user", "/create"};
    private final String[] PUBLIC_ENDPOINTS_PUT_ADMIN = {"/roles/update/{id}"};
    private final String[] PUBLIC_ENDPOINTS_GET_ADMIN = {"/all", "{projectId}/boards"};
    private final String[] PUBLIC_ENDPOINTS_DELETE = {"/delete/user/{id}"};

    @Value("${jwt.signerKey}")
    private String signerKey;

    //    ProviderJwt providerJwt;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                requests -> requests.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS_POST_ADMIN)
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT, PUBLIC_ENDPOINTS_PUT_ADMIN)
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, PUBLIC_ENDPOINTS_DELETE)
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS_GET_ADMIN)
                        .permitAll()
                        .anyRequest()
                        .authenticated());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // decoder token
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));

        return httpSecurity.build();
    }

    //  Cung cấp một converter để chuyển đổi thông tin quyền từ trong JWT thành các quyền mà Spring Security có thể sử
    // dụng để kiểm tra quyền của người dùng
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix(""); // Chỉ định prefix cho quyền, ví dụ: SYSTEM_ADMIN

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return authenticationConverter;
    }

    // nhận chuỗi token và trả về đối tượng
    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}
