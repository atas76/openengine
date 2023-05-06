package org.openengine.vanilla;

import org.junit.Before;
import org.junit.Test;
import org.openengine.vanilla.util.Flags;

public class TacticalBehaviour433Test {

    @Before
    public void setUp() {
        Flags.LOGGING = true;
    }

    @Test
    public void testGoalkeeperActions() {
        Match match = new Match(Tactics._4_3_3, Tactics._4_3_3);
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
    public void testRightCentreMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_RC);
    }

    @Test
    public void testCentralMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_C);
    }

    @Test
    public void testLeftCentreMidfielderActions() {
        testPlayerBehaviourByPosition(Position.M_LC);
    }

    private static void testPlayerBehaviourByPosition(Position position) {
        Match match = new Match(Tactics._4_3_3, Tactics._4_3_3);
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
