package com.horrorcore.entities;

public class Demon {
    private String name;
    private String description;
    private String imageUrl;
    private int healthPoints;
    private int attackPower;
    private int defensePower;
    private boolean isBoss;

    public Demon() {}

    public Demon(String name, String description, String imageUrl, int healthPoints, int attackPower, int defensePower, boolean isBoss) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.isBoss = isBoss;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public int getAttackPower() {
        return attackPower;
    }
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
    public int getDefensePower() {
        return defensePower;
    }
    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }
    public boolean isBoss() {
        return isBoss;
    }
    public void setIsBoss(boolean isBoss) {
        this.isBoss = isBoss;
    }
}
