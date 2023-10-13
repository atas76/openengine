package org.mpn;

import org.junit.Test;
import org.mpn.exceptions.SyntaxErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
            assertEquals("Expected '->' at position 6", syntaxErrorException.getMessage());
            return;
        }
        fail("Exception expected");
    }
}
