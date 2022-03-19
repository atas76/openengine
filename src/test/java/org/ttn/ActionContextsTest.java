package org.ttn;

import org.junit.Test;
import org.ttn.engine.environment.ActionContext;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.ParserUtil;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Statement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.ttn.ParserUtilitiesTest.getTokens;

public class ActionContextsTest {

    @Test
    public void testUnderPressureActionContext() throws ScannerException, ParserException {
        List<String> tokens = getTokens("07:12 DMd:Pr->ForwardPass => M C @ MDd:Fr");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionContext.PRESSED, statement.getActionContext());
    }

    @Test
    public void testSetPieceActionContext() throws ScannerException, ParserException {
        List<String> tokens = getTokens("12:11 Mw:SP->ForwardPass => AM L @ Ad");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionContext.SET_PIECE, statement.getActionContext());
    }

}
