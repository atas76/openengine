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
import static org.junit.Assert.assertNull;

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
    public void testFreePlay() throws ScannerException, ParserException {

        String statement = "00:02 L: DM->LongPass => M";

        Statement parsedStatement = parseStatement(statement);

        Event event = EventModeler.model(parsedStatement);

        assertEquals(2, event.getTime());
        assertEquals("L", event.getTeam());

        InState inputState = event.getInputState();
        assertEquals(Context.InState.FREE, inputState.getContext());   // one-time testing: semantics are mapped to ball play value
        assertEquals(BallPlay.CONTINUOUS, inputState.getBallPlay());
        assertEquals(PlayerPosition.OUTFIELD, inputState.getPlayerPosition());

        org.fgn.domain.Coordinates inputCoordinates = inputState.getCoordinates();
        assertEquals(org.fgn.domain.Coordinates.X.DM, inputCoordinates.getX());
        assertEquals(org.fgn.domain.Coordinates.Y.C, inputCoordinates.getY());

        OutState outputState = event.getOutputState();
        assertEquals(BallPlay.CONTINUOUS, outputState.getBallPlay());
        assertEquals(Possession.OWN, outputState.getPossession());
        assertEquals(PlayerPosition.OUTFIELD, outputState.getPlayerPosition());

        org.fgn.domain.Coordinates outputCoordinates = outputState.getCoordinates();
        assertEquals(org.fgn.domain.Coordinates.X.M, outputCoordinates.getX());
        assertEquals(org.fgn.domain.Coordinates.Y.C, outputCoordinates.getY());

        Action action = event.getAction();
        assertEquals(Action.LONGPASS, action);
    }

    @Test
    public void testKickOff() throws ScannerException, ParserException {

        String kickOff = "00:00 L: KO => DM";

        Statement parsedStatement = parseStatement(kickOff);

        Event event = EventModeler.model(parsedStatement);

        assertEquals(0, event.getTime());
        assertEquals("L", event.getTeam());

        InState inputState = event.getInputState();
        assertEquals(Context.InState.KO, inputState.getContext());
        assertEquals(BallPlay.DISCRETE, inputState.getBallPlay());
        assertEquals(PlayerPosition.OUTFIELD, inputState.getPlayerPosition());

        org.fgn.domain.Coordinates inputCoordinates = inputState.getCoordinates();
        assertEquals(org.fgn.domain.Coordinates.X.M, inputCoordinates.getX());
        assertEquals(org.fgn.domain.Coordinates.Y.C, inputCoordinates.getY());

        OutState outputState = event.getOutputState();
        assertEquals(BallPlay.CONTINUOUS, outputState.getBallPlay());
        assertNull(outputState.getContext());       // one-time test: we don't test null values
        assertNull(outputState.getActionOutcome()); // one-time
        assertEquals(Possession.OWN, outputState.getPossession());
        assertEquals(PlayerPosition.OUTFIELD, outputState.getPlayerPosition());

        org.fgn.domain.Coordinates outputCoordinates = outputState.getCoordinates();
        assertEquals(org.fgn.domain.Coordinates.X.DM, outputCoordinates.getX());
        assertEquals(org.fgn.domain.Coordinates.Y.C, outputCoordinates.getY());

        Action action = event.getAction();
        assertEquals(Action.PASS, action);
    }
}
