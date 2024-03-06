package org.openengine.pureengine;

import java.util.Objects;

public class Team {
    private String name;

    private int skill;

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, int skill) {
        this.name = name;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public int getSkill() {
        return skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
