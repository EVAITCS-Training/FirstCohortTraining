package com.horrorcore;

public class AdminService {
    private UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void hello() {
        userRepository.incrementCount();
        System.out.println("Hello from AdminService!");
    }
}
