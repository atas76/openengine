package org.openfootie.domain;

import org.junit.Test;
import org.openfootie.openengine.domain.Environment;

import static junit.framework.TestCase.assertTrue;

public class EnvironmentTest {

    private static Environment environment;

    private final String TEST_DATA_PATH = "src/test/resources/data";

    @Test
    public void testLoad() {

        Environment environment = new Environment();
        Environment testEnvironment = new Environment(TEST_DATA_PATH);

        // Sanity check for real data
        assertTrue(environment.load());

        // Test data are loaded successfully
        assertTrue(testEnvironment.load());
    }
}
