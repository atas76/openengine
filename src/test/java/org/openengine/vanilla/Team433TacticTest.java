package org.openengine.vanilla;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Team433TacticTest {

    private Team team = new Team("", TacticsRepository.get(Tactics._4_3_3));

    @Test
    public void testTeamFormation433() {
        assertEquals(11, team.getPlayersNumberInFormation());
        assertNotNull(team.getPlayerByPosition(Position.GK));
        assertNotNull(team.getPlayerByPosition(Position.D_R));
        assertNotNull(team.getPlayerByPosition(Position.D_CR));
        assertNotNull(team.getPlayerByPosition(Position.D_CL));
        assertNotNull(team.getPlayerByPosition(Position.D_L));
        assertNotNull(team.getPlayerByPosition(Position.M_RC));
        assertNotNull(team.getPlayerByPosition(Position.M_C));
        assertNotNull(team.getPlayerByPosition(Position.M_LC));
        assertNotNull(team.getPlayerByPosition(Position.F_RC));
        assertNotNull(team.getPlayerByPosition(Position.F_C));
        assertNotNull(team.getPlayerByPosition(Position.F_LC));
    }
}
