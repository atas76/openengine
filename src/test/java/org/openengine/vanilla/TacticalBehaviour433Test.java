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
}
