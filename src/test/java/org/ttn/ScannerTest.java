package org.ttn;

import org.ttn.lexan.Scanner;
import org.ttn.lexan.exceptions.ScannerException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScannerTest {

    @Test
    public void testStatementScan() throws ScannerException {

        String statement = "00:02 DM->Long >>> M RC @ DM";

        List<String> tokens = new Scanner(statement).scan();

        assertEquals(11, tokens.size());
        assertEquals("00", tokens.get(0));
        assertEquals(":", tokens.get(1));
        assertEquals("02", tokens.get(2));
        assertEquals("DM", tokens.get(3));
        assertEquals("->", tokens.get(4));
        assertEquals("Long", tokens.get(5));
        assertEquals(">>>", tokens.get(6));
        assertEquals("M", tokens.get(7));
        assertEquals("RC", tokens.get(8));
        assertEquals("@", tokens.get(9));
        assertEquals("DM", tokens.get(10));
    }
}
