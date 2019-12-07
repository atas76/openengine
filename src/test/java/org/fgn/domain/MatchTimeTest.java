package org.fgn.domain;

import org.fgn.parser.MatchTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatchTimeTest {

    @Test
    public void testTotalSeconds() {
        assertEquals(1685, new MatchTime(28, 5).getTotalSeconds());
    }
}
