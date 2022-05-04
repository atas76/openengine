package org.ttn;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
}
