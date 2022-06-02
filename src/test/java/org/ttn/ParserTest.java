package org.ttn;

import org.junit.Test;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.ActionContext;
import org.ttn.engine.environment.ActionOutcomeType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.Parser;
import org.ttn.parser.exceptions.MissingTokenException;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Directive;
import org.ttn.parser.output.MatchDataElement;
import org.ttn.parser.output.Statement;

import java.util.List;

import static org.junit.Assert.*;
import static org.ttn.ParserUtilitiesTest.getTokens;
import static org.ttn.engine.rules.SetPiece.*;
import static org.ttn.engine.rules.SetPiece.CORNER_KICK;
import static org.ttn.parser.output.MatchDataElement.DirectiveType.*;
import static org.ttn.parser.output.MatchDataElement.StatementType.*;

public class ParserTest {

    @Test(expected = NumberFormatException.class)
    public void statementWithInvalidTime() throws ScannerException, ParserException {
        List<String> tokens = getTokens("a:b DM->Long >>> M RC @ Md");

        new Parser().parseTokenList(tokens);
    }

    @Test
    public void testParseKickOff() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:00 KO => D C @ DM");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.DM, statement.getActionOutcome().getPitchPosition());
    }

    @Test(expected=ParserException.class)
    public void testInvalidStatementEnd() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 Md->Long => F LC @ Apd abc");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(15, statement.getTime());
        assertEquals(PitchPosition.Md, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.F, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.LC, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testKickOffBlockDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: Kickoff");
        Directive directive = (Directive) new Parser().parseTokenList(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
        assertEquals(KICK_OFF, directive.getSetPiece());
    }

    @Test
    public void testPenaltyKickBlockDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: Penalty");
        Directive directive = (Directive) new Parser().parseTokenList(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
        assertEquals(PENALTY, directive.getSetPiece());
    }

    @Test
    public void testThrowInBlock() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: ThrowIn");
        Directive directive = (Directive) new Parser().parseTokenList(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
        assertEquals(THROW_IN, directive.getSetPiece());
    }

    @Test
    public void testCornerKickBlock() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set T: Corner");
        Directive directive = (Directive) new Parser().parseTokenList(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("T", directive.getTeam());
        assertEquals(CORNER_KICK, directive.getSetPiece());
    }

    @Test
    public void defaultExecution() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:18 DM => F LC @ DMw");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(ActionType.Default, statement.getAction().getType());
        assertEquals(PitchPosition.DM, statement.getPitchPosition());
        assertEquals(TacticalPosition.X.F, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.LC, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.DMw, statement.getActionOutcome().getPitchPosition());
    }

    @Test
    public void testCornerKickExecution() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:15 CK->Cross => M C @ AMd");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(STANDARD, statement.getType()); // In the current processing layer it is treated as a standard action
        assertEquals(PitchPosition.CK, statement.getPitchPosition()); // Virtual pitch position
        assertEquals(TacticalPosition.X.M, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.AMd, statement.getActionOutcome().getPitchPosition());
    }

    @Test
    public void testBallControl() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:15 CK->Cross => M C @ AMd:Cnt");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(STANDARD, statement.getType()); // In the current processing layer it is treated as a standard action
        assertEquals(PitchPosition.CK, statement.getPitchPosition()); // Virtual pitch position
        assertEquals(TacticalPosition.X.M, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.AMd, statement.getActionOutcome().getPitchPosition());
        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.CONTROL));
    }

    @Test
    public void testPressureBlockDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":pressure T");
        Directive directive = (Directive) new Parser().parseTokenList(tokens);

        assertEquals(MatchDataElement.DirectiveType.BUILDUP_PRESSURE_BLOCK, directive.getType());
        assertEquals("T", directive.getTeam());
    }

    @Test
    public void testTransitionBlockDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":transition T");
        Directive directive = (Directive) new Parser().parseTokenList(tokens);

        assertEquals(TRANSITION_CHAIN_BLOCK, directive.getType());
        assertEquals("T", directive.getTeam());
    }

    @Test
    public void testPossessorDefinition() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possessor D C");
        Directive directive = (Directive) new Parser().parseTokenList(tokens);

        assertEquals(POSSESSOR_DEFINITION, directive.getType());
        assertEquals(TacticalPosition.X.D, directive.getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, directive.getTacticalPosition().getY());
    }

    @Test
    public void testBreakDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":break");
        Directive directive = (Directive) new Parser().parseTokenList(tokens);

        assertEquals(BREAK, directive.getType());
    }

    @Test
    public void testStandardStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 Md->Long => F LC @ Apd");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(15, statement.getTime());
        assertEquals(PitchPosition.Md, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.F, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.LC, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:17 MDw->Pass => AM L @ Mw");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(257, statement.getTime());
        assertEquals(PitchPosition.MDw, statement.getPitchPosition());
        assertEquals(ActionType.Pass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.AM, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.L, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Mw, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testBackPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:20 DMw->BackPass => D L @ DMw");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(200, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getPitchPosition());
        assertEquals(ActionType.BackPass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.L, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.DMw, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testParallelPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:58 Dp->ParallelPass => D C @ Dp");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(238, statement.getTime());
        assertEquals(PitchPosition.Dp, statement.getPitchPosition());
        assertEquals(ActionType.ParallelPass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Dp, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testDiagonalPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:04 Dpw->DiagonalPass => D R @ Dwp");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(244, statement.getTime());
        assertEquals(PitchPosition.Dpw, statement.getPitchPosition());
        assertEquals(ActionType.DiagonalPass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.R, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Dwp, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testForwardPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:06 Dwp->ForwardPass => AM C @ Dd");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(246, statement.getTime());
        assertEquals(PitchPosition.Dwp, statement.getPitchPosition());
        assertEquals(ActionType.ForwardPass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.AM, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Dd, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testMoveAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:16 DMw->Move => MDw");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(256, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getPitchPosition());
        assertEquals(ActionType.Move, statement.getAction().getType());
        assertEquals(PitchPosition.MDw, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testShotAtGoal() throws ScannerException, ParserException {
        List<String> tokens = getTokens("01:47 Ap->Shoot => G");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(107, statement.getTime());
        assertEquals(PitchPosition.Ap, statement.getPitchPosition());
        assertEquals(ActionType.Shoot, statement.getAction().getType());
        assertEquals(ActionOutcomeType.GOAL, statement.getActionOutcome().getType());
    }

    @Test
    public void testActionParameter() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 Md->Long:FT => F LC @ Apd");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(15, statement.getTime());
        assertEquals(PitchPosition.Md, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertTrue(statement.getAction().isFirstTouch());
        assertFalse(statement.getAction().isOpenPass());
        assertEquals(TacticalPosition.X.F, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.LC, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testActionParameters() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 Md->Long:FT:Open => F LC @ Apd");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(15, statement.getTime());
        assertEquals(PitchPosition.Md, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertTrue(statement.getAction().isFirstTouch());
        assertTrue(statement.getAction().isOpenPass());
        assertEquals(TacticalPosition.X.F, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.LC, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testInterceptionAndRestingOutcome() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:34 Aw->Pass => !M RC @ Dp:I >> C");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(274, statement.getTime());
        assertEquals(PitchPosition.Aw, statement.getPitchPosition());
        assertEquals(ActionType.Pass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.M, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.RC, statement.getActionOutcome().getTacticalPosition().getY());
        assertFalse(statement.isPossessionChange());
        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.INTERCEPTION));
        assertEquals(ActionOutcomeType.CORNER, statement.getRestingOutcome().getType());
    }

    @Test
    public void testHeader() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:15 CK->Cross => !D C @ Dp:HD");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(315, statement.getTime());
        assertEquals(PitchPosition.CK, statement.getPitchPosition());
        assertEquals(ActionType.Cross, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertTrue(statement.isPossessionChange());
        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.HEADER));
    }

    @Test
    public void testIndirectOutcomeStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:02 DM->Long => !D R @ DMw:HD >>> M RC @ Md");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(2, statement.getTime());
        assertEquals(PitchPosition.DM, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.R, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.DMw, statement.getActionOutcome().getPitchPosition());
        assertEquals(TacticalPosition.X.M, statement.getRestingOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.RC, statement.getRestingOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Md, statement.getRestingOutcome().getPitchPosition());
        assertEquals(INDIRECT_OUTCOME, statement.getType());
    }

    @Test
    public void testIndirectOutcomeStatementIndirectDelimiterBugFix() throws ScannerException, ParserException {
        // '=>': direct action outcome delimiter
        // '>>' and '>>>': indirect action outcome delimiters
        List<String> tokens = getTokens("26:05 MD->Long:FT => !D R @ DMd:HD >>> !Gkr");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(PitchPosition.MD, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.R, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.DMd, statement.getActionOutcome().getPitchPosition());
        assertEquals(INDIRECT_OUTCOME, statement.getType());
    }

    @Test
    public void testActionOutcomeType() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:20 Apd->BounceOff => M C @ Ap*H");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(20, statement.getTime());
        assertEquals(PitchPosition.Apd, statement.getPitchPosition());
        assertEquals(ActionType.BounceOff, statement.getAction().getType());
        assertEquals(TacticalPosition.X.M, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Ap, statement.getActionOutcome().getPitchPosition());
        assertEquals(ActionOutcomeType.HANDBALL, statement.getActionOutcome().getType());
        assertFalse(statement.isPossessionChange());
    }

    @Test
    public void testOutcomeParameters() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:12 Dpw->Long => D L @ DMw:Fr");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(252, statement.getTime());
        assertEquals(PitchPosition.Dpw, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.L, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.DMw, statement.getActionOutcome().getPitchPosition());
        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.FREE_SPACE));
    }

    @Test
    public void testOutcomeNegation() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:21 DMw->Long => !D L @ DM:Tck >>> !Gkr");
        Statement statement = (Statement) new Parser().parseTokenList(tokens);

        assertEquals(201, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.Gk.Gkr, statement.getRestingOutcome().getTacticalPosition().getGk());
        assertTrue(statement.isPossessionChange());
    }

    @Test(expected = ParserException.class)
    public void testInvalidTimeSeparator() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00 20 Apd->BounceOff => M C @ Ap*H");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDomainObject() throws ScannerException, ParserException {
        List<String> tokens = getTokens("KO => D C @ DMM");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = ParserException.class)
    public void testMissingDelimiters() throws ScannerException, ParserException {
        List<String> tokens = getTokens("12:38 D Pass DM");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = ParserException.class)
    public void testMissingOutcomeDelimiter() throws ScannerException, ParserException {
        List<String> tokens = getTokens("12:38 D->Pass DM");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = ScannerException.class)
    public void testInvalidOutcomeDelimiter() throws ScannerException, ParserException {
        List<String> tokens = getTokens("12:38 D->Pass + DM");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = ParserException.class)
    public void testMissingActionDelimiter() throws ScannerException, ParserException {
        List<String> tokens = getTokens("12:38 D Pass => DM");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = ParserException.class)
    public void testUnknownBlockLabel() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":random L");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void teamMissing() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possession");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void possessorTacticalPositionMissing() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possessor");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidPossessorDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possessor ABC");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void emptyString() throws ScannerException, ParserException {
        List<String> tokens = getTokens("");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void emptyDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void teamMissingFromSetPieceDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void separatorMissingFromSetPieceDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void missingSetPieceKeyword() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L:");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void testInvalidDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens("@possession");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidDirectiveQualifier() throws ScannerException, ParserException {
        List<String> tokens = getTokens("@possession L");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void testTimedEmptyStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:09");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTimedStatementWithInvalidPitchPosition() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:09 ABC");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void testIncompleteStatementWithMissingAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:09 A");
        new Parser().parseTokenList(tokens);
    }

    @Test(expected = MissingTokenException.class)
    public void testIncompleteStatementWithMissingActionContext() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:09 A:");
        new Parser().parseTokenList(tokens);
    }

}
