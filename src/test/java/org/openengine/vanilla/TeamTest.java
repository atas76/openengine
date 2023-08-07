package org.openengine.vanilla;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @Test
    public void testTeamFormation442() {
        Team team = new Team("", TacticsLibrary.tacticsRepository.get(Tactics._4_4_2));

        assertEquals(11, team.getPlayersNumberInFormation());
        assertNotNull(team.getPlayerByPosition(Position.GK));
        assertNotNull(team.getPlayerByPosition(Position.D_R));
        assertNotNull(team.getPlayerByPosition(Position.D_CR));
        assertNotNull(team.getPlayerByPosition(Position.D_CL));
        assertNotNull(team.getPlayerByPosition(Position.D_L));
        assertNotNull(team.getPlayerByPosition(Position.M_R));
        assertNotNull(team.getPlayerByPosition(Position.M_CR));
        assertNotNull(team.getPlayerByPosition(Position.M_CL));
        assertNotNull(team.getPlayerByPosition(Position.M_L));
        assertNotNull(team.getPlayerByPosition(Position.F_CR));
        assertNotNull(team.getPlayerByPosition(Position.F_CL));
    }
}
