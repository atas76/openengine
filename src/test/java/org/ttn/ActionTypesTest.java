package org.ttn;

import org.junit.Test;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.ActionContext;
import org.ttn.engine.environment.ActionOutcomeType;
import org.ttn.engine.space.PitchPosition;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.ParserUtil;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Statement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
}
