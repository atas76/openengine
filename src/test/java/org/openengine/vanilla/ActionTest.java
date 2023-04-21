package org.openengine.vanilla;

import org.junit.Test;

public class ActionTest {

    @Test
    public void testShoot() {
        Match match = new Match();
        match.getState().setPossessionTeam(match.getHomeTeam());
        Action shoot = new Action(match.getHomeTeam().getPlayerByPosition(Position.F_CR),
                        match.getAwayTeam().getGoalkeeper(),
                        ActionType.Shoot);


        ActionOutcome outcome = match.getState().execute(shoot);
        match.updateStats(outcome);
        match.updateState(outcome);

        System.out.println(outcome);
        match.displayScore();
        System.out.println(match.getState());
    }
}
