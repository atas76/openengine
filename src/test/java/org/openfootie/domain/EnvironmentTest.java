package org.openfootie.domain;

import org.junit.Test;
import org.openfootie.openengine.domain.Environment;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class EnvironmentTest {

    private final String TEST_DATA_PATH = "src/test/resources/data";

    @Test
    public void testLoad() {

        Environment environment = new Environment();
        Environment testEnvironment = new Environment(TEST_DATA_PATH);

        // Sanity check for real data
        assertTrue(environment.load());

        // Test data are loaded successfully
        assertTrue(testEnvironment.load());

        assertEquals(32, environment.getClubs().size());
        assertEquals(32, environment.getNations().size());

        assertEquals("England", environment.getClub("Chelsea").getNation());
        assertEquals(2.9, environment.getClub("Chelsea").getStrength(), 0.01);
        assertEquals("Chelsea", environment.getClub(17).getName());

        assertEquals(2.39, environment.getNation("Poland").getStrength(), 0.01);
        assertEquals("Poland", environment.getNation(18).getName());
    }
}
