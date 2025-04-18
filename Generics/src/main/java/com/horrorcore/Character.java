package com.horrorcore;

import java.util.Objects;

public class Character<T, K, V> {
    private T race;
    private K stats;
    private V skills;

    public Character(T race, K stats, V skills) {
        this.race = race;
        this.stats = stats;
        this.skills = skills;
    }

    public T race() {
        return race;
    }

    public void setRace(T race) {
        this.race = race;
    }

    public K stats() {
        return stats;
    }

    public void setStats(K stats) {
        this.stats = stats;
    }

    public V skills() {
        return skills;
    }

    public void setSkills(V skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Character<?, ?, ?> character)) return false;
        return Objects.equals(race, character.race) && Objects.equals(stats, character.stats) && Objects.equals(skills, character.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(race, stats, skills);
    }
}
