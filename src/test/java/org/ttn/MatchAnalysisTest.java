package org.ttn;

import org.junit.Before;
import org.junit.Test;
import org.ttn.engine.agent.ActionType;
import org.ttn.engine.input.TacticalPosition;
import org.ttn.lexan.Scanner;
import org.ttn.lexan.exceptions.ScannerException;
import org.ttn.parser.Parser;
import org.ttn.parser.exceptions.ParserException;
import org.ttn.parser.output.Directive;
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
import static org.ttn.engine.rules.SetPiece.KICK_OFF;
import static org.ttn.engine.rules.SetPiece.THROW_IN;
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
        assertEquals(POSSESSION_CHAIN_BLOCK, possessionDirective.getType());
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
        assertEquals(ATTACK_CHAIN_BLOCK, attackBlockDirective.getType());
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
        assertEquals(BUILDUP_PRESSURE_BLOCK, pressingDirective.getType());
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
        assertTrue(matchDataElements.get(21) instanceof Statement); // Pass action
        assertTrue(matchDataElements.get(22) instanceof Statement); // Diagonal pass
        assertTrue(matchDataElements.get(28) instanceof Directive); // Transition
        assertTrue(matchDataElements.get(29) instanceof Statement); // Move action
        assertTrue(matchDataElements.get(40) instanceof Statement); // Intermediate action outcome
        assertTrue(matchDataElements.get(41) instanceof Directive); // Corner kick
        assertTrue(matchDataElements.get(42) instanceof Statement); // Cross action
        assertTrue(matchDataElements.get(43) instanceof Statement); // Goalkeeper out of penalty area possession
        assertTrue(matchDataElements.get(45) instanceof Statement); // Possession chain summary
        assertTrue(matchDataElements.get(48) instanceof Statement); // Forward pass action
        assertTrue(matchDataElements.get(49) instanceof Statement); // Action context
        assertTrue(matchDataElements.get(51) instanceof Statement); // 'Wide pass' action
        assertTrue(matchDataElements.get(54) instanceof Statement); // 'Double' possession change in both outcomes
        assertTrue(matchDataElements.get(55) instanceof Directive); // Ball recovery
        assertTrue(matchDataElements.get(56) instanceof Statement); // Throw-in action outcome
        assertTrue(matchDataElements.get(59) instanceof Statement); // Dribble action
        assertTrue(matchDataElements.get(69) instanceof Statement); // 'High pass' action
        assertTrue(matchDataElements.get(72) instanceof Statement); // Clearance action
        assertTrue(matchDataElements.get(89) instanceof Directive); // Attacking possession
        assertTrue(matchDataElements.get(103) instanceof Statement); // Goalkick outcome
        assertTrue(matchDataElements.get(124) instanceof Statement); // Foul outcome
        assertTrue(matchDataElements.get(126) instanceof Directive); // Freekick
        assertTrue(matchDataElements.get(130) instanceof Directive); // Defensive transition
        assertTrue(matchDataElements.get(158) instanceof Statement); // 'Triangle' action
        assertTrue(matchDataElements.get(190) instanceof Directive); // Counter attack
        assertTrue(matchDataElements.get(192) instanceof Statement); // 'Run' action
        assertTrue(matchDataElements.get(192) instanceof Statement); // 'Run' action
        assertTrue(matchDataElements.get(239) instanceof Statement); // '1-2 pass' action
        assertTrue(matchDataElements.get(246) instanceof Statement); // 'Turn' action
        assertTrue(matchDataElements.get(255) instanceof Statement); // 'Back heel pass' action
        assertTrue(matchDataElements.get(264) instanceof Statement); // 'Parallel high pass' action
        assertTrue(matchDataElements.get(336) instanceof Statement); // 'Wide high pass' action
        assertTrue(matchDataElements.get(386) instanceof Statement); // Low cross
        assertTrue(matchDataElements.get(439) instanceof Statement); // Pass swapping flank
        assertTrue(matchDataElements.get(544) instanceof Directive); // Half time
        assertTrue(matchDataElements.get(677) instanceof Statement); // Attempt at goal with header
        assertTrue(matchDataElements.get(678) instanceof Directive); // Substitution
        assertTrue(matchDataElements.get(831) instanceof Statement); // 'Cutback' action
        assertTrue(matchDataElements.get(855) instanceof Directive); // Fair play
        assertTrue(matchDataElements.get(992) instanceof Statement); // 'Hand pass' by goalkeeper
    }
}
