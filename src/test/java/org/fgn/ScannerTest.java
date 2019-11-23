package org.fgn;

import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScannerTest {
    
    @Test
    public void testStatementScan() throws ScannerException {

        String statement = "00:00 L: KO => DM";

        List<String> tokens = new Scanner(statement).scan();

        assertEquals(8, tokens.size());
        assertEquals("00", tokens.get(0));
        assertEquals(":", tokens.get(1));
        assertEquals("00", tokens.get(2));
        assertEquals("L", tokens.get(3));
        assertEquals(":", tokens.get(4));
        assertEquals("KO", tokens.get(5));
        assertEquals("=>", tokens.get(6));
        assertEquals("DM", tokens.get(7));
    }

    @Test(expected = ScannerException.class)
    public void testStatementScanInvalidToken() throws ScannerException {
       new Scanner("00:00 L: KO ==> DM").scan();
    }
}
