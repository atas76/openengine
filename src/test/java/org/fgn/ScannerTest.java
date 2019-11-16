package org.fgn;

import org.fgn.lexan.Scanner;
import org.fgn.lexan.Token;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScannerTest {

    private String statement = "00:00 L: KO => DM";

    @Test
    public void testStatementScan() {

        List<String> tokens = new Scanner(statement).scan();

        assertEquals(8, tokens.size());
        assertEquals("00", tokens.get(0));
        assertEquals(":", tokens.get(1));
        assertEquals("00", tokens.get(2));
        assertEquals("L", tokens.get(3));
        assertEquals(":", tokens.get(4));
    }
}
