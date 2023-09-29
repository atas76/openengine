package org.mpn;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void testKickoff() throws Exception {
        Parser parser = new Parser();

        Statement statement = parser.parse("L: 00:00 KickOff -> Attack");

        assertEquals("L", statement.getTeamKey());
        // assertEquals(0, statement.getMinutes());
        // assertEquals(0, statement.getSeconds());
        // assertEquals(State.KICK_OFF, statement.getInitialState());
        // assertEquals(State.ATTACK, statement.getEndState());
    }
}
