package org.ttn;

import org.junit.Test;
import org.ttn.lexan.Scanner;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.Parser;
import org.ttn.parser.Statement;
import org.ttn.parser.exceptions.ParserException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void testTimeParsing() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 M->Long:FT:Open => F LC @ Apd");
        Statement statement = new Parser(tokens).parse();

        assertEquals(15, statement.getTime());
    }

    @Test(expected = ParserException.class)
    public void testInvalidTimeFormat() throws ScannerException, ParserException {
        List<String> tokens = getTokens("a:b T: D->Pass => DM");

        new Parser(tokens).parse();
    }

    private List<String> getTokens(String s) throws ScannerException {
        return new Scanner(s).scan();
    }
}
