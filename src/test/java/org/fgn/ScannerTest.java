package org.fgn;

import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScannerTest {

    @Test
    public void testDefaultActionStatementScan() throws ScannerException {

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

    @Test
    public void testCommentedStatement() throws ScannerException {

        String statement = "00:00 L: KO => DM # My comments";

        List<String> tokens = new Scanner(statement).scan();

        assertEquals(10, tokens.size());
        assertEquals("00", tokens.get(0));
        assertEquals(":", tokens.get(1));
        assertEquals("00", tokens.get(2));
        assertEquals("L", tokens.get(3));
        assertEquals(":", tokens.get(4));
        assertEquals("KO", tokens.get(5));
        assertEquals("=>", tokens.get(6));
        assertEquals("DM", tokens.get(7));
        assertEquals("#", tokens.get(8));
        assertEquals(" My comments", tokens.get(9));
    }

    @Test
    public void testCommentedOutStatement() throws ScannerException {

        String statement = "# My comments";

        List<String> tokens = new Scanner(statement).scan();

        assertEquals(2, tokens.size());
        assertEquals("#", tokens.get(0));
        assertEquals(" My comments", tokens.get(1));
    }

    @Test
    public void testEmptyComments() throws ScannerException {

        String statement = "#";

        List<String> tokens = new Scanner(statement).scan();

        assertEquals(2, tokens.size());
        assertEquals("#", tokens.get(0));
        assertEquals("", tokens.get(1));
    }

    @Test
    public void testStatementScan() throws ScannerException {

        String statement = "00:02 L: DM->Long => M";

        List<String> tokens = new Scanner(statement).scan();

        assertEquals(10, tokens.size());
        assertEquals("00", tokens.get(0));
        assertEquals(":", tokens.get(1));
        assertEquals("02", tokens.get(2));
        assertEquals("L", tokens.get(3));
        assertEquals(":", tokens.get(4));
        assertEquals("DM", tokens.get(5));
        assertEquals("->", tokens.get(6));
        assertEquals("Long", tokens.get(7));
        assertEquals("=>", tokens.get(8));
        assertEquals("M", tokens.get(9));

    }

    @Test
    public void testParameterisedState() throws ScannerException {

        String statement = "00:20 L: Apc->BounceOff => H(Apc)";

        List<String> tokens = new Scanner(statement).scan();

        assertEquals(13,tokens.size());
        assertEquals("00", tokens.get(0));
        assertEquals(":", tokens.get(1));
        assertEquals("20", tokens.get(2));
        assertEquals("L", tokens.get(3));
        assertEquals(":", tokens.get(4));
        assertEquals("Apc", tokens.get(5));
        assertEquals("->", tokens.get(6));
        assertEquals("BounceOff", tokens.get(7));
        assertEquals("=>", tokens.get(8));
        assertEquals("H", tokens.get(9));
        assertEquals("(", tokens.get(10));
        assertEquals("Apc", tokens.get(11));
        assertEquals(")", tokens.get(12));

    }

    @Test
    public void testReversalOperator() throws ScannerException {

        String statement = "03:20 L: Mw->Long => !Dg";

        List<String> tokens = new Scanner(statement).scan();

        assertEquals(11, tokens.size());
        assertEquals("=>", tokens.get(8));
        assertEquals("!", tokens.get(9));
        assertEquals("Dg", tokens.get(10));
    }

    @Test
    public void goalAttemptOperator() throws ScannerException {

        String statement = "09:02 T: A->Shoot => G'(GKo)";

        List<String> tokens = new Scanner(statement).scan();

        assertEquals(14, tokens.size());

        assertEquals("G", tokens.get(9));
        assertEquals("'", tokens.get(10));
        assertEquals("(", tokens.get(11));
    }

    @Test(expected = ScannerException.class)
    public void testStatementScanInvalidToken() throws ScannerException {
       new Scanner("00:00 L: KO ==> DM").scan();
    }

    @Test(expected = ScannerException.class)
    public void testStatementRandomToken() throws ScannerException {
        new Scanner("---").scan();
    }

    @Test(expected = ScannerException.class)
    public void testStatementUnsupportedToken() throws ScannerException {
        new Scanner("00:18 R: DMw->Pass => *M(T)").scan();
    }
 }
