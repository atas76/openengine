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

public class ActionContextsTest {

    @Test
    public void testTacklingActionOutcome() throws ScannerException, ParserException {
       List<String> tokens = getTokens("11:38 Mw->Dribble => !D CR @ DMw:Tck >> F C @ Mw");
       Statement statement = ParserUtil.parseStatement(tokens);

       assertTrue(statement.getActionOutcome().isOutcome(ActionContext.TACKLING));
    }
}
