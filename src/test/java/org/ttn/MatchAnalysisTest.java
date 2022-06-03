package org.ttn;

import org.junit.Before;
import org.junit.Test;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.environment.ActionOutcomeType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.lexan.Scanner;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.Parser;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Directive;
import org.ttn.parser.output.InPlayPhase;
import org.ttn.parser.output.MatchDataElement;
import org.ttn.parser.output.Statement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.ttn.engine.agent.ActionType.*;
import static org.ttn.engine.environment.ActionContext.MARKED;
import static org.ttn.engine.environment.ActionOutcomeType.FOUL;
import static org.ttn.engine.environment.ActionOutcomeType.GOAL_KICK;
import static org.ttn.engine.input.TacticalPosition.X.F;
import static org.ttn.engine.input.TacticalPosition.Y.C;
import static org.ttn.engine.rules.SetPiece.*;
import static org.ttn.parser.output.InPlayPhase.*;
import static org.ttn.parser.output.MatchDataElement.DirectiveType.*;

public class MatchAnalysisTest {

    private final String MATCH_SAMPLE_RESOURCE_NAME = "src/test/resources/data/ttn/cl_sample.ttn";

    private Path matchSampleResource;

    @Before
    public void setUp() {
        matchSampleResource = Paths.get(MATCH_SAMPLE_RESOURCE_NAME);
    }

    @Test
    public void testReadMatchData() throws IOException {
        List<String> matchData = Files.readAllLines(matchSampleResource);

        assertEquals(1076, matchData.size());
    }

    @Test
    public void testParseMatchDataElements() throws IOException {
        List<MatchDataElement> matchDataElements = Files.lines(matchSampleResource).map(line -> {
            try {
                return new Parser().parseTokenList(new Scanner(line).scan());
            } catch (ScannerException | ParserException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        assertEquals(1076, matchDataElements.size());
        assertTrue(matchDataElements.get(0) instanceof Directive);
        assertTrue(matchDataElements.get(1) instanceof Statement);
    }

    @Test
    public void testParseMatchFile() throws IOException, ParserException {
        List<MatchDataElement> matchDataElements =
                new Parser().parse(Files.lines(matchSampleResource).collect(Collectors.toList()));

        assertEquals(1076, matchDataElements.size());
        // Kick-off
        assertTrue(matchDataElements.get(0) instanceof Directive);
        Directive kickOff = (Directive) matchDataElements.get(0);
        assertEquals(KICK_OFF, kickOff.getSetPiece());
        // Default action type
        assertTrue(matchDataElements.get(1) instanceof Statement);
        Statement defaultActionStatement = (Statement) matchDataElements.get(1);
        assertEquals(Default, defaultActionStatement.getAction().getType());
        // Possession
        assertTrue(matchDataElements.get(2) instanceof Directive);
        Directive possessionDirective = (Directive) matchDataElements.get(2);
        assertEquals(INPLAY_PHASE, possessionDirective.getType());
        assertEquals(POSSESSION, possessionDirective.getInPlayPhase());
        // Interpretive directive
        assertTrue(matchDataElements.get(3) instanceof Directive);
        Directive interpretiveDirective = (Directive) matchDataElements.get(3);
        assertTrue(interpretiveDirective.getType().toString().endsWith("ACTION"));
        // Long pass
        assertTrue(matchDataElements.get(4) instanceof Statement);
        Statement longPass = (Statement) matchDataElements.get(4);
        assertEquals(ActionType.Long, longPass.getAction().getType());
        // Action parameters
        assertTrue(matchDataElements.get(6) instanceof Statement);
        Statement parameterisedActionStmt = (Statement) matchDataElements.get(6);
        assertTrue(parameterisedActionStmt.getAction().isFirstTouch());
        assertTrue(parameterisedActionStmt.getAction().isOpenPass());
        // Attack
        assertTrue(matchDataElements.get(7) instanceof Directive);
        Directive attackBlockDirective = (Directive) matchDataElements.get(7);
        assertEquals(INPLAY_PHASE, attackBlockDirective.getType());
        assertEquals(ATTACK, attackBlockDirective.getInPlayPhase());
        // Bounceoff action
        assertTrue(matchDataElements.get(8) instanceof Statement);
        Statement bounceOff = (Statement) matchDataElements.get(8);
        assertEquals(BounceOff, bounceOff.getAction().getType());
        // Set piece execution block
        assertTrue(matchDataElements.get(9) instanceof Directive);
        Directive setPiece = (Directive) matchDataElements.get(9);
        assertEquals(SET_PIECE_EXECUTION_BLOCK, setPiece.getType());
        // Shot action
        assertTrue(matchDataElements.get(10) instanceof Statement);
        Statement shotStmt = (Statement) matchDataElements.get(10);
        assertEquals(Shoot, shotStmt.getAction().getType());
        // Break
        assertTrue(matchDataElements.get(11) instanceof Directive);
        Directive flowBreak = (Directive) matchDataElements.get(11);
        assertEquals(BREAK, flowBreak.getType());
        // Throw-in
        assertTrue(matchDataElements.get(12) instanceof Directive);
        Directive throwIn = (Directive) matchDataElements.get(12);
        assertEquals(THROW_IN, throwIn.getSetPiece());
        //
        assertTrue(matchDataElements.get(13) instanceof Statement);
        // Back pass action
        assertTrue(matchDataElements.get(14) instanceof Statement);
        Statement backPassActionStmt = (Statement) matchDataElements.get(14);
        assertEquals(BackPass, backPassActionStmt.getAction().getType());
        // Possession change
        assertTrue(matchDataElements.get(15) instanceof Statement);
        Statement possessionChangeStmt = (Statement) matchDataElements.get(15);
        assertTrue(possessionChangeStmt.getActionOutcome().isPossessionChange());
        // Pressing
        assertTrue(matchDataElements.get(17) instanceof Directive);
        Directive pressingDirective = (Directive) matchDataElements.get(17);
        assertEquals(INPLAY_PHASE, pressingDirective.getType());
        assertEquals(PRESSURE, pressingDirective.getInPlayPhase());
        // Possessor
        assertTrue(matchDataElements.get(18) instanceof Directive);
        Directive possessorDirective = (Directive) matchDataElements.get(18);
        assertEquals(POSSESSOR_DEFINITION, possessorDirective.getType());
        // Parallel pass
        assertTrue(matchDataElements.get(19) instanceof Statement);
        Statement parallelPassAction = (Statement) matchDataElements.get(19);
        assertEquals(ParallelPass, parallelPassAction.getAction().getType());
        // Back pass to goalkeeper
        assertTrue(matchDataElements.get(20) instanceof Statement);
        Statement gkrBackpass = (Statement) matchDataElements.get(20);
        assertEquals(BackPass, gkrBackpass.getAction().getType());
        assertEquals(TacticalPosition.Gk.Gkr, gkrBackpass.getActionOutcome().getTacticalPosition().getGk());
        // Pass action
        assertTrue(matchDataElements.get(21) instanceof Statement);
        Statement passAction = (Statement) matchDataElements.get(21);
        assertEquals(Pass, passAction.getAction().getType());
        // Diagonal pass
        assertTrue(matchDataElements.get(22) instanceof Statement);
        Statement diagonalPass = (Statement) matchDataElements.get(22);
        assertEquals(DiagonalPass, diagonalPass.getAction().getType());
        // Transition
        assertTrue(matchDataElements.get(28) instanceof Directive);
        Directive transitionDirective = (Directive) matchDataElements.get(28);
        assertEquals(TRANSITION_CHAIN_BLOCK, transitionDirective.getType());
        // Move action
        assertTrue(matchDataElements.get(29) instanceof Statement);
        Statement moveActionStmt = (Statement) matchDataElements.get(29);
        assertEquals(Move, moveActionStmt.getAction().getType());
        // Intermediate action outcome
        assertTrue(matchDataElements.get(40) instanceof Statement);
        Statement intermediateActionOutcomeStmt = (Statement) matchDataElements.get(40);
        assertTrue(intermediateActionOutcomeStmt.getActionOutcome().isPossessionChange());
        assertEquals(ActionOutcomeType.CORNER, intermediateActionOutcomeStmt.getRestingOutcome().getType());
        // Corner kick
        assertTrue(matchDataElements.get(41) instanceof Directive);
        Directive cornerKickDirective = (Directive) matchDataElements.get(41);
        assertEquals(SET_PIECE_EXECUTION_BLOCK, cornerKickDirective.getType());
        assertEquals(CORNER_KICK, cornerKickDirective.getSetPiece());
        // Cross action
        assertTrue(matchDataElements.get(42) instanceof Statement);
        Statement crossAction = (Statement) matchDataElements.get(42);
        assertEquals(Cross, crossAction.getAction().getType());
        // Goalkeeper out of penalty area possession
        assertTrue(matchDataElements.get(43) instanceof Statement);
        Statement gkdStmt = (Statement) matchDataElements.get(43);
        assertEquals(TacticalPosition.Gk.Gkd, gkdStmt.getActionOutcome().getTacticalPosition().getGk());
        // Possession chain summary
        assertTrue(matchDataElements.get(45) instanceof Statement);
        Statement possessionChainSummary = (Statement) matchDataElements.get(45);
        assertEquals(Implicit, possessionChainSummary.getAction().getType());
        // Forward pass action
        assertTrue(matchDataElements.get(48) instanceof Statement);
        Statement forwardPassAction = (Statement) matchDataElements.get(48);
        assertEquals(ForwardPass, forwardPassAction.getAction().getType());
        // Action context
        assertTrue(matchDataElements.get(49) instanceof Statement);
        Statement actionContextStmt = (Statement) matchDataElements.get(49);
        assertEquals(MARKED, actionContextStmt.getActionContext());
        // 'Wide pass' action
        assertTrue(matchDataElements.get(51) instanceof Statement);
        Statement widePass = (Statement) matchDataElements.get(51);
        assertEquals(WidePass, widePass.getAction().getType());
        // Possession change in both outcomes
        assertTrue(matchDataElements.get(54) instanceof Statement);
        Statement doublePossessionChange = (Statement) matchDataElements.get(54);
        assertTrue(doublePossessionChange.getActionOutcome().isPossessionChange());
        assertTrue(doublePossessionChange.getRestingOutcome().isPossessionChange());
        // Ball recovery
        assertTrue(matchDataElements.get(55) instanceof Directive);
        Directive ballRecovery = (Directive) matchDataElements.get(55);
        assertEquals(INPLAY_PHASE, ballRecovery.getType());
        assertEquals(BALL_RECOVERY, ballRecovery.getInPlayPhase());
        // Throw-in action outcome
        assertTrue(matchDataElements.get(56) instanceof Statement);
        Statement throwInOutcome = (Statement) matchDataElements.get(56);
        assertEquals(ActionOutcomeType.THROW_IN, throwInOutcome.getActionOutcome().getType());
        // Dribble action
        assertTrue(matchDataElements.get(59) instanceof Statement);
        Statement dribbleAction = (Statement) matchDataElements.get(59);
        assertEquals(Dribble, dribbleAction.getAction().getType());
        // 'High pass' action
        assertTrue(matchDataElements.get(69) instanceof Statement);
        Statement highPassAction = (Statement) matchDataElements.get(69);
        assertEquals(HighPass, highPassAction.getAction().getType());
        // Clearance action
        assertTrue(matchDataElements.get(72) instanceof Statement);
        Statement clearanceAction = (Statement) matchDataElements.get(72);
        assertEquals(Clear, clearanceAction.getAction().getType());
        // Attacking possession
        assertTrue(matchDataElements.get(89) instanceof Directive);
        Directive attackingPossession = (Directive) matchDataElements.get(89);
        assertEquals(INPLAY_PHASE, attackingPossession.getType());
        assertEquals(InPlayPhase.ATTACKING_POSSESSION, attackingPossession.getInPlayPhase());
        // Goalkick outcome
        assertTrue(matchDataElements.get(103) instanceof Statement);
        Statement goalKick = (Statement) matchDataElements.get(103);
        assertEquals(GOAL_KICK, goalKick.getActionOutcome().getType());
        // Foul outcome
        assertTrue(matchDataElements.get(124) instanceof Statement);
        Statement foul = (Statement) matchDataElements.get(124);
        assertEquals(FOUL, foul.getActionOutcome().getType());
        // Freekick
        assertTrue(matchDataElements.get(126) instanceof Directive);
        Directive freeKickDirective = (Directive) matchDataElements.get(126);
        assertEquals(SET_PIECE_EXECUTION_BLOCK, freeKickDirective.getType());
        assertEquals(FREEKICK, freeKickDirective.getSetPiece());
        // Defensive transition
        assertTrue(matchDataElements.get(130) instanceof Directive);
        Directive defensiveTransition = (Directive) matchDataElements.get(130);
        assertEquals(INPLAY_PHASE, defensiveTransition.getType());
        assertEquals(InPlayPhase.DEFENSIVE_TRANSITION, defensiveTransition.getInPlayPhase());
        // 'Triangle' action
        assertTrue(matchDataElements.get(158) instanceof Statement);
        Statement triangleStmt = (Statement) matchDataElements.get(158);
        assertEquals(Triangle, triangleStmt.getAction().getType());
        // Counter attack
        assertTrue(matchDataElements.get(190) instanceof Directive);
        Directive counterAttack = (Directive) matchDataElements.get(190);
        assertEquals(COUNTER_ATTACK, counterAttack.getType());
        // 'Run' action
        assertTrue(matchDataElements.get(192) instanceof Statement);
        Statement runActionStmt = (Statement) matchDataElements.get(192);
        assertEquals(Run, runActionStmt.getAction().getType());
        // '1-2 pass' action
        assertTrue(matchDataElements.get(239) instanceof Statement);
        Statement oneTwoPassActionStmt = (Statement) matchDataElements.get(239);
        assertEquals(OneTwoPass, oneTwoPassActionStmt.getAction().getType());
        // 'Turn' action
        assertTrue(matchDataElements.get(246) instanceof Statement);
        Statement turnActionStmt = (Statement) matchDataElements.get(246);
        assertEquals(Turn, turnActionStmt.getAction().getType());
        // 'Back heel pass' action
        assertTrue(matchDataElements.get(255) instanceof Statement);
        Statement backHeelPassActionStmt = (Statement) matchDataElements.get(255);
        assertEquals(BackHeelPass, backHeelPassActionStmt.getAction().getType());
        // 'Parallel high pass' action
        assertTrue(matchDataElements.get(264) instanceof Statement);
        Statement parallelHighPassActionStmt = (Statement) matchDataElements.get(264);
        assertEquals(ParallelHighPass, parallelHighPassActionStmt.getAction().getType());
        // 'Wide high pass' action
        assertTrue(matchDataElements.get(336) instanceof Statement);
        Statement wideHighPassActionStmt = (Statement) matchDataElements.get(336);
        assertEquals(WideHighPass, wideHighPassActionStmt.getAction().getType());
        // Low cross
        assertTrue(matchDataElements.get(386) instanceof Statement);
        Statement lowCrossActionStmt = (Statement) matchDataElements.get(386);
        assertEquals(LowCross, lowCrossActionStmt.getAction().getType());
        // Pass swapping flank
        assertTrue(matchDataElements.get(439) instanceof Statement);
        Statement passSwappingFlankStmt = (Statement) matchDataElements.get(439);
        assertEquals(SwapSidePass, passSwappingFlankStmt.getAction().getType());
        // Half time
        assertTrue(matchDataElements.get(544) instanceof Directive);
        Directive halfTimeDirective = (Directive) matchDataElements.get(544);
        assertEquals(HALF_TIME, halfTimeDirective.getType());
        // Attempt at goal with header
        assertTrue(matchDataElements.get(677) instanceof Statement);
        Statement headAtGoalStmt = (Statement) matchDataElements.get(677);
        assertEquals(HdSht, headAtGoalStmt.getAction().getType());
        // Substitution
        assertTrue(matchDataElements.get(678) instanceof Directive);
        Directive substitutionDirective = (Directive) matchDataElements.get(678);
        assertEquals(SUBSTITUTION, substitutionDirective.getType());
        assertEquals("L", substitutionDirective.getTeam());
        assertEquals(F, substitutionDirective.getTacticalPosition().getX());
        assertEquals(C, substitutionDirective.getTacticalPosition().getY());
        // 'Cutback' action
        assertTrue(matchDataElements.get(831) instanceof Statement);
        Statement cutBackActionStmt = (Statement) matchDataElements.get(831);
        assertEquals(Cutback, cutBackActionStmt.getAction().getType());
        // Fair play
        assertTrue(matchDataElements.get(855) instanceof Directive);
        Directive fairPlayDirective = (Directive) matchDataElements.get(855);
        assertEquals(FAIR_PLAY, fairPlayDirective.getType());
        // 'Hand pass' by goalkeeper
        assertTrue(matchDataElements.get(992) instanceof Statement);
        Statement handPassAction = (Statement) matchDataElements.get(992);
        assertEquals(HandPass, handPassAction.getAction().getType());
    }
}
