package com.horrorcore.bankapp.config;

import com.horrorcore.bankapp.repositories.UserCredentialRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for setting up application-level beans and security components.
 * This class is part of the Spring Boot framework and is responsible for managing
 * authentication and password encoding configurations.
 */
@Configuration
public class ApplicationConfig {

    private final UserCredentialRepository userCredentialRepository;

    /**
     * Constructor for ApplicationConfig.
     *
     * @param userCredentialRepository Repository for accessing user credentials from the database.
     */
    public ApplicationConfig(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    /**
     * Defines a bean for UserDetailsService, which is used to load user-specific data.
     *
     * @return A UserDetailsService implementation that retrieves user details from the database.
     * @throws UsernameNotFoundException if the user is not found in the database.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userCredentialRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Defines a bean for PasswordEncoder, which is used to encode passwords securely.
     *
     * @return A BCryptPasswordEncoder instance for hashing passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines a bean for AuthenticationProvider, which handles authentication logic.
     *
     * @return A DaoAuthenticationProvider instance configured with UserDetailsService and PasswordEncoder.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Defines a bean for AuthenticationManager, which manages authentication processes.
     *
     * @param authenticationConfiguration The Spring Security configuration for authentication.
     * @return An AuthenticationManager instance.
     * @throws Exception if an error occurs while retrieving the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
