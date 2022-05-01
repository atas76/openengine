package org.ttn;

import org.junit.Test;
import org.ttn.engine.agent.ActionType;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.ParserUtil;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Statement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.ttn.ParserUtilitiesTest.getTokens;

public class ActionTypesTest {

    @Test
    public void testDribbleAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("06:40 Aw:Mrk->Dribble => !D L @ Dwp");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.Dribble, statement.getAction().getType());
    }

    @Test
    public void testHighPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("07:17 Mw->HighPass => !D R @ Dwp");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.HighPass, statement.getAction().getType());
    }

    @Test
    public void testClearanceAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("07:22 Gkr->Clear => !A*T");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.Clear, statement.getAction().getType());
    }

    @Test
    public void testTrianglePseudoAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("15:06 DMd->Triangle => M CR @ DMd");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.Triangle, statement.getAction().getType());
    }

    @Test
    public void testRunAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("18:29 DMw:Fr->Run => Aw");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.Run, statement.getAction().getType());
    }

    @Test
    public void testOneTwoPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("20:29 A->OneTwoPass => F RC @ A");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.OneTwoPass, statement.getAction().getType());
    }

    @Test
    public void testTurningAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("21:51 Awp->Turn => Apw");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.Turn, statement.getAction().getType());
    }

    @Test
    public void testBackHeelPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("22:32 Aw->BackHeelPass => M LC @ Awp");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.BackHeelPass, statement.getAction().getType());
    }

    @Test
    public void testParallelHighPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("23:11 Ad->ParallelHighPass => D L @ AM:Fr");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.ParallelHighPass, statement.getAction().getType());
    }

    @Test
    public void testWideHighPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("30:10 D->WideHighPass => !M*T");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.WideHighPass, statement.getAction().getType());
    }

    @Test
    public void testLowCrossAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("33:32 Awp->LowCross => !D CR @ Dwp:I >> C");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.LowCross, statement.getAction().getType());
    }

    @Test
    public void testSwapSidePassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("37:39 Dd->SwapSidePass => D L @ MDw:Fr");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.SwapSidePass, statement.getAction().getType());
    }

    @Test
    public void testHeaderAttemptAtGoalAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("57:17 Apw->HdSht => !GK");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.HdSht, statement.getAction().getType());
    }

    @Test
    public void testCutbackAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("70:25 Apw->Cutback => !D CL @ Dp:B >>> M*T");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.Cutback, statement.getAction().getType());
    }

    @Test
    public void testHandPassAction() throws ScannerException, ParserException {
        List<String> tokens = getTokens("85:33 Gkr->HandPass => D L @ Mw");

        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionType.HandPass, statement.getAction().getType());
    }
}
