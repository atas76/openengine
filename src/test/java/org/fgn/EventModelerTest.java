package org.fgn;

import org.fgn.domain.*;
import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.fgn.parser.Parser;
import org.fgn.parser.Statement;
import org.fgn.parser.exceptions.ParserException;
import org.fgn.schema.ActionOutcome;
import org.fgn.schema.ActionType;
import org.fgn.schema.Coordinates;
import org.fgn.schema.StateContext;
import org.fgn.schema.data.Schema;
import org.junit.Before;
import org.junit.Test;
import org.openfootie.openengine.util.fgn.EventModeler;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventModelerTest {

    private static final String FGN_ROOT = "src/test/resources/data/fgn";

    @Before
    public void setUp() throws IOException {
        Schema schema = Schema.create(FGN_ROOT + "/schema/classic.json");
        StateContext.load(schema);
        ActionOutcome.load(schema);
        Coordinates.load(schema);
        ActionType.load(schema);
    }

    private Statement parseStatement(String statement) throws ScannerException, ParserException {
        List<String> tokens = getTokens(statement);
        return new Parser(tokens).parse();
    }

    private List<String> getTokens(String s) throws ScannerException {
        return new Scanner(s).scan();
    }

    @Test
    public void testKickOff() throws ScannerException, ParserException {

        String kickOff = "00:00 L: KO => DM";

        Statement parsedStatement = parseStatement(kickOff);

        Event event = EventModeler.model(parsedStatement);

        // TODO Value is faked (matches should start at zero), so that the test does not pass by coincidence
        assertEquals(0, event.getTime());
        assertEquals("L", event.getTeam());

        InState state = event.getInputState();
        assertEquals(Context.InState.KO, state.getContext());
        assertEquals(BallPlay.DISCRETE, state.getBallPlay());
        assertEquals(PlayerPosition.OUTFIELD, state.getPlayerPosition());

        org.fgn.domain.Coordinates inputCoordinates = state.getCoordinates();
        assertEquals(org.fgn.domain.Coordinates.X.M, inputCoordinates.getX());
    }
}
