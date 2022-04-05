package org.ttn;

import org.junit.Test;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.ActionOutcome;
import org.ttn.engine.environment.ActionOutcomeType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.ParserUtil;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Statement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.ttn.ParserUtilitiesTest.getTokens;

public class ActionOutcomesTypeTest {

    @Test
    public void testParseFoul() throws ScannerException, ParserException {
        List<String> tokens = getTokens("11:40 Mw->Dribble => Mw*F");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionOutcomeType.FOUL, statement.getActionOutcome().getType());
    }

    @Test
    public void testParseOffside() throws ScannerException, ParserException {
        List<String> tokens = getTokens("27:40 DM->Long => !D*O");
        Statement statement = ParserUtil.parseStatement(tokens);

        assertEquals(ActionOutcomeType.OFFSIDE, statement.getActionOutcome().getType());
    }
}
