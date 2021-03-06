package org.fgn;

import org.fgn.schema.ActionOutcome;
import org.fgn.schema.Coordinates;
import org.fgn.schema.StateContext;
import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.fgn.schema.ActionType;
import org.fgn.parser.*;
import org.fgn.parser.exceptions.ParserException;
import org.fgn.schema.data.Schema;
import org.fgn.schema.data.SchemaException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {

    private static final String FGN_ROOT = "src/test/resources/data/fgn";

    private static final String DEFAULT_ACTION_STATEMENT = "00:00 L: KO => DM";
    private static final String RANDOM_ACTION_STATEMENT = "12:38 T: D->Pass => DM";
    private static final String PARAMETERISED_INSTATE_STATEMENT = "01:47 L: Ap(SP)->Shoot => G";
    private static final String PARAMETERISED_OUTSTATE_STATEMENT = "11:34 T: D(T) => F(Mw)";
    private static final String GARBAGE_END_STATEMENT = "11:34 T: D(T) => F(Mw)))) Garbage)in (garbage out(";
    private static final String GOAL_ATTEMPT_STATEMENT = "09:02 T: A->Shoot => GK";
    private static final String SHOT_OFF_TARGET = "16:47 L: A->Shoot => GK # comment";
    private static final String ACTION_OUTCOME = "27:42 R: Ap(FT)->Shoot => PST >> FT(Ap)";
    private static final String GS_ACTION_OUTCOME = "19:44 R: Apc->Shoot => GS >> !Dp";
    private static final String GS_CORNER_KICK = "37:47 L: A->Shoot => GS >> C";
    private static final String GS_CATCH = "52:25 L: A->Shoot => GS >> !Dg";
    private static final String UNSUPPORTED_DOMAIN_OBJECT = "52:25 L: A->Shoot => GS >> !Dggggg";

    private Statement parseStatement(String statement) throws ScannerException, ParserException {
        List<String> tokens = getTokens(statement);
        return new Parser(tokens).parse();
    }

    @Before
    public void setUp() throws IOException {
        Schema schema = Schema.create(FGN_ROOT + "/schema/classic.json");
        StateContext.load(schema);
        ActionOutcome.load(schema);
        Coordinates.load(schema);
        ActionType.load(schema);
    }

    @Test
    public void testActionOutcome() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement(ACTION_OUTCOME);

        assertEquals(ActionOutcome.getEntity("PST"), parsedStatement.getActionOutcome());
        assertEquals(StateContext.getEntity("FT"), parsedStatement.getStateOut().getContext());
        assertEquals(Coordinates.getEntity("Ap"), parsedStatement.getStateOut().getSpace());
    }

    @Test
    public void testGoalSaveActionOutcome() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement(GS_ACTION_OUTCOME);

        assertEquals(ActionOutcome.getEntity("GS"), parsedStatement.getActionOutcome());
        assertEquals(Coordinates.getEntity("Dp"), parsedStatement.getStateOut().getSpace());
        assertFalse(parsedStatement.getStateOut().isSamePossesion());
    }

    @Test
    public void testGoalSaveCornerKick() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement(GS_CORNER_KICK);

        assertEquals(ActionOutcome.getEntity("GS"), parsedStatement.getActionOutcome());
        assertEquals(StateContext.getEntity("C"), parsedStatement.getStateOut().getContext());
    }

    @Test
    public void testShotOffTarget() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement(SHOT_OFF_TARGET);

        assertEquals(StateContext.getEntity("GK"), parsedStatement.getStateOut().getContext());
        assertEquals(" comment", parsedStatement.getComment());
    }

    @Test
    public void testGoalSaveCatch() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement(GS_CATCH);

        assertEquals(ActionOutcome.getEntity("GS"), parsedStatement.getActionOutcome());
        assertEquals(Coordinates.getEntity("Dg"), parsedStatement.getStateOut().getSpace());
        assertFalse(parsedStatement.getStateOut().isSamePossesion());
    }

    @Test
    public void testGoalAttempt() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement(GOAL_ATTEMPT_STATEMENT);

        assertEquals(StateContext.getEntity("GK"), parsedStatement.getStateOut().getContext());
    }

    @Test
    public void testParsedStatement() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement("03:20 L: Mw->LongPass => !Dg");

        assertEquals(new MatchTime(3, 20), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals(Coordinates.getEntity("Mw"), parsedStatement.getStateIn().getSpace());
        assertEquals("LongPass", parsedStatement.getAction().toString());
        assertEquals(Coordinates.getEntity(("Dg")), parsedStatement.getStateOut().getSpace());
        assertFalse(parsedStatement.getStateOut().isSamePossesion());
    }

    @Test
    public void testCommentedStatement() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement("00:00 L: KO => DM # My comments");

        assertEquals(new MatchTime(0, 0), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals(StateContext.getEntity("KO"), parsedStatement.getStateIn().getContext());
        assertEquals(Coordinates.getEntity("DM"), parsedStatement.getStateOut().getSpace());
        assertEquals(" My comments", parsedStatement.getComment());
    }

    @Test
    public void testCommentedOutStatement() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement("# My comments");
        assertEquals(" My comments", parsedStatement.getComment());
    }

    @Test
    public void testSpecialCommentCharactersStatement() throws ScannerException, ParserException {
        Statement parsedStatement = parseStatement("#@");
        assertEquals("@", parsedStatement.getComment());
    }

    @Test
    public void testEmptyComments() throws ScannerException, ParserException {
        Statement parsedStatement = parseStatement("#");
        assertEquals("", parsedStatement.getComment());
    }

    @Test
    public void testParameterisedStateParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement(PARAMETERISED_INSTATE_STATEMENT);

        assertEquals(new MatchTime(1, 47), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals(Coordinates.getEntity("Ap"), parsedStatement.getStateIn().getSpace());
        assertTrue(parsedStatement.getStateIn().isSetPiece());
        assertEquals("Shoot", parsedStatement.getAction().toString());
        assertEquals(StateContext.getEntity("G"), parsedStatement.getStateOut().getContext());
    }

    @Test
    public void testParameterisedStatesParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement(PARAMETERISED_OUTSTATE_STATEMENT);

        assertEquals(new MatchTime(11,34), parsedStatement.getTime());
        assertEquals("T", parsedStatement.getTeam());
        assertEquals(Coordinates.getEntity("D"), parsedStatement.getStateIn().getSpace());
        assertTrue(parsedStatement.getStateIn().isThrowIn());
        assertEquals(StateContext.getEntity("T"), parsedStatement.getStateIn().getContext());
        assertNull(parsedStatement.getAction());
        assertEquals(StateContext.getEntity("F"), parsedStatement.getStateOut().getContext());
        assertEquals(Coordinates.getEntity("Mw"), parsedStatement.getStateOut().getSpace());
    }

    @Test
    public void testUnknownStates() throws ScannerException, ParserException {

        // This is meant to be a dynamic String placeholder, to test patching while ontology is pending
        final String UNKNOWN_STATES_STATEMENT = "00:20 L: Apc->BounceOff => H(Apc)";
        // final String UNKNOWN_STATES_STATEMENT = "13:30 T: Aw->Long => A(T) \t#!Ap(HD)";

        Statement parsedStatement = parseStatement(UNKNOWN_STATES_STATEMENT);

        assertEquals(new MatchTime(0, 20), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals(Coordinates.getEntity("Apc"), parsedStatement.getStateIn().getSpace());
        assertEquals("BounceOff", parsedStatement.getAction().toString());
        assertEquals(StateContext.getEntity("H"), parsedStatement.getStateOut().getContext());
        assertEquals(Coordinates.getEntity("Apc"), parsedStatement.getStateOut().getSpace());
    }

    @Test
    public void testRandomActionParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement(RANDOM_ACTION_STATEMENT);

        assertEquals(new MatchTime(12, 38), parsedStatement.getTime());
        assertEquals("T", parsedStatement.getTeam());
        assertEquals(Coordinates.getEntity("D"), parsedStatement.getStateIn().getSpace());
        assertEquals("Pass", parsedStatement.getAction().toString());
        assertEquals(Coordinates.getEntity("DM"), parsedStatement.getStateOut().getSpace());
    }

    @Test
    public void testDefaultActionParsing() throws ScannerException, ParserException {

        Statement parsedStatement = parseStatement(DEFAULT_ACTION_STATEMENT);

        assertEquals(new MatchTime(0, 0), parsedStatement.getTime());
        assertEquals("L", parsedStatement.getTeam());
        assertEquals(StateContext.getEntity("KO"), parsedStatement.getStateIn().getContext());
        assertEquals(Coordinates.getEntity("DM"), parsedStatement.getStateOut().getSpace());
    }

    // Unhappy path

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testUnsupportedDomainObject() throws ScannerException, ParserException {
        expectedEx.expect(SchemaException.class);
        expectedEx.expectMessage("not supported");
        parseStatement(UNSUPPORTED_DOMAIN_OBJECT);
    }

    @Test
    public void testInvalidStatementEnd() throws ScannerException, ParserException {
        expectedEx.expect(ParserException.class);
        expectedEx.expectMessage("Unexpected end of statement");
        parseStatement(GARBAGE_END_STATEMENT);
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
