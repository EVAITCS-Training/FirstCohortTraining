package com.horrorcore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // Define your beans and configurations here
    // For example, you can define a bean for UserService
    // @Bean
    // public UserService userService() {
    //     return new UserService(userRepository());
    // }

    // You can also define other beans as needed

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }
}
