package org.openengine.vanilla;

import java.util.*;

public class Team {

    private String name;
    private List<Player> lineup = new ArrayList<>();
    private Map<Position, Player> formation = new TreeMap<>();

    public Team(String name, Tactics tactics) {
        this.name = name;
        initializeFormation(tactics);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    private void initializeFormation(Tactics tactics) {
        switch(tactics) {
            case _4_4_2 -> {
                if (lineup.isEmpty()) { // create dummy players
                    formation.put(Position.GK, new Player(Position.GK, 1));
                    formation.put(Position.D_R, new Player(Position.D_R, 2));
                    formation.put(Position.D_L, new Player(Position.D_L, 3));
                    formation.put(Position.D_CR, new Player(Position.D_CR, 4));
                    formation.put(Position.D_CL, new Player(Position.D_CL, 5));
                    formation.put(Position.M_R, new Player(Position.M_R, 7));
                    formation.put(Position.M_L, new Player(Position.M_L, 11));
                    formation.put(Position.M_CR, new Player(Position.M_CR, 8));
                    formation.put(Position.M_CL, new Player(Position.M_CL, 6));
                    formation.put(Position.F_CR, new Player(Position.F_CR, 9));
                    formation.put(Position.F_CL, new Player(Position.F_CL, 10));
                }
            }
        }
    }

    public Player getGoalkeeper() {
        return this.formation.get(Position.GK);
    }

    public Player getPlayerByPosition(Position position) {
        return formation.get(position);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
