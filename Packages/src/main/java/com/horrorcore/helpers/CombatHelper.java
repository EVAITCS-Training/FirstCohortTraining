package com.horrorcore.helpers;

public class CombatHelper {
    public static int calculateDamage(int attackPower, int defensePower) {
        // Simple damage calculation: damage = attack - defense
        System.out.println("MJM:CombatHelper:l6:calculateDamage: Starting Attack");
        return Math.max(0, attackPower - defensePower);
    }

    public static boolean isCriticalHit(double criticalHitChance) {
        // Check if the hit is a critical hit based on the chance
        return Math.random() < criticalHitChance;
    }

    public static int calculateExperience(int baseExperience, int level) {
        // Experience calculation can be adjusted based on level
        return baseExperience * level;
    }
}
