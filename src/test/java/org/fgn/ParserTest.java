package org.fgn;

import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.fgn.parser.*;
import org.fgn.parser.exceptions.ParserException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {

    private static final String DEFAULT_ACTION_STATEMENT = "00:00 L: KO => DM";
    private static final String RANDOM_ACTION_STATEMENT = "12:38 T: D->Pass => DM";
    private static final String PARAMETERISED_INSTATE_STATEMENT = "01:47 L: Ap(SP)->Shoot => G";
    private static final String PARAMETERISED_OUTSTATE_STATEMENT = "11:34 T: D(T) => F(Mw)";
    private static final String GARBAGE_END_STATEMENT = "11:34 T: D(T) => F(Mw)))) Garbage)in (garbage out(";
    private static final String GOAL_ATTEMPT_STATEMENT = "09:02 T: A->Shoot => GKo";
    private static final String SHOT_OFF_TARGET = "16:47 L: A->Shoot => GK # comment";
    private static final String ACTION_OUTCOME = "27:42 R: Ap(FT)->Shoot => PST >> FT(Ap)";
    private static final String GS_ACTION_OUTCOME = "19:44 R: Apc->Shoot => GS >> !Dp";
    private static final String GS_CORNER_KICK = "37:47 L: A->Shoot => GS >> C";
    private static final String GS_CATCH = "52:25 L: A->Shoot => GS >> !Dg";

    private Statement parseStatemement(String statement) throws ScannerException, ParserException {
        List<String> tokens = getTokens(statement);
        return new Parser(tokens).parse();
    }

    @Test
    public void testActionOutcome() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(ACTION_OUTCOME);

        assertEquals(ActionOutcome.PST, parsedStatement.getActionOutcome());
        assertEquals(StateContext.FT, parsedStatement.getStateOut().getContext());
        assertEquals(Coordinates.Ap, parsedStatement.getStateOut().getSpace());
    }

    @Test
    public void testGoalSaveActionOutcome() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(GS_ACTION_OUTCOME);

        assertEquals(ActionOutcome.GS, parsedStatement.getActionOutcome());
        assertEquals(Coordinates.Dp, parsedStatement.getStateOut().getSpace());
        assertFalse(parsedStatement.getStateOut().isSamePossesion());
    }

    @Test
    public void testGoalSaveCornerKick() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(GS_CORNER_KICK);

        assertEquals(ActionOutcome.GS, parsedStatement.getActionOutcome());
        assertEquals(StateContext.C, parsedStatement.getStateOut().getContext());
    }

    @Test
    public void testShotOffTarget() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(SHOT_OFF_TARGET);

        assertEquals(StateContext.GK, parsedStatement.getStateOut().getContext());
        assertEquals(" comment", parsedStatement.getComment());
    }

    @Test
    public void testGoalSaveCatch() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(GS_CATCH);

        assertEquals(ActionOutcome.GS, parsedStatement.getActionOutcome());
        assertEquals(Coordinates.Dg, parsedStatement.getStateOut().getSpace());
        assertFalse(parsedStatement.getStateOut().isSamePossesion());
    }

    @Test
    public void testGoalAttempt() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(GOAL_ATTEMPT_STATEMENT);

        assertEquals(StateContext.GKo, parsedStatement.getStateOut().getContext());
    }

    @Test
    public void testParsedStatement() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement("03:20 L: Mw->Long => !Dg");

        assertEquals(new MatchTime(3, 20), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals(Coordinates.Mw, parsedStatement.getStateIn().getSpace());
        assertEquals("Long", parsedStatement.getAction().toString());
        assertEquals(Coordinates.Dg, parsedStatement.getStateOut().getSpace());
        assertFalse(parsedStatement.getStateOut().isSamePossesion());
    }

    @Test
    public void testCommentedStatement() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement("00:00 L: KO => DM # My comments");

        assertEquals(new MatchTime(0, 0), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals(StateContext.KO, parsedStatement.getStateIn().getContext());
        assertEquals(Coordinates.DM, parsedStatement.getStateOut().getSpace());
        assertEquals(" My comments", parsedStatement.getComment());
    }

    @Test
    public void testCommentedOutStatement() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement("# My comments");
        assertEquals(" My comments", parsedStatement.getComment());
    }

    @Test
    public void testSpecialCommentCharactersStatement() throws ScannerException, ParserException {
        Statement parsedStatement = parseStatemement("#@");
        assertEquals("@", parsedStatement.getComment());
    }

    @Test
    public void testEmptyComments() throws ScannerException, ParserException {
        Statement parsedStatement = parseStatemement("#");
        assertEquals("", parsedStatement.getComment());
    }

    @Test
    public void testParameterisedStateParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(PARAMETERISED_INSTATE_STATEMENT);

        assertEquals(new MatchTime(1, 47), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals(Coordinates.Ap, parsedStatement.getStateIn().getSpace());
        assertTrue(parsedStatement.getStateIn().isSetPiece());
        assertEquals("Shoot", parsedStatement.getAction().toString());
        assertEquals(StateContext.G, parsedStatement.getStateOut().getContext());
    }

    @Test
    public void testParameterisedStatesParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(PARAMETERISED_OUTSTATE_STATEMENT);

        assertEquals(new MatchTime(11,34), parsedStatement.getTime());
        assertEquals("T", parsedStatement.getTeam());
        assertEquals(Coordinates.D, parsedStatement.getStateIn().getSpace());
        assertTrue(parsedStatement.getStateIn().isThrowIn());
        assertEquals(StateContext.T, parsedStatement.getStateIn().getContext());
        assertNull(parsedStatement.getAction());
        assertEquals(StateContext.F, parsedStatement.getStateOut().getContext());
        assertEquals(Coordinates.Mw, parsedStatement.getStateOut().getSpace());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testInvalidStatementEnd() throws ScannerException, ParserException {
        expectedEx.expect(ParserException.class);
        expectedEx.expectMessage("Unexpected end of statement");
        parseStatemement(GARBAGE_END_STATEMENT);
    }

    @Test
    public void testUnknownStates() throws ScannerException, ParserException {

        // This is meant to be a dynamic String placeholder, to test patching while ontology is pending
        final String UNKNOWN_STATES_STATEMENT = "00:20 L: Apc->BounceOff => H(Apc)";
        // final String UNKNOWN_STATES_STATEMENT = "13:30 T: Aw->Long => A(T) \t#!Ap(HD)";

        Statement parsedStatement = parseStatemement(UNKNOWN_STATES_STATEMENT);

        assertEquals(new MatchTime(0, 20), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals(Coordinates.Apc, parsedStatement.getStateIn().getSpace());
        assertEquals("BounceOff", parsedStatement.getAction().toString());
        assertEquals(StateContext.H, parsedStatement.getStateOut().getContext());
        assertEquals(Coordinates.Apc, parsedStatement.getStateOut().getSpace());
    }

    @Test
    public void testRandomActionParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(RANDOM_ACTION_STATEMENT);

        assertEquals(new MatchTime(12, 38), parsedStatement.getTime());
        assertEquals("T", parsedStatement.getTeam());
        assertEquals(Coordinates.D, parsedStatement.getStateIn().getSpace());
        assertEquals("Pass", parsedStatement.getAction().toString());
        assertEquals(Coordinates.DM, parsedStatement.getStateOut().getSpace());
    }

    @Test
    public void testDefaultActionParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatemement(DEFAULT_ACTION_STATEMENT);

        assertEquals(new MatchTime(0, 0), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals(StateContext.KO, parsedStatement.getStateIn().getContext());
        assertEquals(Coordinates.DM, parsedStatement.getStateOut().getSpace());
    }

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
