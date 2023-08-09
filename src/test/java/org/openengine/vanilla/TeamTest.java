package org.openengine.vanilla;

import org.junit.Test;

import java.util.List;

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

    @Test
    public void testGoalkeeperActions442() {
        Team team = new Team("", TacticsLibrary.tacticsRepository.get(Tactics._4_4_2));
        Player goalkeeper = team.getGoalkeeper();

        List<Action> actions = goalkeeper.getPermissibleActions();
        Action rightDefenderPass = actions.get(0);
        Action centreRightDefenderPass = actions.get(1);
        Action centreLeftDefenderPass = actions.get(2);
        Action leftDefenderPass = actions.get(3);
        Action rightMidfielderPass = actions.get(4);
        Action centreRightMidfielderPass = actions.get(5);
        Action centreLeftMidfielderPass = actions.get(6);
        Action leftMidfielderPass = actions.get(7);
        Action centreRightForwardPass = actions.get(8);
        Action centreLeftForwardPass = actions.get(9);

        assertEquals(10, actions.size());
        assertEquals(1.0, rightDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_R, rightDefenderPass.getTarget().getPosition());
        assertEquals(1.0, centreRightDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CR, centreRightDefenderPass.getTarget().getPosition());
        assertEquals(1.0, centreLeftDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CL, centreLeftDefenderPass.getTarget().getPosition());
        assertEquals(1.0, leftDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_L, leftDefenderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), rightMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_R, rightMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), centreRightMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_CR, centreRightMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), centreLeftMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_CL, centreLeftMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), leftMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_L, leftMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4), centreRightForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CR, centreRightForwardPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4), centreLeftForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CL, centreLeftForwardPass.getTarget().getPosition());
    }
}
