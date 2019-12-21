package org.fgn;

import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.fgn.parser.MatchTime;
import org.fgn.parser.Parser;
import org.fgn.parser.Statement;
import org.fgn.parser.exceptions.ParserException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    private static final String DEFAULT_ACTION_STATEMENT = "00:00 L: KO => DM";
    private static final String RANDOM_ACTION_STATEMENT = "12:38 T: D->Pass => DM";
    private static final String PARAMETERISED_ACTION_STATEMENT = "01:47 L: Ap(SP)->Shoot => G";

    private Statement parseStatemement(String statement) throws ScannerException, ParserException {
        List<String> tokens = getTokens(statement);
        return new Parser(tokens).parse();
    }

    @Test
    public void testParameterisedActionParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(PARAMETERISED_ACTION_STATEMENT);

        assertEquals(new MatchTime(1, 47), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals("Ap", parsedStatement.getStateIn().getSpace().toString());
        assertEquals(true, parsedStatement.getStateIn().isSetPiece());
        assertEquals("Shoot", parsedStatement.getAction().toString());
        assertEquals("G", parsedStatement.getStateOut().toString());
    }

    @Test
    public void testRandomActionParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(RANDOM_ACTION_STATEMENT);

        assertEquals(new MatchTime(12, 38), parsedStatement.getTime());
        assertEquals("T", parsedStatement.getTeam());
        assertEquals("D", parsedStatement.getStateIn().toString());
        assertEquals("Pass", parsedStatement.getAction().toString());
        assertEquals("DM", parsedStatement.getStateOut().toString());
    }

    @Test
    public void testDefaultActionParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(DEFAULT_ACTION_STATEMENT);

        assertEquals(new MatchTime(0, 0), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals("KO", parsedStatement.getStateIn().toString());
        assertEquals("DM", parsedStatement.getStateOut().toString());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testSyntaxError() throws ScannerException, ParserException {

        expectedEx.expect(ParserException.class);
        expectedEx.expectMessage("Syntax error");

        // Missing action operators
        List<String> tokens = getTokens("12:38 T: D Pass DM");

        new Parser(tokens).parse();
    }

    @Test
    public void testInvalidTimeSeparator() throws ScannerException, ParserException {

        expectedEx.expect(ParserException.class);
        expectedEx.expectMessage("':' expected");

        // Most invalid separators are restricted by lexical analysis anyway, so cover the case for missing separator
        List<String> tokens = getTokens("12 38 T: D->Pass => DM");

       new Parser(tokens).parse();
    }

    @Test
    public void testInvalidTeamSeparator() throws ScannerException,ParserException {

        expectedEx.expect(ParserException.class);
        expectedEx.expectMessage("':' expected");

        List<String> tokens = getTokens("12:38 T D->Pass => DM");

        new Parser(tokens).parse();
    }

    @Test
    public void testInvalidTimeFormat() throws ScannerException, ParserException {

        expectedEx.expect(ParserException.class);
        expectedEx.expectMessage(ParserException.INVALID_TIME_FORMAT);

        List<String> tokens = getTokens("a:b T: D->Pass => DM");

        new Parser(tokens).parse();
    }

    @Test
    public void testMissingTime() throws ScannerException, ParserException {

        expectedEx.expect(ParserException.class);
        expectedEx.expectMessage(ParserException.INVALID_TIME_FORMAT);

        List<String> tokens = getTokens("T: D->Pass => DM");

        new Parser(tokens).parse();
    }

    private List<String> getTokens(String s) throws ScannerException {
        return new Scanner(s).scan();
    }
}
