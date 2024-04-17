package org.openengine.pureengine;

import java.util.Objects;

public class Team {

    private String name;
    private String fullName;

    private int skill;

    public Team(String name) {
        this.fullName = name;
    }

    public Team(String name, int skill) {
        this.fullName = name;
        this.skill = skill;
    }

    public Team(String name, String fullName, int skill) {
        this(fullName, skill);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public int getSkill() {
        return skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(fullName, team.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + fullName + '\'' +
                ", skill=" + skill +
                '}';
    }
}
