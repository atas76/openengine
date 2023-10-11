package org.mpn;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LexanTest {

    @Test
    public void testKickoff() throws Exception {
        Lexan lexan = new Lexan();

        List<String> tokens = lexan.scan("L: 00:00 KickOff -> Attack");

        assertEquals("L", tokens.get(0));
        assertEquals(":", tokens.get(1));
        assertEquals("00", tokens.get(2));
        assertEquals(":", tokens.get(3));
        assertEquals("00", tokens.get(4));
        assertEquals("KickOff", tokens.get(5));
        assertEquals("->", tokens.get(6));
        assertEquals("Attack", tokens.get(7));
    }

    @Test
    public void testTimeSpan() throws Exception {
        Lexan lexan = new Lexan();

        List<String> tokens = lexan.scan("L: 00:19 => 00:21 Attack -> Penalty");

        assertEquals("L", tokens.get(0));
        assertEquals(":", tokens.get(1));
        assertEquals("00", tokens.get(2));
        assertEquals(":", tokens.get(3));
        assertEquals("19", tokens.get(4));
        assertEquals("=>", tokens.get(5));
        assertEquals("00", tokens.get(6));
        assertEquals(":", tokens.get(7));
        assertEquals("21", tokens.get(8));
        assertEquals("Attack", tokens.get(9));
        assertEquals("->", tokens.get(10));
        assertEquals("Penalty", tokens.get(11));
    }
}
