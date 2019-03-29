package org.openfootie.openengine.util;

import org.junit.Test;
import org.openfootie.openengine.domain.Environment;
import org.openfootie.openengine.util.analysis.MatchSample;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class MatchSampleAnalysisTest {

    private final String TEST_DATA_PATH = "src/test/resources/data";
    private final String TEST_SAMPLES_DATA_PATH = "src/test/resources/data/samples";
    private final String TEST_SAMPLE_MATCH_FILE_PATH = "src/test/resources/data/samples/CL_Atletico_Juventus.csv";

    @Test
    public void testLoad() {

        // We will actually need the environment later
        Environment environment = new Environment(TEST_DATA_PATH);

        // Test data are loaded successfully
        assertTrue(environment.load());

        MatchSample matchParticipation = new MatchSample(TEST_SAMPLE_MATCH_FILE_PATH);

        assertEquals(28, matchParticipation.getMatchParticipation().size());
    }
}
