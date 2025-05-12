package com.horrorcore.bankapp;

import com.horrorcore.bankapp.entities.UserProfile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class BankappApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BankappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
