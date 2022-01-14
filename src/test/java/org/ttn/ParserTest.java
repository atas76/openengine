package org.ttn;

import org.junit.Test;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.OutcomeType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;
import org.ttn.lexan.Scanner;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.Parser;
import org.ttn.parser.Statement;
import org.ttn.parser.exceptions.ParserException;

import java.util.List;

import static org.junit.Assert.*;
import static org.ttn.engine.rules.SetPiece.KICK_OFF;
import static org.ttn.engine.rules.SetPiece.PENALTY;
import static org.ttn.parser.Statement.Type.*;

public class ParserTest {

    @Test(expected = ParserException.class)
    public void testInvalidTimeFormat() throws ScannerException, ParserException {
        List<String> tokens = getTokens("a:b DM->Long >>> M RC @ Md");

        new Parser(tokens).parse();
    }

    @Test
    public void testDefaultActionParsing() throws ScannerException, ParserException {
        List<String> tokens = getTokens("=> D C @ DM");
        Statement statement = new Parser(tokens).parse();

        assertEquals(TacticalPosition.X.D, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.C, statement.getTacticalPositionY());
        assertEquals(PitchPosition.DM, statement.getActionOutcome().getPitchPosition());
    }

    @Test(expected=ParserException.class)
    public void testInvalidStatementEnd() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 Md->Long => F LC @ Apd abc");
        Statement statement = new Parser(tokens).parse();

        assertEquals(15, statement.getTime());
        assertEquals(PitchPosition.Md, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.F, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.LC, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testKickOffBlockDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: Kickoff");
        Statement statement = new Parser(tokens).parse();

        assertEquals(SP_EXECUTION, statement.getType());
        assertEquals("L", statement.getTeam());
        assertEquals(KICK_OFF, statement.getSetPiece());
    }

    @Test
    public void testPenaltyKickBlockDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: Penalty");
        Statement statement = new Parser(tokens).parse();

        assertEquals(SP_EXECUTION, statement.getType());
        assertEquals("L", statement.getTeam());
        assertEquals(PENALTY, statement.getSetPiece());
    }

    @Test
    public void testPossessionBlockStart() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possession L");
        Statement statement = new Parser(tokens).parse();

        assertEquals(POSSESSION_BLOCK_START, statement.getType());
        assertEquals("L", statement.getTeam());
    }

    @Test
    public void testStandardStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 Md->Long => F LC @ Apd");
        Statement statement = new Parser(tokens).parse();

        assertEquals(15, statement.getTime());
        assertEquals(PitchPosition.Md, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.F, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.LC, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testActionParameter() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 Md->Long:FT => F LC @ Apd");
        Statement statement = new Parser(tokens).parse();

        assertEquals(15, statement.getTime());
        assertEquals(PitchPosition.Md, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertTrue(statement.getAction().isFirstTouch());
        assertFalse(statement.getAction().isOpenPass());
        assertEquals(TacticalPosition.X.F, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.LC, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testActionParameters() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 Md->Long:FT:Open => F LC @ Apd");
        Statement statement = new Parser(tokens).parse();

        assertEquals(15, statement.getTime());
        assertEquals(PitchPosition.Md, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertTrue(statement.getAction().isFirstTouch());
        assertTrue(statement.getAction().isOpenPass());
        assertEquals(TacticalPosition.X.F, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.LC, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testIndirectOutcomeStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:02 DM->Long >>> M RC @ Md");
        Statement statement = new Parser(tokens).parse();

        assertEquals(2, statement.getTime());
        assertEquals(PitchPosition.DM, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.M, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.RC, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Md, statement.getActionOutcome().getPitchPosition());
        assertEquals(INDIRECT_OUTCOME, statement.getType());
    }

    @Test
    public void testActionOutcomeType() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:20 Apd->BounceOff => M C @ Ap*H");
        Statement statement = new Parser(tokens).parse();

        assertEquals(20, statement.getTime());
        assertEquals(PitchPosition.Apd, statement.getAction().getPitchPosition());
        assertEquals(ActionType.BounceOff, statement.getAction().getType());
        assertEquals(TacticalPosition.X.M, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.C, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Ap, statement.getActionOutcome().getPitchPosition());
        assertEquals(OutcomeType.HANDBALL, statement.getActionOutcome().getType());
    }

    @Test(expected = ParserException.class)
    public void testInvalidTimeSeparator() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00 20 Apd->BounceOff => M C @ Ap*H");
        new Parser(tokens).parse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDomainObject() throws ScannerException, ParserException {
        List<String> tokens = getTokens("=> D C @ DMM");
        new Parser(tokens).parse();
    }

    @Test(expected = ParserException.class)
    public void testMissingDelimiters() throws ScannerException, ParserException {
        List<String> tokens = getTokens("12:38 D Pass DM");
        new Parser(tokens).parse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingOutcomeDelimiter() throws ScannerException, ParserException {
        List<String> tokens = getTokens("12:38 D->Pass DM");
        new Parser(tokens).parse();
    }

    @Test(expected = ScannerException.class)
    public void testInvalidOutcomeDelimiter() throws ScannerException, ParserException {
        List<String> tokens = getTokens("12:38 D->Pass + DM");
        new Parser(tokens).parse();
    }

    @Test(expected = ParserException.class)
    public void testMissingActionDelimiter() throws ScannerException, ParserException {
        List<String> tokens = getTokens("12:38 D Pass => DM");
        new Parser(tokens).parse();
    }

    @Test(expected = ParserException.class)
    public void testUnknownBlockLabel() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":random L");
        new Parser(tokens).parse();
    }

    @Test(expected = ParserException.class)
    public void teamMissing() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possession");
        new Parser(tokens).parse();
    }

    @Test(expected = ParserException.class)
    public void testInvalidStatementQualifier() throws ScannerException, ParserException {
        List<String> tokens = getTokens("@possession");
        new Parser(tokens).parse();
    }

    private List<String> getTokens(String s) throws ScannerException {
        return new Scanner(s).scan();
    }
}
