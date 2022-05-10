package org.ttn;

import org.junit.Before;
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
    public void testReadMatchData() throws IOException {
        List<String> matchData = Files.readAllLines(matchSampleResource);

        assertEquals(2, matchData.size());
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

        assertEquals(2, matchDataElements.size());
        assertTrue(matchDataElements.get(0) instanceof Directive);
        assertTrue(matchDataElements.get(1) instanceof Statement);
    }

    @Test
    public void testParseMatchFile() throws IOException, ParserException {
        List<MatchDataElement> matchDataElements =
                new Parser().parse(Files.lines(matchSampleResource).collect(Collectors.toList()));

        assertEquals(2, matchDataElements.size());
        assertTrue(matchDataElements.get(0) instanceof Directive);
        assertTrue(matchDataElements.get(1) instanceof Statement);
    }
}
