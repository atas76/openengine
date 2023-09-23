package org.mpn;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LexanTest {

    @Test
    public void testKickoff() {
        Lexan lexan = new Lexan();

        List<String> tokens = lexan.scan("L: 00:00 KickOff -> Attack");

        assertEquals("L", tokens.get(0));
    }
}
