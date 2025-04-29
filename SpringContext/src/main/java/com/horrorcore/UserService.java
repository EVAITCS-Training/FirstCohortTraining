package com.horrorcore;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void hello() {
        userRepository.incrementCount();
        System.out.println("Hello from UserService!");
    }
}
