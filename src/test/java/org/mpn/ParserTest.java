package org.mpn;

import org.junit.Test;
import org.mpn.exceptions.SyntaxErrorException;
import org.mpn.exceptions.UnknownStateException;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void testKickoff() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("L: 00:00 KickOff -> Attack");

        assertEquals("L", statement.getTeamKey());
        assertEquals(0, statement.getMinutes());
        assertEquals(0, statement.getSeconds());
        assertEquals(0, statement.getEndMinutes());
        assertEquals(0, statement.getEndSeconds());
        assertEquals(State.KICK_OFF, statement.getInitialState());
        assertEquals(State.ATTACK, statement.getEndState());
    }

    @Test
    public void testTimeSpan() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("L: 00:19 => 00:21 Attack -> Penalty");

        assertEquals("L", statement.getTeamKey());
        assertEquals(0, statement.getMinutes());
        assertEquals(19, statement.getSeconds());
        assertEquals(0, statement.getEndMinutes());
        assertEquals(21, statement.getEndSeconds());
        assertEquals(State.ATTACK, statement.getInitialState());
        assertEquals(State.PENALTY, statement.getEndState());
    }

    @Test
    public void testParameterizedStatementSingle() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("T: 09:03 GoalAttempt -> OffTarget; xG = 0.03");

        assertEquals("T", statement.getTeamKey());
        assertEquals(9, statement.getMinutes());
        assertEquals(3, statement.getSeconds());
        assertEquals(State.GOAL_ATTEMPT, statement.getInitialState());
        assertEquals(State.OFF_TARGET, statement.getEndState());
        assertEquals(0.03, statement.getParameters().xG(), 0.0);
    }

    @Test
    public void testParameterizedStatementMultiple() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("L: 01:47 Penalty -> Goal; xG = 0.79, default = Corner");

        assertEquals("L", statement.getTeamKey());
        assertEquals(1, statement.getMinutes());
        assertEquals(47, statement.getSeconds());
        assertEquals(State.PENALTY, statement.getInitialState());
        assertEquals(State.GOAL, statement.getEndState());
        assertEquals(0.79, statement.getParameters().xG(), 0.1);
        assertEquals(State.CORNER, statement.getParameters().defaultEndState());
    }

    @Test
    public void testParameterizedState() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("L: 03:18 ThrowIn:DM -> Transition");

        assertEquals("L", statement.getTeamKey());
        assertEquals(3, statement.getMinutes());
        assertEquals(18, statement.getSeconds());
        assertEquals(State.THROW_IN, statement.getInitialState());
        assertEquals(PitchPosition.DM, statement.getInitialPitchPosition());
        assertEquals(State.TRANSITION, statement.getEndState());
        assertTrue(statement.isPossessionRetained());
    }

    @Test
    public void testPossessionChange() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("L: 03:18 ThrowIn:DM -> !Transition");

        assertEquals("L", statement.getTeamKey());
        assertEquals(3, statement.getMinutes());
        assertEquals(18, statement.getSeconds());
        assertEquals(State.THROW_IN, statement.getInitialState());
        assertEquals(PitchPosition.DM, statement.getInitialPitchPosition());
        assertEquals(State.TRANSITION, statement.getEndState());
        assertFalse(statement.isPossessionRetained());
    }

    @Test
    public void testParameterizedOutcomeState() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("T: 06:40 => 06:45 AttackingTransition -> ThrowIn:M");

        assertEquals("T", statement.getTeamKey());
        assertEquals(6, statement.getMinutes());
        assertEquals(40, statement.getSeconds());
        assertEquals(6, statement.getEndMinutes());
        assertEquals(45, statement.getEndSeconds());
        assertEquals(State.ATTACKING_TRANSITION, statement.getInitialState());
        assertEquals(State.THROW_IN, statement.getEndState());
        assertEquals(PitchPosition.M, statement.getOutcomePitchPosition());
    }

    @Test
    public void testGoalOutcomeState() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("L: 37:47 GoalAttempt -> Save => Corner");

        assertEquals("L", statement.getTeamKey());
        assertEquals(37, statement.getMinutes());
        assertEquals(47, statement.getSeconds());
        assertEquals(State.GOAL_ATTEMPT, statement.getInitialState());
        assertEquals(State.SAVE, statement.getEndState());
        assertEquals(State.CORNER, statement.getGoalAttemptOutcome());
    }

    @Test
    public void testMultipleOptionalElements() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("L: 37:47 GoalAttempt -> Save => Corner; xG = 0.03");

        assertEquals("L", statement.getTeamKey());
        assertEquals(37, statement.getMinutes());
        assertEquals(47, statement.getSeconds());
        assertEquals(State.GOAL_ATTEMPT, statement.getInitialState());
        assertEquals(State.SAVE, statement.getEndState());
        assertEquals(State.CORNER, statement.getGoalAttemptOutcome());
        assertEquals(0.03, statement.getParameters().xG(), 0.0);
    }

    @Test
    public void smokeTest() throws Exception {
        Parser parser = new Parser();
        final String CURRENT_STATEMENT = "L: 37:47 GoalAttempt -> Save => Corner; xG = 0.03";

        parser.parse(CURRENT_STATEMENT);
    }

    @Test
    public void testUnknownState() throws Exception {
        Parser parser = new Parser();

        try {
            parser.parse("T: 03:37 Transition -> UnknownState");
        } catch (UnknownStateException exception) {
            return;
        }
        fail("UnknownStateException expected");
    }

    @Test
    public void testSyntaxErrorMissingTeamSeparator() throws Exception {
        Parser parser = new Parser();

        try {
            parser.parse("L 00:00 KickOff -> Attack");
        } catch (SyntaxErrorException syntaxErrorException) {
            assertEquals("Expected ':' at position 1", syntaxErrorException.getMessage());
            return;
        }
        fail("Exception expected");
    }

    @Test
    public void testSyntaxErrorIncorrectTimeFormat() throws Exception {
        Parser parser = new Parser();

        try {
            parser.parse("L: 00 KickOff -> Attack");
        } catch (SyntaxErrorException syntaxErrorException) {
            assertEquals("Expected ':' at position 3", syntaxErrorException.getMessage());
            return;
        }
        fail("Exception expected");
    }

    @Test
    public void testSyntaxErrorInvalidStateSeparator() throws Exception {
        Parser parser = new Parser();

        try {
            parser.parse("L: 00:00 KickOff Attack");
        } catch (SyntaxErrorException syntaxErrorException) {
            assertEquals("Unexpected token at position 6", syntaxErrorException.getMessage());
            return;
        }
        fail("Exception expected");
    }
}
