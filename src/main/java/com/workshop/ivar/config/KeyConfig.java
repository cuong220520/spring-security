package com.workshop.ivar.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * @author cuongnd9
 * @date 31/03/2023
 * @project ivar
 * @package com.workshop.ivar.config
 */
@Configuration
public class KeyConfig {

    @Value("${jwt.secret:certifiedloverboy}")
    private String jwtSecret;

    @Bean(name = "jwtSigningKey")
    public Key jwtSigningKey() {
        byte[] keyBytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
