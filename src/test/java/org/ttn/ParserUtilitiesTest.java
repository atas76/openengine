package org.ttn;

import org.junit.Test;
import org.ttn.engine.agent.Action;
import org.ttn.engine.agent.ActionParameter;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.ActionOutcome;
import org.ttn.engine.environment.ActionContext;
import org.ttn.engine.environment.ActionOutcomeType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;
import org.ttn.lexan.Scanner;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.output.Directive;
import org.ttn.parser.ParserUtil;
import org.ttn.parser.output.Parsable;
import org.ttn.parser.output.Statement;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.exceptions.ValueException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.ttn.engine.rules.SetPiece.*;
import static org.ttn.parser.output.Parsable.DirectiveType.*;
import static org.ttn.parser.output.Parsable.StatementType.*;

public class ParserUtilitiesTest {

    @Test
    public void testGetPitchPosition() {
        assertEquals(PitchPosition.DM, ParserUtil.getPitchPosition("DM"));
    }

    @Test
    public void testGetTacticalPositionX() {
        assertEquals(TacticalPosition.X.D, ParserUtil.getTacticalPositionX("D"));
    }

    @Test
    public void testGetTacticalPositionY() {
        assertEquals(TacticalPosition.Y.C, ParserUtil.getTacticalPositionY("C"));
    }

    @Test
    public void testGetGoalkeeperPosition() {
        assertEquals(TacticalPosition.X.Gkr, ParserUtil.getGoalkeeperPosition("Gkr").getX());
    }

    @Test
    public void testGetActionType() {
        assertEquals(ActionType.Long, ParserUtil.getActionType("Long"));
    }

    @Test
    public void testGetActionParameter() throws ValueException {
        assertEquals(ActionParameter.FIRST_TOUCH, ParserUtil.getActionParameter("FT"));
        assertEquals(ActionParameter.OPEN_PASS, ParserUtil.getActionParameter("Open"));
    }

    @Test
    public void testGetActionOutcomeType() throws ValueException {
        assertEquals(ActionOutcomeType.HANDBALL, ParserUtil.getActionOutcomeType("H"));
    }

    @Test
    public void testGetActionOutcomeParameter() throws ValueException {
        assertEquals(ActionContext.FREE_SPACE, ParserUtil.getActionContext("Fr"));
    }

    @Test
    public void testParseTacticalPosition() {
        List<String> tokens = Arrays.asList("D", "C");

        TacticalPosition tacticalPosition = ParserUtil.parseTacticalPosition(tokens);

        assertEquals(TacticalPosition.X.D, tacticalPosition.getX());
        assertEquals(TacticalPosition.Y.C, tacticalPosition.getY());
    }
    
    @Test
    public void testSpaceBoundOutcome() throws ParserException {
        List<String> tokens = Arrays.asList("D", "C", "@", "DM");

        ActionOutcome actionOutcome = ParserUtil.parseSpaceBoundActionOutcome(tokens);

        assertEquals(TacticalPosition.X.D, actionOutcome.getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, actionOutcome.getTacticalPosition().getY());
        assertEquals(PitchPosition.DM, actionOutcome.getPitchPosition());
    }

    @Test
    public void testDefaultActionStatement() throws ParserException {
        List<String> tokens = Arrays.asList("00", ":", "00", "KO", "=>", "D", "C", "@", "DM");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(0, statement.getTime());
        assertEquals(ActionType.Default, statement.getAction().getType());
        assertEquals(PitchPosition.KO, statement.getPitchPosition());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.DM, statement.getActionOutcome().getPitchPosition());
    }

    @Test
    public void testParseIndirectOutcomeStatement() throws ParserException {
        List<String> tokens = Arrays.asList("00", ":", "02", "DM", "->", "Long", ">>>", "M", "RC", "@", "Md");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(2, statement.getTime());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(PitchPosition.DM, statement.getPitchPosition());
        assertEquals(TacticalPosition.X.M, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.RC, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Md, statement.getActionOutcome().getPitchPosition());
        assertEquals(INDIRECT_OUTCOME, statement.getType());
    }

    @Test
    public void testParseDirectOutcomeStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 Md->Long => F LC @ Apd");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(15, statement.getTime());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(PitchPosition.Md, statement.getPitchPosition());
        assertEquals(TacticalPosition.X.F, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.LC, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
    }

    @Test
    public void testParseActionType() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:53 MA->WidePass => F LC @ Ad");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(353, statement.getTime());
        assertEquals(ActionType.WidePass, statement.getAction().getType());
        assertEquals(PitchPosition.MA, statement.getPitchPosition());
        assertEquals(TacticalPosition.X.F, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.LC, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Ad, statement.getActionOutcome().getPitchPosition());
    }

    @Test
    public void testParseIndirectAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:27 Gkd -> D R @ DMw");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(327, statement.getTime());
        assertEquals(ActionType.Implicit, statement.getAction().getType());
        assertEquals(PitchPosition.Gkd, statement.getPitchPosition());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.R, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.DMw, statement.getActionOutcome().getPitchPosition());
    }

    @Test
    public void testParseMoveAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:16 DMw->Move => MDw");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(256, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getPitchPosition());
        assertEquals(ActionType.Move, statement.getAction().getType());
        assertEquals(PitchPosition.MDw, statement.getActionOutcome().getPitchPosition());
        assertEquals(STANDARD, statement.getType());
    }

    @Test
    public void testActionContext() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:50 MA:Mrk->Move => MA");

        Statement statement = ParserUtil.parseStatement(tokens);
        assertEquals(350, statement.getTime());
        assertEquals(ActionType.Move, statement.getAction().getType());
        assertEquals(PitchPosition.MA, statement.getActionOutcome().getPitchPosition());
        assertEquals(ActionContext.MARKED, statement.getActionContext());
    }

    @Test
    public void testActionContextInActionOutcomePitchPosition() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:50 MA:Mrk->Move => MA:Fr");

        Statement statement = ParserUtil.parseStatement(tokens);
        assertEquals(350, statement.getTime());
        assertEquals(ActionType.Move, statement.getAction().getType());
        assertEquals(PitchPosition.MA, statement.getActionOutcome().getPitchPosition());
        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.FREE_SPACE));
    }

    @Test
    public void testPivotActionOutcomeContext() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:58 Ad->Pass => F RC @ Apd:Pvt");

        Statement statement = ParserUtil.parseStatement(tokens);
        assertEquals(358, statement.getTime());
        assertEquals(ActionType.Pass, statement.getAction().getType());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.PIVOT));
    }

    @Test
    public void testParseActionParameters() throws ScannerException, ParserException {
        List<String> tokens = getTokens("FT:Open");

        List<ActionParameter> actionParameters = ParserUtil.parseActionParameters(tokens);

        assertEquals(2, actionParameters.size());
        assertEquals(ActionParameter.FIRST_TOUCH, actionParameters.get(0));
        assertEquals(ActionParameter.OPEN_PASS, actionParameters.get(1));
    }

    @Test
    public void testParseParameterisedAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("Long:FT:Open");

        Action action = ParserUtil.parseAction(tokens);

        assertEquals(ActionType.Long, action.getType());
        assertTrue(action.isFirstTouch());
        assertTrue(action.isOpenPass());
    }

    @Test
    public void testParseActionParametersStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:15 Md->Long:FT:Open => F LC @ Apd");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(15, statement.getTime());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertTrue(statement.getAction().isFirstTouch());
        assertTrue(statement.getAction().isOpenPass());
        assertEquals(PitchPosition.Md, statement.getPitchPosition());
        assertEquals(TacticalPosition.X.F, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.LC, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Apd, statement.getActionOutcome().getPitchPosition());
    }

    @Test
    public void testParseActionOutcomeType() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:20 Apd->BounceOff => M C @ Ap*H");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(20, statement.getTime());
        assertEquals(PitchPosition.Apd, statement.getPitchPosition());
        assertEquals(ActionType.BounceOff, statement.getAction().getType());
        assertEquals(TacticalPosition.X.M, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Ap, statement.getActionOutcome().getPitchPosition());
        assertEquals(ActionOutcomeType.HANDBALL, statement.getActionOutcome().getType());
    }

    @Test
    public void testParseThrowIn() throws ScannerException, ParserException {
        List<String> tokens = getTokens("06:02 Dp->Long:FT => !MA*T");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(362, statement.getTime());
        assertEquals(PitchPosition.Dp, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(PitchPosition.MA, statement.getActionOutcome().getPitchPosition());
        assertEquals(ActionOutcomeType.THROW_IN, statement.getActionOutcome().getType());
        assertTrue(statement.isPossessionChange());
    }

    @Test
    public void testParseGoalkeeperTacticalPosition() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:21 DMw->Long >>> Gkr");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(201, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.Gkr, statement.getActionOutcome().getTacticalPosition().getX());
    }

    @Test
    public void testParseGoalkeeperOutsideAreaPosition() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:22 AMd->BackPass => Gkd");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(322, statement.getTime());
        assertEquals(PitchPosition.AMd, statement.getPitchPosition());
        assertEquals(ActionType.BackPass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.Gkd, statement.getActionOutcome().getTacticalPosition().getX());
    }

    @Test
    public void testParsePossessionChangeStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:21 DMw->Long >>> !Gkr");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(201, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.Gkr, statement.getActionOutcome().getTacticalPosition().getX());
        assertTrue(statement.getActionOutcome().isPossessionChange());
    }

    @Test
    public void testParseActionOutcome() throws ScannerException, ParserException {
        ActionOutcome goalScoredActionOutcome = ParserUtil.parseActionOutcome(getTokens("G"));
        ActionOutcome spaceBoundActionOutcome = ParserUtil.parseActionOutcome(getTokens("M C @ Ap*H"));

        assertEquals(ActionOutcomeType.GOAL, goalScoredActionOutcome.getType());
        assertEquals(TacticalPosition.X.M, spaceBoundActionOutcome.getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, spaceBoundActionOutcome.getTacticalPosition().getY());
        assertEquals(PitchPosition.Ap, spaceBoundActionOutcome.getPitchPosition());
        assertEquals(ActionOutcomeType.HANDBALL, spaceBoundActionOutcome.getType());
    }

    @Test
    public void testParseFixedSpaceActionOutcome() throws ScannerException, ParserException {
        List<String> tokens = getTokens("01:47 Ap->Shoot => G");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(107, statement.getTime());
        assertEquals(PitchPosition.Ap, statement.getPitchPosition());
        assertEquals(ActionType.Shoot, statement.getAction().getType());
        assertEquals(ActionOutcomeType.GOAL, statement.getActionOutcome().getType());
    }

    @Test
    public void testPossessionChangeWhenGoalIsScored() throws ScannerException, ParserException {
        List<String> tokens = getTokens("01:47 Ap->Shoot => G");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(107, statement.getTime());
        assertEquals(PitchPosition.Ap, statement.getPitchPosition());
        assertEquals(ActionType.Shoot, statement.getAction().getType());
        assertEquals(ActionOutcomeType.GOAL, statement.getActionOutcome().getType());
        assertTrue(statement.getActionOutcome().isPossessionChange());
    }

    @Test
    public void testParseOutcomeParametersStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:12 Dpw->Long => D L @ DMw:Fr");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(252, statement.getTime());
        assertEquals(PitchPosition.Dpw, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.L, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.DMw, statement.getActionOutcome().getPitchPosition());
        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.FREE_SPACE));
    }

    @Test
    public void testParseRestingOutcomeType() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:34 Aw->Pass => !M RC @ Dp:I >> C");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(274, statement.getTime());
        assertEquals(PitchPosition.Aw, statement.getPitchPosition());
        assertEquals(ActionType.Pass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.M, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.RC, statement.getActionOutcome().getTacticalPosition().getY());
        assertTrue(statement.getActionOutcome().isPossessionChange());
        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.INTERCEPTION));
        assertEquals(ActionOutcomeType.CORNER, statement.getRestingOutcome().getType());
        assertFalse(statement.isPossessionChange());
    }

    @Test
    public void testPossessionChangeInBothOutcomes() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:59 Apd->Pass => !AM L @ Dp:I >> !D C @ Dp");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(359, statement.getTime());
        assertEquals(PitchPosition.Apd, statement.getPitchPosition());
        assertEquals(ActionType.Pass, statement.getAction().getType());
        assertEquals(TacticalPosition.X.AM, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.L, statement.getActionOutcome().getTacticalPosition().getY());
        assertTrue(statement.getActionOutcome().isPossessionChange());
        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.INTERCEPTION));
        assertTrue(statement.isPossessionChange());
    }

    @Test
    public void testParseRestingOutcome() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:15 CK->Cross => !D C @ Dp:HD >> M C @ AMd:Cnt");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(315, statement.getTime());
        assertEquals(PitchPosition.CK, statement.getPitchPosition());
        assertEquals(ActionType.Cross, statement.getAction().getType());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertTrue(statement.getActionOutcome().isPossessionChange());
        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.HEADER));
        assertEquals(PitchPosition.Dp, statement.getActionOutcome().getPitchPosition());
        assertEquals(TacticalPosition.X.M, statement.getRestingOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, statement.getActionOutcome().getTacticalPosition().getY());
        assertFalse(statement.getRestingOutcome().isPossessionChange());
        assertTrue(statement.getRestingOutcome().isOutcome(ActionContext.CONTROL));
        assertEquals(PitchPosition.AMd, statement.getRestingOutcome().getPitchPosition());
        assertFalse(statement.isPossessionChange());
    }

    @Test
    public void testDribbleAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("06:40 Aw->Dribble => !D C @ Dwp");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.Dribble, statement.getAction().getType());
    }

    @Test
    public void testParsePossessionChainStartDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possession L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(POSSESSION_CHAIN_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
    }

    @Test
    public void testParseBallRecoveryDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":recovery L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(BALL_RECOVERY_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
    }

    @Test
    public void testAttackChainDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":attack L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(ATTACK_CHAIN_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
    }

    @Test
    public void testBuildupPressureDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":pressure T");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(Parsable.DirectiveType.BUILDUP_PRESSURE_BLOCK, directive.getType());
        assertEquals("T", directive.getTeam());
    }

    @Test
    public void testTransitionChainDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":transition T");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(TRANSITION_CHAIN_BLOCK, directive.getType());
        assertEquals("T", directive.getTeam());
    }

    @Test
    public void testPossessorDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possessor D C");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(POSSESSOR_DEFINITION, directive.getType());
        assertEquals(TacticalPosition.X.D, directive.getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, directive.getTacticalPosition().getY());
    }

    @Test
    public void testPenaltyKickChain() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: Penalty");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
        assertEquals(PENALTY, directive.getSetPiece());
    }

    @Test
    public void testThrowInChain() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: ThrowIn");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
        assertEquals(THROW_IN, directive.getSetPiece());
    }

    @Test
    public void testCornerKickChain() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set T: Corner");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("T", directive.getTeam());
        assertEquals(CORNER_KICK, directive.getSetPiece());
    }

    @Test
    public void testParseBreakDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":break");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(BREAK, directive.getType());
    }

    @Test
    public void testGenericDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: Corner");
        ParserUtil.parseDirective(tokens);
    }

    @Test
    public void testGenericStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("09:37 Dd->Long:Open => !D R @ Dpw:I >> C");
        ParserUtil.parseStatement(tokens);
    }

    @Test
    public void testParseTime() throws ParserException {
        assertEquals(315, ParserUtil.parseTime(Arrays.asList("05", ":", "15")));
        assertEquals(315, ParserUtil.parseTime(Arrays.asList("5", ":", "15")));
        assertEquals(-235, ParserUtil.parseTime(Arrays.asList("-5", ":", "65"))); // No semantic checks provided
    }

    @Test(expected = ParserException.class)
    public void testParseTimeInvalidFormat() throws ParserException {
        ParserUtil.parseTime(Arrays.asList("05", "::", "15"));
    }

    @Test(expected = NumberFormatException.class)
    public void testParseTimeInvalidNumbers() throws ParserException {
        ParserUtil.parseTime(Arrays.asList("a", ":", "b"));
    }

    public static List<String> getTokens(String s) throws ScannerException {
        return new Scanner(s).scan();
    }
}