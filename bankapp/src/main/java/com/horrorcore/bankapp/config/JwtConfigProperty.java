package com.horrorcore.bankapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for JWT (JSON Web Token).
 * <p>
 * This class is used to bind properties defined in the application's configuration
 * file (e.g., `application.yml` or `application.properties`) with the prefix `jwt`.
 * It provides access to the JWT secret key and expiration time.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigProperty {

    /**
     * The secret key used for signing and verifying JWTs.
     */
    private String secret;

    /**
     * The expiration time (in milliseconds) for the JWT.
     */
    private long expiration;
}
