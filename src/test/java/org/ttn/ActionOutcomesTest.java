package org.ttn;

import org.junit.Test;
import org.ttn.engine.environment.ActionContext;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.ParserUtil;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Statement;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.ttn.ParserUtilitiesTest.getTokens;

public class ActionOutcomesTest {

    @Test
    public void testClearanceActionContext() throws ScannerException, ParserException {
        List<String> tokens = getTokens("06:40 Aw:Mrk->Dribble => !D L @ Dwp:Clr");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.CLEARANCE));
    }

    @Test
    public void testTacklingActionOutcome() throws ScannerException, ParserException {
        List<String> tokens = getTokens("11:38 Mw->Dribble => !D CR @ DMw:Tck >> F C @ Mw");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.TACKLING));
    }

    @Test
    public void testChallengeActionOutcome() throws ScannerException, ParserException {
        List<String> tokens = getTokens("18:14 DMw->ForwardPass => !D R @ Mw:Ch >> !AM C @ Mw");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.CHALLENGE));
    }

    @Test
    public void testBlockActionOutcome() throws ScannerException, ParserException {
        List<String> tokens = getTokens("18:20 M->ForwardPass => !M RC @ DM:B >> !D C @ DM");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.BLOCK));
    }

    @Test
    public void testFirstTouchActionOutcome() throws ScannerException, ParserException {
        List<String> tokens = getTokens("19:40 A => AM R @ Aw:FT >> AM C @ Awp");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.FIRST_TOUCH));
    }

    @Test
    public void testAdvantageActionOutcome() throws ScannerException, ParserException {
        List<String> tokens = getTokens("54:31 Awp->Dribble => Awp:Adv >> AM L @ Awp");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.ADVANTAGE));
    }

    @Test
    public void textDiveActionOutcome() throws ScannerException, ParserException {
        List<String> tokens = getTokens("90:58 Mw->Move => Apd:Dv >> !F CL @ Dpw");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertTrue(statement.getActionOutcome().isOutcome(ActionContext.DIVE));
    }
}
