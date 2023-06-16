package org.openengine.vanilla;

import java.util.*;

public class Team {

    private String name;
    private List<Player> lineup = new ArrayList<>();
    // TODO represent formations in a (5x7) tactics matrix, from which permissible actions and markers can be deduced
    private Map<Position, Player> formation = new TreeMap<>();
    private Tactics tactics;

    public Team(String name, Tactics tactics) {
        this.name = name;
        initializeFormation(tactics);
        initializeInstructions(tactics);
        this.tactics = tactics;
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
                        new Action(goalkeeper, this.formation.get(Position.D_L), ActionType.Pass),
                        new Action(goalkeeper, this.formation.get(Position.M_R), ActionType.Pass, 2.0),
                        new Action(goalkeeper, this.formation.get(Position.M_CR), ActionType.Pass, 2.0),
                        new Action(goalkeeper, this.formation.get(Position.M_CL), ActionType.Pass, 2.0),
                        new Action(goalkeeper, this.formation.get(Position.M_L), ActionType.Pass, 2.0),
                        new Action(goalkeeper, this.formation.get(Position.F_CR), ActionType.Pass, 4.0),
                        new Action(goalkeeper, this.formation.get(Position.F_CL), ActionType.Pass, 4.0)
                ));

                Player rightBack = formation.get(Position.D_R);
                rightBack.setPermissibleActions(Arrays.asList(
                        new Action(rightBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(rightBack, this.formation.get(Position.D_CR), ActionType.Pass),
                        new Action(rightBack, this.formation.get(Position.M_R), ActionType.Pass),
                        new Action(rightBack, this.formation.get(Position.M_CR), ActionType.Pass, 1.4),
                        new Action(rightBack, this.formation.get(Position.F_CR), ActionType.Pass, 2.0 * 1.4)
                ));

                Player leftBack = formation.get(Position.D_L);
                leftBack.setPermissibleActions(Arrays.asList(
                        new Action(leftBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(leftBack, this.formation.get(Position.D_CL), ActionType.Pass),
                        new Action(leftBack, this.formation.get(Position.M_L), ActionType.Pass),
                        new Action(rightBack, this.formation.get(Position.M_CL), ActionType.Pass, 1.4),
                        new Action(rightBack, this.formation.get(Position.F_CL), ActionType.Pass, 2.0 * 1.4)
                ));

                Player centreRightBack = formation.get(Position.D_CR);
                centreRightBack.setPermissibleActions(Arrays.asList(
                        new Action(centreRightBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.D_R), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.D_CL), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.M_CR), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.M_R), ActionType.Pass, 1.4),
                        new Action(centreRightBack, this.formation.get(Position.F_CR), ActionType.Pass, 2.0)
                ));

                Player centreLeftBack = formation.get(Position.D_CL);
                centreLeftBack.setPermissibleActions(Arrays.asList(
                        new Action(centreLeftBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.D_L), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.D_CR), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.M_CL), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.M_L), ActionType.Pass, 1.4),
                        new Action(centreRightBack, this.formation.get(Position.F_CL), ActionType.Pass, 2.0)
                ));

                Player rightMidfielder = formation.get(Position.M_R);
                rightMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(rightMidfielder, this.formation.get(Position.M_CR), ActionType.Pass),
                        new Action(rightMidfielder, this.formation.get(Position.F_CR), ActionType.Pass, 1.4)
                ));

                Player leftMidfielder = formation.get(Position.M_L);
                leftMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(leftMidfielder, this.formation.get(Position.M_CL), ActionType.Pass),
                        new Action(rightMidfielder, this.formation.get(Position.F_CL), ActionType.Pass, 1.4)
                ));

                Player centreRightMidfielder = formation.get(Position.M_CR);
                centreRightMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(centreRightMidfielder, this.formation.get(Position.M_R), ActionType.Pass),
                        new Action(centreRightMidfielder, this.formation.get(Position.M_CL), ActionType.Pass),
                        new Action(centreRightMidfielder, this.formation.get(Position.F_CR), ActionType.Pass)
                ));

                Player centreLeftMidfielder = formation.get(Position.M_CL);
                centreLeftMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(centreLeftMidfielder, this.formation.get(Position.M_L), ActionType.Pass),
                        new Action(centreLeftMidfielder, this.formation.get(Position.M_CR), ActionType.Pass),
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
            case _4_3_3 -> {
                Player goalkeeper = formation.get(Position.GK);
                goalkeeper.setPermissibleActions(Arrays.asList(
                        new Action(goalkeeper, this.formation.get(Position.D_R), ActionType.Pass),
                        new Action(goalkeeper, this.formation.get(Position.D_CR), ActionType.Pass),
                        new Action(goalkeeper, this.formation.get(Position.D_CL), ActionType.Pass),
                        new Action(goalkeeper, this.formation.get(Position.D_L), ActionType.Pass),
                        new Action(goalkeeper, this.formation.get(Position.M_RC), ActionType.Pass, 2.0),
                        new Action(goalkeeper, this.formation.get(Position.M_C), ActionType.Pass, 2.0),
                        new Action(goalkeeper, this.formation.get(Position.M_LC), ActionType.Pass, 2.0),
                        new Action(goalkeeper, this.formation.get(Position.F_RC), ActionType.Pass, 3.0),
                        new Action(goalkeeper, this.formation.get(Position.F_C), ActionType.Pass, 3.0),
                        new Action(goalkeeper, this.formation.get(Position.F_LC), ActionType.Pass, 3.0)
                ));

                Player rightBack = formation.get(Position.D_R);
                rightBack.setPermissibleActions(Arrays.asList(
                        new Action(rightBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(rightBack, this.formation.get(Position.D_CR), ActionType.Pass),
                        new Action(rightBack, this.formation.get(Position.M_RC), ActionType.Pass, 1.4),
                        new Action(rightBack, this.formation.get(Position.M_C), ActionType.Pass, 2.1),
                        new Action(rightBack, this.formation.get(Position.F_RC), ActionType.Pass, 2 * 1.4),
                        new Action(rightBack, this.formation.get(Position.F_C), ActionType.Pass, 2 * 2.1)
                ));

                Player leftBack = formation.get(Position.D_L);
                leftBack.setPermissibleActions(Arrays.asList(
                        new Action(leftBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(leftBack, this.formation.get(Position.D_CL), ActionType.Pass),
                        new Action(leftBack, this.formation.get(Position.M_LC), ActionType.Pass, 1.4),
                        new Action(leftBack, this.formation.get(Position.M_C), ActionType.Pass, 2.1),
                        new Action(leftBack, this.formation.get(Position.F_LC), ActionType.Pass, 2 * 1.4),
                        new Action(leftBack, this.formation.get(Position.F_C), ActionType.Pass, 2 * 2.1)
                ));

                Player centreRightBack = formation.get(Position.D_CR);
                centreRightBack.setPermissibleActions(Arrays.asList(
                        new Action(centreRightBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.D_R), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.D_CL), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.M_C), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.M_RC), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.F_RC), ActionType.Pass, 2),
                        new Action(centreRightBack, this.formation.get(Position.F_C), ActionType.Pass, 2)
                ));

                Player centreLeftBack = formation.get(Position.D_CL);
                centreLeftBack.setPermissibleActions(Arrays.asList(
                        new Action(centreLeftBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.D_L), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.D_CR), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.M_C), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.M_LC), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.F_LC), ActionType.Pass, 2),
                        new Action(centreLeftBack, this.formation.get(Position.F_C), ActionType.Pass, 2)
                ));

                Player rightCentreMidfielder = formation.get(Position.M_RC);
                rightCentreMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(rightCentreMidfielder, this.formation.get(Position.M_C), ActionType.Pass),
                        new Action(rightCentreMidfielder, this.formation.get(Position.F_RC), ActionType.Pass),
                        new Action(rightCentreMidfielder, this.formation.get(Position.F_C), ActionType.Pass, 1.4)
                ));

                Player centreMidfielder = formation.get(Position.M_C);
                centreMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(centreMidfielder, this.formation.get(Position.M_RC), ActionType.Pass),
                        new Action(centreMidfielder, this.formation.get(Position.M_LC), ActionType.Pass),
                        new Action(centreMidfielder, this.formation.get(Position.F_C), ActionType.Pass),
                        new Action(centreMidfielder, this.formation.get(Position.F_RC), ActionType.Pass),
                        new Action(centreMidfielder, this.formation.get(Position.F_LC), ActionType.Pass)
                ));

                Player leftCentreMidfielder = formation.get(Position.M_LC);
                leftCentreMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(leftCentreMidfielder, this.formation.get(Position.M_C), ActionType.Pass),
                        new Action(leftCentreMidfielder, this.formation.get(Position.F_LC), ActionType.Pass),
                        new Action(leftCentreMidfielder, this.formation.get(Position.F_C), ActionType.Pass, 1.4)
                ));

                Player rightCentreForward = formation.get(Position.F_RC);
                rightCentreForward.setPermissibleActions(Arrays.asList(
                        new Action(rightCentreForward, this.formation.get(Position.F_C), ActionType.Pass),
                        new Action(rightCentreForward, null, ActionType.Shoot)
                ));

                Player centreForward = formation.get(Position.F_C);
                centreForward.setPermissibleActions(Arrays.asList(
                        new Action(centreForward, null, ActionType.Shoot),
                        new Action(centreForward, this.formation.get(Position.F_RC), ActionType.Pass),
                        new Action(centreForward, this.formation.get(Position.F_LC), ActionType.Pass)
                ));

                Player leftCentreForward = formation.get(Position.F_LC);
                leftCentreForward.setPermissibleActions(Arrays.asList(
                        new Action(leftCentreForward, this.formation.get(Position.F_C), ActionType.Pass),
                        new Action(leftCentreForward, null, ActionType.Shoot)
                ));
            }
        }
    }

    private void initializeFormation(Tactics tactics) {
        switch(tactics) {
            case _4_4_2 -> {
                if (lineup.isEmpty()) { // create dummy players
                    formation.put(Position.GK, new Player(Position.GK, 1, this));
                    formation.put(Position.D_R, new Player(Position.D_R, 2, this));
                    formation.put(Position.D_L, new Player(Position.D_L, 3, this));
                    formation.put(Position.D_CR, new Player(Position.D_CR, 4, this));
                    formation.put(Position.D_CL, new Player(Position.D_CL, 5, this));
                    formation.put(Position.M_R, new Player(Position.M_R, 7, this));
                    formation.put(Position.M_L, new Player(Position.M_L, 11, this));
                    formation.put(Position.M_CR, new Player(Position.M_CR, 8, this));
                    formation.put(Position.M_CL, new Player(Position.M_CL, 6, this));
                    formation.put(Position.F_CR, new Player(Position.F_CR, 9, this));
                    formation.put(Position.F_CL, new Player(Position.F_CL, 10, this));
                }
            }
            case _4_3_3 -> {
                if (lineup.isEmpty()) {
                    formation.put(Position.GK, new Player(Position.GK, 1, this));
                    formation.put(Position.D_R, new Player(Position.D_R, 2, this));
                    formation.put(Position.D_L, new Player(Position.D_L, 3, this));
                    formation.put(Position.D_CR, new Player(Position.D_CR, 4, this));
                    formation.put(Position.D_CL, new Player(Position.D_CL, 6, this));
                    formation.put(Position.M_RC, new Player(Position.M_RC, 7, this));
                    formation.put(Position.M_C, new Player(Position.M_C, 5, this));
                    formation.put(Position.M_LC, new Player(Position.M_LC, 11, this));
                    formation.put(Position.F_RC, new Player(Position.F_RC, 10, this));
                    formation.put(Position.F_C, new Player(Position.F_C, 9, this));
                    formation.put(Position.F_LC, new Player(Position.F_LC, 11, this));
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

    public Tactics getTactics() {
        return this.tactics;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
