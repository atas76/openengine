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
import static org.ttn.engine.rules.SetPiece.*;
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
    public void testThrowInBlock() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: ThrowIn");
        Statement statement = new Parser(tokens).parse();

        assertEquals(SP_EXECUTION, statement.getType());
        assertEquals("L", statement.getTeam());
        assertEquals(THROW_IN, statement.getSetPiece());
    }

    @Test
    public void testCornerKickBlock() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set T: Corner");
        Statement statement = new Parser(tokens).parse();

        assertEquals(SP_EXECUTION, statement.getType());
        assertEquals("T", statement.getTeam());
        assertEquals(CORNER_KICK, statement.getSetPiece());
    }

    @Test
    public void defaultExecution() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:18 DM => F LC @ DMw");
        Statement statement = new Parser(tokens).parse();

        assertEquals(DEFAULT_EXECUTION, statement.getType());
        assertEquals(TacticalPosition.X.F, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.LC, statement.getTacticalPositionY());
        assertEquals(PitchPosition.DMw, statement.getActionOutcome().getPitchPosition());
    }

    @Test
    public void testPossessionBlockDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possession L");
        Statement statement = new Parser(tokens).parse();

        assertEquals(POSSESSION_STATEMENT_BLOCK, statement.getType());
        assertEquals("L", statement.getTeam());
    }

    @Test
    public void testPressureBlockDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":pressure T");
        Statement statement = new Parser(tokens).parse();

        assertEquals(PRESSURE_STATEMENT_BLOCK, statement.getType());
        assertEquals("T", statement.getTeam());
    }

    @Test
    public void testTransitionBlockDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":transition T");
        Statement statement = new Parser(tokens).parse();

        assertEquals(TRANSITION_STATEMENT_BLOCK, statement.getType());
        assertEquals("T", statement.getTeam());
    }

    @Test
    public void testPossessorDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possessor D C");
        Statement statement = new Parser(tokens).parse();

        assertEquals(POSSESSOR_DEFINITION, statement.getType());
        assertEquals(TacticalPosition.X.D, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.C, statement.getTacticalPositionY());
    }
    @Test
    public void testBreakDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":break");
        Statement statement = new Parser(tokens).parse();

        assertEquals(BREAK, statement.getType());
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
    public void testPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:17 MDw->Pass => AM L @ Mw");
        Statement statement = new Parser(tokens).parse();

        assertEquals(257, statement.getTime());
        assertEquals(PitchPosition.MDw, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Pass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.AM, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.L, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Mw, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testBackPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:20 DMw->BackPass => D L @ DMw");
        Statement statement = new Parser(tokens).parse();

        assertEquals(200, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getAction().getPitchPosition());
        assertEquals(ActionType.BackPass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.L, statement.getTacticalPositionY());
        assertEquals(PitchPosition.DMw, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testParallelPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:58 Dp->ParallelPass => D C @ Dp");
        Statement statement = new Parser(tokens).parse();

        assertEquals(238, statement.getTime());
        assertEquals(PitchPosition.Dp, statement.getAction().getPitchPosition());
        assertEquals(ActionType.ParallelPass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.C, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Dp, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testDiagonalPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:04 Dpw->DiagonalPass => D R @ Dwp");
        Statement statement = new Parser(tokens).parse();

        assertEquals(244, statement.getTime());
        assertEquals(PitchPosition.Dpw, statement.getAction().getPitchPosition());
        assertEquals(ActionType.DiagonalPass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.R, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Dwp, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testForwardPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:06 Dwp->ForwardPass => AM C @ Dd");
        Statement statement = new Parser(tokens).parse();

        assertEquals(246, statement.getTime());
        assertEquals(PitchPosition.Dwp, statement.getAction().getPitchPosition());
        assertEquals(ActionType.ForwardPass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.AM, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.C, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Dd, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testMoveAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:16 DMw->Move => MDw");
        Statement statement = new Parser(tokens).parse();

        assertEquals(256, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Move, statement.getAction().getType());
        assertEquals(PitchPosition.MDw, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testShotAtGoal() throws ScannerException, ParserException {
        List<String> tokens = getTokens("01:47 Ap->Shoot => G");
        Statement statement = new Parser(tokens).parse();

        assertEquals(107, statement.getTime());
        assertEquals(PitchPosition.Ap, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Shoot, statement.getAction().getType());
        assertEquals(OutcomeType.GOAL, statement.getActionOutcome().getType());
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
    public void testInterceptionAndRestingOutcome() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:34 Aw->Pass => !M RC @ Dp:I >> C");
        Statement statement = new Parser(tokens).parse();

        assertEquals(274, statement.getTime());
        assertEquals(PitchPosition.Aw, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Pass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.M, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.RC, statement.getTacticalPositionY());
        assertTrue(statement.isBallPossessionChange());
        assertTrue(statement.getActionOutcome().isInterception());
        assertEquals(OutcomeType.CORNER, statement.getActionOutcome().getRestingOutcome());
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
        assertFalse(statement.isBallPossessionChange());
    }

    @Test
    public void testOutcomeParameters() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:12 Dpw->Long => D L @ DMw:Fr");
        Statement statement = new Parser(tokens).parse();

        assertEquals(252, statement.getTime());
        assertEquals(PitchPosition.Dpw, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.L, statement.getTacticalPositionY());
        assertEquals(PitchPosition.DMw, statement.getActionOutcome().getPitchPosition());
        assertTrue(statement.getActionOutcome().isFreeSpace());
    }

    @Test
    public void testOutcomeNegation() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:21 DMw->Long >>> !Gkr");
        Statement statement = new Parser(tokens).parse();

        assertEquals(201, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.Gkr, statement.getTacticalPositionX()); // Tactical and pitch position merge
        assertTrue(statement.isBallPossessionChange());
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

    @Test(expected = ParserException.class)
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
