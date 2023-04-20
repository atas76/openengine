package org.openengine.vanilla;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeamTest {

    @Test
    public void testTeamFormation() {
        Team team = new Team("", Tactics._4_4_2);

        Player goalkeeper = team.getPlayerByPosition(Position.GK);
        Player leftMidfielder = team.getPlayerByPosition(Position.M_L);
        Player centralMidfielder = team.getPlayerByPosition(Position.M_CR);

        assertEquals("GK", goalkeeper.getName());
        assertEquals(1, goalkeeper.getShirtNo());
        assertEquals("M_L", leftMidfielder.getName());
        assertEquals(11, leftMidfielder.getShirtNo());
        assertEquals("M_CR", centralMidfielder.getName());
        assertEquals(8, centralMidfielder.getShirtNo());
    }
}
