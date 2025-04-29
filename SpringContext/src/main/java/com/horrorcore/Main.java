package com.horrorcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        BankManagement bankManagement = new BankManagement(
//                1,
//                2000,
//                5000,
//                scan);

//        UserRepository userRepository = new UserRepository();
//        UserService userService = new UserService(userRepository);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        UserService userService = context.getBean("userService", UserService.class);

        userService.hello();

        AdminService adminService = context.getBean("adminService", AdminService.class);

        adminService.hello();

        UserRepository userRepository = context.getBean("userRepository", UserRepository.class);

        System.out.println("User count: " + userRepository.getCount());
    }
}