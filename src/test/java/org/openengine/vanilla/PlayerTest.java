package org.openengine.vanilla;

import org.junit.Test;

public class PlayerTest {

    @Test
    public void testGoalkeeperActions() {
        Match match = new Match();
        match.getState().setPossessionTeam(match.getHomeTeam());
        match.getState().setPossessionPlayer(match.getHomeTeam().getGoalkeeper());
        Player goalkeeper = match.getState().getPossessionPlayer();

        Action action = goalkeeper.decide();
        ActionOutcome outcome = match.getState().execute(action);
        match.updateState(outcome);

        System.out.println(action);
        System.out.println(outcome);
        System.out.println(match.getState());
    }

    @Test
    public void testRightBackActions() {
        testPlayerBehaviourByPosition(Position.D_R);
    }

    @Test
    public void testCentreRightBackActions() {
        testPlayerBehaviourByPosition(Position.D_CR);
    }

    @Test
    public void testCentreLeftBackActions() {
        testPlayerBehaviourByPosition(Position.D_CL);
    }

    @Test
    public void testLeftBackActions() {
        testPlayerBehaviourByPosition(Position.D_L);
    }

    @Test
    public void testCentreRightForwardActions() {
        testPlayerBehaviourByPosition(Position.F_CR);
    }

    @Test
    public void testCentreLeftForwardActions() {
        testPlayerBehaviourByPosition(Position.F_CL);
    }

    private static void testPlayerBehaviourByPosition(Position position) {
        Match match = new Match();
        match.getState().setPossessionTeam(match.getHomeTeam());
        match.getState().setPossessionPlayer(match.getHomeTeam().getPlayerByPosition(position));
        Player player = match.getState().getPossessionPlayer();

        Action action = player.decide();
        ActionOutcome outcome = match.getState().execute(action);
        match.updateState(outcome);

        System.out.println(action);
        System.out.println(outcome);
        System.out.println(match.getState());
    }
}
