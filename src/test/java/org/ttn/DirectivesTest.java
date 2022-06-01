package org.ttn;

import org.junit.Test;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.ParserUtil;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Directive;
import org.ttn.parser.output.MatchDataElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.ttn.ParserUtilitiesTest.getTokens;
import static org.ttn.engine.rules.SetPiece.*;
import static org.ttn.parser.output.MatchDataElement.DirectiveType.*;
import static org.ttn.parser.output.MatchDataElement.DirectiveType.ATTACKING_POSSESSION;

public class DirectivesTest {

    @Test
    public void testParsePossessionChainStartDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":possession L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(POSSESSION_CHAIN_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
    }

    @Test
    public void testParseBallRecoveryDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":recovery L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(BALL_RECOVERY_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
    }

    @Test
    public void testAttackChainDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":attack L");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(ATTACK_CHAIN_BLOCK, directive.getType());
        assertEquals("L", directive.getTeam());
    }

    @Test
    public void testBuildupPressureDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":pressure T");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(MatchDataElement.DirectiveType.BUILDUP_PRESSURE_BLOCK, directive.getType());
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
    public void testAttackingPossessionDirective() throws ScannerException, ParserException {
        List<String> tokens = getTokens(":attacking_possession T");
        Directive directive = ParserUtil.parseDirective(tokens);

        assertEquals(ATTACKING_POSSESSION, directive.getType());
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
