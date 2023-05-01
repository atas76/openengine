package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

public class PlayerTest {

    @Before
    public void setUp() {
        Flags.LOGGING = false;
    }

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
    public void testRightMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_R);
    }

    @Test
    public void testCentreRightMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_CR);
    }

    @Test
    public void testCenterLeftMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_CL);
    }

    @Test
    public void testLeftMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_L);
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
