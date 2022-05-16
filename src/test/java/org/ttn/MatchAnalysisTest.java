package org.ttn;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatchAnalysisTest {

    private final String MATCH_SAMPLE_RESOURCE_NAME = "src/test/resources/data/ttn/cl_sample.ttn";

    private Path matchSampleResource;

    @Before
    public void setUp() {
        matchSampleResource = Paths.get(MATCH_SAMPLE_RESOURCE_NAME);
    }

    @Test
    @Ignore
    public void testReadMatchData() throws IOException {
        List<String> matchData = Files.readAllLines(matchSampleResource);

        assertEquals(2, matchData.size());
    }

    @Test
    @Ignore
    public void testParseMatchDataElements() throws IOException {
        List<MatchDataElement> matchDataElements = Files.lines(matchSampleResource).map(line -> {
            try {
                return new Parser().parseTokenList(new Scanner(line).scan());
            } catch (ScannerException | ParserException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        assertEquals(2, matchDataElements.size());
        assertTrue(matchDataElements.get(0) instanceof Directive);
        assertTrue(matchDataElements.get(1) instanceof Statement);
    }

    @Test
    public void testParseMatchFile() throws IOException, ParserException {
        List<MatchDataElement> matchDataElements =
                new Parser().parse(Files.lines(matchSampleResource).collect(Collectors.toList()));

        assertEquals(256, matchDataElements.size());
        assertTrue(matchDataElements.get(0) instanceof Directive); // Kick-off
        assertTrue(matchDataElements.get(1) instanceof Statement); // Default action type
        assertTrue(matchDataElements.get(2) instanceof Directive); // Possession
        assertTrue(matchDataElements.get(3) instanceof Directive); // Interpretive directive
        assertTrue(matchDataElements.get(4) instanceof Statement); // Long pass action
        assertTrue(matchDataElements.get(6) instanceof Statement); // Action parameters
        assertTrue(matchDataElements.get(7) instanceof Directive); // Attack
        assertTrue(matchDataElements.get(8) instanceof Statement); // Bounceoff action
        assertTrue(matchDataElements.get(9) instanceof Directive); // Set piece
        assertTrue(matchDataElements.get(10) instanceof Statement); // Shot action
        assertTrue(matchDataElements.get(11) instanceof Directive); // Break
        assertTrue(matchDataElements.get(12) instanceof Directive); // Throw-in
        assertTrue(matchDataElements.get(13) instanceof Statement); // In-play default action
        assertTrue(matchDataElements.get(14) instanceof Statement); // Back pass action
        assertTrue(matchDataElements.get(15) instanceof Statement); // Possession change
        assertTrue(matchDataElements.get(17) instanceof Directive); // Pressing
        assertTrue(matchDataElements.get(18) instanceof Directive); // Possessor
        assertTrue(matchDataElements.get(19) instanceof Statement); // Parallel pass
        assertTrue(matchDataElements.get(20) instanceof Statement); // Back pass to goalkeeper
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
    }
}
