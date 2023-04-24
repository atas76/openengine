package org.openengine.vanilla;

import java.util.*;

public class Team {

    private String name;
    private List<Player> lineup = new ArrayList<>();
    // TODO represent formations in a (5x7) tactics matrix, from which permissible actions and markers can be deduced
    private Map<Position, Player> formation = new TreeMap<>();

    public Team(String name, Tactics tactics) {
        this.name = name;
        initializeFormation(tactics);
        initializeInstructions(tactics);
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

    private void initializeInstructions(Tactics tactics) {
        switch(tactics) {
            case _4_4_2 -> {
                Player goalkeeper = formation.get(Position.GK);
                goalkeeper.setPermissibleActions(Arrays.asList(
                        new Action(goalkeeper, this.formation.get(Position.D_R), ActionType.Pass),
                        new Action(goalkeeper, this.formation.get(Position.D_CR), ActionType.Pass),
                        new Action(goalkeeper, this.formation.get(Position.D_CL), ActionType.Pass),
                        new Action(goalkeeper, this.formation.get(Position.D_L), ActionType.Pass)
                ));

                Player rightBack = formation.get(Position.D_R);
                rightBack.setPermissibleActions(Arrays.asList(
                        new Action(rightBack, this.formation.get(Position.M_R), ActionType.Pass)
                ));

                Player leftBack = formation.get(Position.D_L);
                leftBack.setPermissibleActions(Arrays.asList(
                        new Action(leftBack, this.formation.get(Position.M_L), ActionType.Pass)
                ));

                Player centreRightBack = formation.get(Position.D_CR);
                centreRightBack.setPermissibleActions(Arrays.asList(
                        new Action(centreRightBack, this.formation.get(Position.D_R), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.M_CR), ActionType.Pass)
                ));

                Player centreLeftBack = formation.get(Position.D_CL);
                centreLeftBack.setPermissibleActions(Arrays.asList(
                        new Action(centreLeftBack, this.formation.get(Position.D_L), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.M_CL), ActionType.Pass)
                ));

                Player rightMidfielder = formation.get(Position.M_R);
                rightMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(rightMidfielder, this.formation.get(Position.M_CR), ActionType.Pass)
                ));

                Player leftMidfielder = formation.get(Position.M_L);
                leftMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(leftMidfielder, this.formation.get(Position.M_CL), ActionType.Pass)
                ));

                Player centreRightMidfielder = formation.get(Position.M_CR);
                centreRightMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(centreRightMidfielder, this.formation.get(Position.M_R), ActionType.Pass),
                        new Action(centreRightMidfielder, this.formation.get(Position.F_CR), ActionType.Pass)
                ));

                Player centreLeftMidfielder = formation.get(Position.M_CL);
                centreLeftMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(centreLeftMidfielder, this.formation.get(Position.M_L), ActionType.Pass),
                        new Action(centreLeftMidfielder, this.formation.get(Position.F_CL), ActionType.Pass)
                ));

                Player centreRightForward = formation.get(Position.F_CR);
                centreRightForward.setPermissibleActions(Arrays.asList(
                        new Action(centreRightForward, this.formation.get(Position.F_CL), ActionType.Pass),
                        new Action(centreRightForward, null, ActionType.Shoot)
                ));

                Player centreLeftForward = formation.get(Position.F_CL);
                centreLeftForward.setPermissibleActions(Arrays.asList(
                        new Action(centreLeftForward, this.formation.get(Position.F_CR), ActionType.Pass),
                        new Action(centreLeftForward, null, ActionType.Shoot)
                ));
            }
        }
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