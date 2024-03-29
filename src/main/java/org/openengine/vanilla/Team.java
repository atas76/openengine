package org.openengine.vanilla;

import java.util.*;

import static org.openengine.vanilla.State.HORIZONTAL_DISTANCE_UNIT_FACTOR;
import static org.openengine.vanilla.State.VERTICAL_DISTANCE_UNIT_FACTOR;

public class Team {

    private String name;
    private List<Player> lineup = new ArrayList<>();
    // TODO represent formations in a (5x7) tactics matrix, from which permissible actions and markers can be deduced
    private Map<Position, Player> formation = new TreeMap<>();
    private Tactics tactics;

    public Team(String name, Tactics tactics) {
        this(name, TacticsRepository.get(tactics));
        this.tactics = tactics;
    }

    public Team(String name, Tactic tactic) {
        this.name = name;
        initializeFormation(tactic);
        initializeInstructions(tactic);
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

    private void initializeInstructions(Tactic tactic) {
        List<Integer> positionIndices = tactic.getPlayerPositionsIndices();
        Player goalkeeper = formation.get(Position.GK);
        List<Action> goalkeeperActions = new ArrayList<>();
        positionIndices.forEach(index ->
                goalkeeperActions.add(new Action(goalkeeper, this.formation.get(Position.values()[index]), ActionType.Pass,
                        Tactic.computeDistanceUnitFactor(-1, index - 1))));
        goalkeeper.setPermissibleActions(goalkeeperActions);

        Arrays.stream(Position.values())
                .filter(position -> position != Position.GK && formation.get(position) != null)
                .forEach(position -> {
                    Player player = formation.get(position);
                    List<Action> actions = new ArrayList<>();
                    Map<Integer, Tactic.Distance> adjacentPlayerPositions =
                            tactic.getAdjacentPlayersPositions(position.ordinal() - 1);

                    // player is defender
                    if (position.ordinal() <= Tactic.Y_SIZE) {
                        // passes to goalkeeper
                        adjacentPlayerPositions.put(Position.GK.ordinal(), new Tactic.Distance(0, 0));
                    }
                    // add passing actions
                    adjacentPlayerPositions.forEach((targetPlayerPosition, distance) -> {
                        double geometryFactor = 1.0;
                        geometryFactor *= State.DISTANCE_UNIT_FACTORS.get(distance.verticalDistance());
                        if (distance.horizontalDistance() == 1) geometryFactor *= HORIZONTAL_DISTANCE_UNIT_FACTOR;
                        if (distance.horizontalDistance() > 1) geometryFactor *= HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5;
                        actions.add(new Action(player, this.formation.get(Position.values()[targetPlayerPosition]),
                                ActionType.Pass, geometryFactor));
                    });
                    // add shooting actions
                    if (Tactic.isWithinShootingRange((position.ordinal() - 1) / Tactic.Y_SIZE)) {
                        actions.add(new Action(player, null, ActionType.Shoot));
                    }
                    player.setPermissibleActions(actions);
                });
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
                        new Action(goalkeeper, this.formation.get(Position.M_R), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(goalkeeper, this.formation.get(Position.M_CR), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(goalkeeper, this.formation.get(Position.M_CL), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(goalkeeper, this.formation.get(Position.M_L), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(goalkeeper, this.formation.get(Position.F_CR), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR * 2),
                        new Action(goalkeeper, this.formation.get(Position.F_CL), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR * 2)
                ));

                Player rightBack = formation.get(Position.D_R);
                rightBack.setPermissibleActions(Arrays.asList(
                        new Action(rightBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(rightBack, this.formation.get(Position.D_CR), ActionType.Pass),
                        new Action(rightBack, this.formation.get(Position.M_R), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(rightBack, this.formation.get(Position.M_CR), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR),
                        new Action(rightBack, this.formation.get(Position.F_CR), ActionType.Pass,
                                2 * VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR)
                ));

                Player leftBack = formation.get(Position.D_L);
                leftBack.setPermissibleActions(Arrays.asList(
                        new Action(leftBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(leftBack, this.formation.get(Position.D_CL), ActionType.Pass),
                        new Action(leftBack, this.formation.get(Position.M_L), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(leftBack, this.formation.get(Position.M_CL), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR),
                        new Action(leftBack, this.formation.get(Position.F_CL), ActionType.Pass,
                                2.0 * VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR)
                ));

                Player centreRightBack = formation.get(Position.D_CR);
                centreRightBack.setPermissibleActions(Arrays.asList(
                        new Action(centreRightBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.D_R), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.D_CL), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.M_CR), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreRightBack, this.formation.get(Position.M_R), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreRightBack, this.formation.get(Position.F_CR), ActionType.Pass, 2 * VERTICAL_DISTANCE_UNIT_FACTOR)
                ));

                Player centreLeftBack = formation.get(Position.D_CL);
                centreLeftBack.setPermissibleActions(Arrays.asList(
                        new Action(centreLeftBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.D_L), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.D_CR), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.M_CL), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreLeftBack, this.formation.get(Position.M_L), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreLeftBack, this.formation.get(Position.F_CL), ActionType.Pass, 2 * VERTICAL_DISTANCE_UNIT_FACTOR)
                ));

                Player rightMidfielder = formation.get(Position.M_R);
                rightMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(rightMidfielder, this.formation.get(Position.M_CR), ActionType.Pass),
                        new Action(rightMidfielder, this.formation.get(Position.F_CR), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR)
                ));

                Player leftMidfielder = formation.get(Position.M_L);
                leftMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(leftMidfielder, this.formation.get(Position.M_CL), ActionType.Pass),
                        new Action(leftMidfielder, this.formation.get(Position.F_CL), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR)
                ));

                Player centreRightMidfielder = formation.get(Position.M_CR);
                centreRightMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(centreRightMidfielder, this.formation.get(Position.M_R), ActionType.Pass),
                        new Action(centreRightMidfielder, this.formation.get(Position.M_CL), ActionType.Pass),
                        new Action(centreRightMidfielder, this.formation.get(Position.F_CR), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR)
                ));

                Player centreLeftMidfielder = formation.get(Position.M_CL);
                centreLeftMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(centreLeftMidfielder, this.formation.get(Position.M_L), ActionType.Pass),
                        new Action(centreLeftMidfielder, this.formation.get(Position.M_CR), ActionType.Pass),
                        new Action(centreLeftMidfielder, this.formation.get(Position.F_CL), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR)
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
                        new Action(goalkeeper, this.formation.get(Position.M_RC), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(goalkeeper, this.formation.get(Position.M_C), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(goalkeeper, this.formation.get(Position.M_LC), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(goalkeeper, this.formation.get(Position.F_RC), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR * 2),
                        new Action(goalkeeper, this.formation.get(Position.F_C), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR * 2),
                        new Action(goalkeeper, this.formation.get(Position.F_LC), ActionType.Pass, VERTICAL_DISTANCE_UNIT_FACTOR * 2)
                ));

                Player rightBack = formation.get(Position.D_R);
                rightBack.setPermissibleActions(Arrays.asList(
                        new Action(rightBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(rightBack, this.formation.get(Position.D_CR), ActionType.Pass),
                        new Action(rightBack, this.formation.get(Position.M_RC), ActionType.Pass,
                                HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(rightBack, this.formation.get(Position.M_C), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5),
                        new Action(rightBack, this.formation.get(Position.F_RC), ActionType.Pass,
                                2 * HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(rightBack, this.formation.get(Position.F_C), ActionType.Pass,
                                2 * HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR * 1.5)
                ));

                Player leftBack = formation.get(Position.D_L);
                leftBack.setPermissibleActions(Arrays.asList(
                        new Action(leftBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(leftBack, this.formation.get(Position.D_CL), ActionType.Pass),
                        new Action(leftBack, this.formation.get(Position.M_LC), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR),
                        new Action(leftBack, this.formation.get(Position.M_C), ActionType.Pass,
                                HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR * 1.5),
                        new Action(leftBack, this.formation.get(Position.F_LC), ActionType.Pass,
                                2 * HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(leftBack, this.formation.get(Position.F_C), ActionType.Pass,
                                2 * HORIZONTAL_DISTANCE_UNIT_FACTOR * VERTICAL_DISTANCE_UNIT_FACTOR * 1.5)
                ));

                Player centreRightBack = formation.get(Position.D_CR);
                centreRightBack.setPermissibleActions(Arrays.asList(
                        new Action(centreRightBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.D_R), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.D_CL), ActionType.Pass),
                        new Action(centreRightBack, this.formation.get(Position.M_C), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreRightBack, this.formation.get(Position.M_RC), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreRightBack, this.formation.get(Position.F_RC), ActionType.Pass,
                                2 * VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreRightBack, this.formation.get(Position.F_C), ActionType.Pass,
                                2 * VERTICAL_DISTANCE_UNIT_FACTOR)
                ));

                Player centreLeftBack = formation.get(Position.D_CL);
                centreLeftBack.setPermissibleActions(Arrays.asList(
                        new Action(centreLeftBack, this.formation.get(Position.GK), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.D_L), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.D_CR), ActionType.Pass),
                        new Action(centreLeftBack, this.formation.get(Position.M_C), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreLeftBack, this.formation.get(Position.M_LC), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreLeftBack, this.formation.get(Position.F_LC), ActionType.Pass,
                                2 * VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreLeftBack, this.formation.get(Position.F_C), ActionType.Pass,
                                2 * VERTICAL_DISTANCE_UNIT_FACTOR)
                ));

                Player rightCentreMidfielder = formation.get(Position.M_RC);
                rightCentreMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(rightCentreMidfielder, this.formation.get(Position.M_C), ActionType.Pass),
                        new Action(rightCentreMidfielder, this.formation.get(Position.F_RC), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(rightCentreMidfielder, this.formation.get(Position.F_C), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR)
                ));

                Player centreMidfielder = formation.get(Position.M_C);
                centreMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(centreMidfielder, this.formation.get(Position.M_RC), ActionType.Pass),
                        new Action(centreMidfielder, this.formation.get(Position.M_LC), ActionType.Pass),
                        new Action(centreMidfielder, this.formation.get(Position.F_C), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreMidfielder, this.formation.get(Position.F_RC), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(centreMidfielder, this.formation.get(Position.F_LC), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR)
                ));

                Player leftCentreMidfielder = formation.get(Position.M_LC);
                leftCentreMidfielder.setPermissibleActions(Arrays.asList(
                        new Action(leftCentreMidfielder, this.formation.get(Position.M_C), ActionType.Pass),
                        new Action(leftCentreMidfielder, this.formation.get(Position.F_LC), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR),
                        new Action(leftCentreMidfielder, this.formation.get(Position.F_C), ActionType.Pass,
                                VERTICAL_DISTANCE_UNIT_FACTOR * HORIZONTAL_DISTANCE_UNIT_FACTOR)
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

    private void initializeFormation(Tactic tactic) {
        formation.put(Position.GK, new Player(Position.GK, 1, this));
        List<Integer> tacticPositionIndices = tactic.getPlayerPositionsIndices();
        tacticPositionIndices.forEach(index -> {
            Position position = Position.values()[index];
            formation.put(position, new Player(position, position.ordinal() + 2, this));
        });
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

    public int getPlayersNumberInFormation() {
        return this.formation.size();
    }

    public Tactics getTactics() {
        return this.tactics;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
