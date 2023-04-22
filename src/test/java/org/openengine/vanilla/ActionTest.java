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

    @Test
    public void testPass() {
        Match match = new Match();
        match.getState().setPossessionTeam(match.getHomeTeam());
        Player passTarget = match.getHomeTeam().getPlayerByPosition(Position.M_CR);
        passTarget.setMarker(match.getAwayTeam().getPlayerByPosition(Position.M_CL));
        Action pass = new Action(match.getHomeTeam().getPlayerByPosition(Position.M_R), passTarget, ActionType.Pass);

        ActionOutcome outcome = match.getState().execute(pass);
        match.updateStats(outcome);
        match.updateState(outcome);

        System.out.println(outcome);
        System.out.println(match.getState());
    }
}
