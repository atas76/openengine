package org.ttn;

import org.junit.Test;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.ParserUtil;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Directive;
import org.ttn.parser.output.InPlayPhase;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.ttn.ParserUtilitiesTest.getTokens;
import static org.ttn.engine.rules.SetPiece.*;
import static org.ttn.parser.output.InPlayPhase.*;
import static org.ttn.parser.output.MatchDataElement.DirectiveType.*;

public class DirectivesTest {

    @Test
    public void testPossessionInPlayPhase() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":phase possession L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(INPLAY_PHASE, directive.getType());
        assertEquals(POSSESSION, directive.getInPlayPhase());
        assertEquals("L", directive.getTeam());
    }

    @Test
    public void testBallRecoveryInPlayPhase() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":phase recovery L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(INPLAY_PHASE, directive.getType());
        assertEquals(BALL_RECOVERY, directive.getInPlayPhase());
        assertEquals("L", directive.getTeam());
    }

    @Test
    public void testAttackInPlayPhase() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":phase attack L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(INPLAY_PHASE, directive.getType());
        assertEquals(ATTACK, directive.getInPlayPhase());
        assertEquals("L", directive.getTeam());
    }

    @Test
    public void testPressingInPlayPhase() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":phase pressure T");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(INPLAY_PHASE, directive.getType());
        assertEquals(PRESSURE, directive.getInPlayPhase());
        assertEquals("T", directive.getTeam());
    }

    @Test
    public void testTransitionChainDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":transition T");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(TRANSITION_CHAIN_BLOCK, directive.getType());
        assertEquals("T", directive.getTeam());
    }

    @Test
    public void testPossessorDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possessor D C");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(POSSESSOR_DEFINITION, directive.getType());
        assertEquals(TacticalPosition.X.D, directive.getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.C, directive.getTacticalPosition().getY());
    }

    @Test
    public void testPenaltyKickChain() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: Penalty");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
        assertEquals(PENALTY, directive.getSetPiece());
    }

    @Test
    public void testThrowInChain() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set L: ThrowIn");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
        assertEquals(THROW_IN, directive.getSetPiece());
    }

    @Test
    public void testCornerKickChain() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set T: Corner");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("T", directive.getTeam());
        assertEquals(CORNER_KICK, directive.getSetPiece());
    }

    @Test
    public void testFreekickChain() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":set T: Freekick");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(SET_PIECE_EXECUTION_BLOCK, directive.getType());
        assertEquals("T", directive.getTeam());
        assertEquals(FREEKICK, directive.getSetPiece());
    }

    @Test
    public void testParseBreakDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":break");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(BREAK, directive.getType());
    }

    @Test
    public void testInjuryDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":injury");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(INJURY, directive.getType());
    }

    @Test
    public void testHalfTimeDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":HT");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(HALF_TIME, directive.getType());
    }

    @Test
    public void testAttackingPossessionInPlayPhase() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":phase attacking_possession T");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(INPLAY_PHASE, directive.getType());
        assertEquals(InPlayPhase.ATTACKING_POSSESSION, directive.getInPlayPhase());
        assertEquals("T", directive.getTeam());
    }

    @Test
    public void testDefensiveTransitionDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":defensive_transition L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(DEFENSIVE_TRANSITION, directive.getType());
    }

    @Test
    public void testAttackingTransitionDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":attacking_transition T");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(ATTACKING_TRANSITION, directive.getType());
    }

    @Test
    public void testCounterAttackDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":counter_attack L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(COUNTER_ATTACK, directive.getType());
    }

    @Test
    public void testSubstitutionDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":substitution L: M LC");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(SUBSTITUTION, directive.getType());
        assertEquals("L", directive.getTeam());
        assertEquals(TacticalPosition.X.M, directive.getTacticalPosition().getX());
        assertEquals(TacticalPosition.Y.LC, directive.getTacticalPosition().getY());
    }

    @Test
    public void testFairPlayDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":fair_play");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(FAIR_PLAY, directive.getType());
    }
}
