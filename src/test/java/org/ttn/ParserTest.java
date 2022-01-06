package org.ttn;

import org.junit.Test;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;
import org.ttn.lexan.Scanner;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.Parser;
import org.ttn.parser.Statement;
import org.ttn.parser.exceptions.ParserException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.ttn.engine.rules.SetPiece.KICK_OFF;
import static org.ttn.parser.Statement.Type.*;

public class ParserTest {

    @Test(expected = ParserException.class)
    public void testInvalidTimeFormat() throws ScannerException, ParserException {
        List<String> tokens = getTokens("a:b DM->Long >>> M RC @ Md");

        new Parser(tokens).parse();
    }

    @Test
    public void testDefaultActionParsing() throws ScannerException, ParserException {
        List<String> tokens = getTokens("=> D C @ DM");
        Statement statement = new Parser(tokens).parse();

        assertEquals(TacticalPosition.X.D, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.C, statement.getTacticalPositionY());
        assertEquals(PitchPosition.DM, statement.getPitchPosition());
    }

    // Test all statements so far

    @Test
    public void testSetPieceExecution() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: Kickoff");
        Statement statement = new Parser(tokens).parse();

        assertEquals(SP_EXECUTION, statement.getType());
        assertEquals("L", statement.getTeam());
        assertEquals(KICK_OFF, statement.getSetPiece());
    }

    @Test
    public void testPossessionBlockStart() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possession L");
        Statement statement = new Parser(tokens).parse();

        assertEquals(POSSESSION_BLOCK_START, statement.getType());
        assertEquals("L", statement.getTeam());
    }

    @Test
    public void testIndirectOutcomeStatement() throws ScannerException, ParserException {
        List<String> tokens = getTokens("00:02 DM->Long >>> M RC @ Md");
        Statement statement = new Parser(tokens).parse();

        assertEquals(2, statement.getTime());
        assertEquals(PitchPosition.DM, statement.getAction().getPitchPosition());
        assertEquals(ActionType.Long, statement.getAction().getType());
        assertEquals(TacticalPosition.X.M, statement.getTacticalPositionX());
        assertEquals(TacticalPosition.Y.RC, statement.getTacticalPositionY());
        assertEquals(PitchPosition.Md, statement.getPitchPosition());
        assertEquals(INDIRECT_OUTCOME, statement.getType());
    }

    private List<String> getTokens(String s) throws ScannerException {
        return new Scanner(s).scan();
    }
}
