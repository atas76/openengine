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

    @Test
    public void testPenaltyPostDefensiveRebound() throws Exception {
        Parser parser = new Parser();

        Statement statement =
                parser.parse("X: 00:00 Penalty -> Post => !Transition:Dw");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.POST, statement.getEndState());
        assertEquals(State.TRANSITION, statement.getGoalAttemptOutcome());
        assertEquals(PitchPosition.Dw, statement.getOutcomePitchPosition());
        assertTrue(statement.isPossessionChanged());
    }

    @Test
    public void testPenaltyAttackingRebound() throws Exception {
        Parser parser = new Parser();

        Statement statement =
                parser.parse("X: 00:00 Penalty -> Save => Attack:ABw");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.SAVE, statement.getEndState());
        assertEquals(State.ATTACK, statement.getGoalAttemptOutcome());
        assertEquals(PitchPosition.ABw, statement.getOutcomePitchPosition());
    }

    @Test
    public void testPenaltyAttackingThrowIn() throws Exception {
        Parser parser = new Parser();

        Statement statement =
                parser.parse("X: 00:00 Penalty -> Save => ThrowIn:ABw");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.SAVE, statement.getEndState());
        assertEquals(State.THROW_IN, statement.getGoalAttemptOutcome());
        assertEquals(PitchPosition.ABw, statement.getOutcomePitchPosition());
    }

    @Test
    public void testPenaltyPostCorner() throws Exception {
        Parser parser = new Parser();

        Statement statement =
                parser.parse("X: 00:00 Penalty -> Post => Corner");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.POST, statement.getEndState());
        assertEquals(State.CORNER, statement.getGoalAttemptOutcome());
    }

    @Test
    public void testPenaltySaveCorner() throws Exception {
        Parser parser = new Parser();

        Statement statement =
                parser.parse("X: 00:00 Penalty -> Save => Corner");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.SAVE, statement.getEndState());
        assertEquals(State.CORNER, statement.getGoalAttemptOutcome());
    }

    @Test
    public void testPenaltyPostGoalAttemptAfterRebound() throws Exception {
        Parser parser = new Parser();

        Statement statement =
                parser.parse("X: 00:00 Penalty -> Post => GoalAttempt:AB11; xG = 0.04, default = OffTarget");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.POST, statement.getEndState());
        assertEquals(State.GOAL_ATTEMPT, statement.getGoalAttemptOutcome());
        assertEquals(PitchPosition.AB11, statement.getOutcomePitchPosition());
        assertEquals(0.04, statement.getParameters().xG(), 0.0);
        assertEquals(State.OFF_TARGET, statement.getParameters().defaultEndState());
    }

    @Test
    public void testPenaltyShotOffTarget() throws Exception {
        Parser parser = new Parser();

        Statement statement =
                parser.parse("X: 00:00 Penalty -> OffTarget");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.OFF_TARGET, statement.getEndState());
    }

    @Test
    public void testPenaltyShotAttackingEncroachment() throws Exception {
        Parser parser = new Parser();

        Statement statement =
                parser.parse("X: 00:00 Penalty -> Post => AttackingEncroachment");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.POST, statement.getEndState());
        assertEquals(State.ATTACKING_ENCROACHMENT, statement.getGoalAttemptOutcome());
    }

    @Test
    public void testPenaltyShotDefensiveEncroachment() throws Exception {
        Parser parser = new Parser();

        Statement statement =
                parser.parse("X: 00:00 Penalty -> Save => DefensiveEncroachment");

        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.SAVE, statement.getEndState());
        assertEquals(State.DEFENSIVE_ENCROACHMENT, statement.getGoalAttemptOutcome());
    }
}
