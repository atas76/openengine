package org.ttn;

import org.ttn.lexan.Scanner;
import org.ttn.lexan.exceptions.ScannerException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScannerTest {

    @Test
    public void testStatementScan() throws ScannerException {
        String indirectOutcomeStatement = "00:02 DM->Long >>> M RC @ DM";
        String directOutcomeStatement = "00:15 M->Long:FT:Open => F LC @ Apd";

        List<String> indirectOutcomeTokens = new Scanner(indirectOutcomeStatement).scan();
        List<String> directOutcomeTokens = new Scanner(directOutcomeStatement).scan();

        assertEquals(11, indirectOutcomeTokens.size());
        assertEquals("00", indirectOutcomeTokens.get(0));
        assertEquals(":", indirectOutcomeTokens.get(1));
        assertEquals("02", indirectOutcomeTokens.get(2));
        assertEquals("DM", indirectOutcomeTokens.get(3));
        assertEquals("->", indirectOutcomeTokens.get(4));
        assertEquals("Long", indirectOutcomeTokens.get(5));
        assertEquals(">>>", indirectOutcomeTokens.get(6));
        assertEquals("M", indirectOutcomeTokens.get(7));
        assertEquals("RC", indirectOutcomeTokens.get(8));
        assertEquals("@", indirectOutcomeTokens.get(9));
        assertEquals("DM", indirectOutcomeTokens.get(10));
        //
        assertEquals(15, directOutcomeTokens.size());
        assertEquals("=>", directOutcomeTokens.get(10));
    }

    @Test(expected = ScannerException.class)
    public void testStatementUnsupportedToken() throws org.ttn.lexan.exceptions.ScannerException {
        new Scanner("00:02 DM->Long >>> M RC % DM").scan();
    }

    @Test(expected = ScannerException.class)
    public void testStatementScanInvalidToken() throws org.ttn.lexan.exceptions.ScannerException {
        new Scanner("00:15 M->Long:FT:Open ==> F LC @ Apd").scan();
    }

    @Test(expected = ScannerException.class)
    public void testStatementRandomToken() throws org.ttn.lexan.exceptions.ScannerException {
        new Scanner("---").scan();
    }
}
