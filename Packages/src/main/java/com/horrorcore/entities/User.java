package com.horrorcore.entities;

public class User {
    private String email;
    private String password;
    private String username;
    private String profilePictureUrl;
    private String bio;
    private int level;
    private int experiencePoints;
    private int healthPoints;
    private int attackPower;
    private int defensePower;

    public User() {}

    public User(String email, String password, String username, String profilePictureUrl, String bio, int level, int experiencePoints, int healthPoints, int attackPower, int defensePower) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.profilePictureUrl = profilePictureUrl;
        this.bio = bio;
        this.level = level;
        this.experiencePoints = experiencePoints;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }
}
