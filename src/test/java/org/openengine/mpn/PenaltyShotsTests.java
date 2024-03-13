package org.openengine.mpn;

import org.junit.Test;
import org.mpn.Parser;
import org.mpn.PitchPosition;
import org.mpn.State;
import org.mpn.Statement;

import static org.junit.Assert.*;

public class PenaltyShotsTests {

    @Test
    public void testPenaltySave() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("X: 00:00 Penalty -> Save => !Goalkeeper");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.SAVE, statement.getEndState());
        assertEquals(State.GOALKEEPER, statement.getGoalAttemptOutcome());
        assertTrue(statement.isPossessionChanged());
    }

    @Test
    public void testPenaltySaveGoalAttemptAfterRebound() throws Exception {
        Parser parser = new Parser();

        Statement statement =
                parser.parse("X: 00:00 Penalty -> Save => GoalAttempt:AB01; xG = 0.27, default = OffTarget");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.SAVE, statement.getEndState());
        assertEquals(State.GOAL_ATTEMPT, statement.getGoalAttemptOutcome());
        assertEquals(PitchPosition.AB01, statement.getOutcomePitchPosition());
        assertEquals(0.27, statement.getParameters().xG(), 0.0);
        assertEquals(State.OFF_TARGET, statement.getParameters().defaultEndState());
    }
}
