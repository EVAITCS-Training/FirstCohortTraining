package com.horrorcore;

import java.util.HashMap;
import java.util.Map;

public class Skill {
    private Map<String, Box<String, String, String>> skills = new HashMap<>();

    public void addSkill(String name, String description, String type) {
        skills.put(name, new Box<>(name, description, type));
    }

    public Box<String, String, String> getSkill(String name) {
        return skills.get(name);
    }

    public Map<String, Box<String, String, String>> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Box<String, String, String>> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Skill{");
        sb.append("skills=").append(skills);
        sb.append('}');
        return sb.toString();
    }
}
