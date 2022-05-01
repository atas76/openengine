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
import org.ttn.parser.output.Statement;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.exceptions.ValueException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
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
        assertEquals(TacticalPosition.Gk.Gkr, ParserUtil.getGoalkeeperPosition("Gkr").getGk());
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
    public void testParseIndirectOutcomeStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:02 DM->Long => !D R @ DMw:HD >>> M RC @ Md");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(2, statement.getTime());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(PitchPosition.DM, statement.getPitchPosition());
        assertEquals(TacticalPosition.X.D, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.R, statement.getActionOutcome().getTacticalPosition().getY());
        assertEquals(TacticalPosition.X.M, statement.getRestingOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.RC, statement.getRestingOutcome().getTacticalPosition().getY());
        assertEquals(PitchPosition.Md, statement.getRestingOutcome().getPitchPosition());
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
        List<String> tokens = getTokens("05:53 AM->WidePass => F LC @ Ad");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(353, statement.getTime());
        assertEquals(ActionType.WidePass, statement.getAction().getType());
        assertEquals(PitchPosition.AM, statement.getPitchPosition());
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
        List<String> tokens = getTokens("05:50 AM:Mrk->Move => AM");

        Statement statement = ParserUtil.parseStatement(tokens);
        assertEquals(350, statement.getTime());
        assertEquals(ActionType.Move, statement.getAction().getType());
        assertEquals(PitchPosition.AM, statement.getActionOutcome().getPitchPosition());
        assertEquals(ActionContext.MARKED, statement.getActionContext());
    }

    @Test
    public void testActionContextInActionOutcomePitchPosition() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:50 AM:Mrk->Move => AM:Fr");

        Statement statement = ParserUtil.parseStatement(tokens);
        assertEquals(350, statement.getTime());
        assertEquals(ActionType.Move, statement.getAction().getType());
        assertEquals(PitchPosition.AM, statement.getActionOutcome().getPitchPosition());
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
        List<String> tokens = getTokens("06:02 Dp->Long:FT => !AM*T");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(362, statement.getTime());
        assertEquals(PitchPosition.Dp, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(PitchPosition.AM, statement.getActionOutcome().getPitchPosition());
        assertEquals(ActionOutcomeType.THROW_IN, statement.getActionOutcome().getType());
        assertTrue(statement.isPossessionChange());
    }

    @Test
    public void testParseGoalkeeperTacticalPosition() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:21 DMw->Long => !D L @ DM:Tck >>> !Gkr");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(201, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.Gk.Gkr, statement.getRestingOutcome().getTacticalPosition().getGk());
    }

    @Test
    public void testParseGoalkeeperOutsideAreaPosition() throws ScannerException, ParserException {
        List<String> tokens = getTokens("05:22 AMd->BackPass => Gkd");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(322, statement.getTime());
        assertEquals(PitchPosition.AMd, statement.getPitchPosition());
        assertEquals(ActionType.BackPass, statement.getAction().getType());
        assertEquals(TacticalPosition.Gk.Gkd, statement.getActionOutcome().getTacticalPosition().getGk());
    }

    @Test
    public void testParsePossessionChangeStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:21 DMw->Long => !D L @ DM:Tck >>> !Gkr");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(201, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.Gk.Gkr, statement.getRestingOutcome().getTacticalPosition().getGk());
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
    public void testGoalKick() throws ScannerException, ParserException {
        List<String> tokens = getTokens("09:02 A->Shoot => !GK");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionOutcomeType.GOAL_KICK, statement.getActionOutcome().getType());
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
    public void testPitchTacticalPositionClash() throws ScannerException, ParserException {
        List<String> tokens = getTokens("06:40 Aw:Mrk->Dribble => !D L @ Dwp:Clr >> Mw*T");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.CLEARANCE));
        assertEquals(PitchPosition.Mw, statement.getRestingOutcome().getPitchPosition());
        assertEquals(ActionOutcomeType.THROW_IN, statement.getRestingOutcome().getType());
    }

    // Pitch position tests

    @Test
    public void testAMwPitchPosition() throws ScannerException, ParserException {
        List<String> tokens = getTokens("08:29 Mw->BackPass:FT => D L @ AMw");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(PitchPosition.AMw, statement.getActionOutcome().getPitchPosition());
    }

    @Test
    public void testDMdPitchPositionTest() throws ScannerException, ParserException {
        List<String> tokens = getTokens("06:57 MD -> D C @ DMd");

        Statement statement = ParserUtil.parseStatement(tokens);
        assertEquals(PitchPosition.DMd, statement.getActionOutcome().getPitchPosition());
    }

    @Test
    public void testGoalkickPitchPosition() throws ScannerException, ParserException {
        List<String> tokens = getTokens("09:20 GK->ParallelPass => D C @ Dwp");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(PitchPosition.GK, statement.getPitchPosition());
    }

    // Tactical position tests

    @Test
    public void testCentreMidfielderTacticalPosition() throws ScannerException, ParserException {
        List<String> tokens = getTokens("04:08 Dd->ParallelPass => M CL @ DM");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(TacticalPosition.X.M, statement.getActionOutcome().getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.CL, statement.getActionOutcome().getTacticalPosition().getY());
    }

    // Action ('causal') directives

    @Test
    public void testBreakBallActionDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":break_ball_action");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(BREAK_BALL_ACTION, directive.getType());
    }

    @Test
    public void testAttackActionDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":attack_action");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(ATTACK_ACTION, directive.getType());
    }

    @Test
    public void testReleaseFromPressingActionDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":release_pressing_action");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(RELEASE_PRESSING_ACTION, directive.getType());
    }

    @Test
    public void testCoolDownActionDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":cool_down_action");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(COOL_DOWN_ACTION, directive.getType());
    }

    @Test
    public void testWithdrawFromPressureActionDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":withdraw_pressure_action");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(WITHDRAW_PRESSURE_ACTION, directive.getType());
    }

    // Gk position tests

    @Test
    public void testGkPositionStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("03:21 DMw->Long => !D L @ DM:Tck >>> !Gkr");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(201, statement.getTime());
        assertEquals(PitchPosition.DMw, statement.getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.Gk.Gkr, statement.getRestingOutcome().getTacticalPosition().getGk());
        assertTrue(statement.getActionOutcome().isPossessionChange());
    }

    // Generic tests

    @Test
    public void testDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":substitution T: M CL");
        ParserUtil.parseDirective(tokens);
    }

    @Test
    public void testStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("85:33 Gkr->HandPass => D L @ Mw");
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
