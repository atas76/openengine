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

    @Test
    public void testArgumentsCommalist() throws Exception {
        Lexan lexan = new Lexan();

        List<String> tokens = lexan.scan("L: 01:47 Penalty -> Goal; xG = 0.79, default = Corner");

        assertEquals("L", tokens.get(0));
        assertEquals(":", tokens.get(1));
        assertEquals("01", tokens.get(2));
        assertEquals(":", tokens.get(3));
        assertEquals("47", tokens.get(4));
        assertEquals("Penalty", tokens.get(5));
        assertEquals("->", tokens.get(6));
        assertEquals("Goal", tokens.get(7));
        assertEquals(";", tokens.get(8));
        assertEquals("xG", tokens.get(9));
        assertEquals("=", tokens.get(10));
        assertEquals("0.79", tokens.get(11));
        assertEquals(",", tokens.get(12));
        assertEquals("default", tokens.get(13));
        assertEquals("=", tokens.get(14));
        assertEquals("Corner", tokens.get(15));
    }
}
