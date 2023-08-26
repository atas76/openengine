package org.openengine.vanilla;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Team442TacticTest {

    private Team team = new Team("", TacticsRepository.get(Tactics._4_4_2));

    @Test
    public void testTeamFormation442() {
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

    @Test
    public void testRightDefenderActions() {
        Player rightDefender = team.getPlayerByPosition(Position.D_R);

        List<Action> actions = rightDefender.getPermissibleActions();
        Action goalkeeperPass = actions.get(0);
        Action centreRightDefenderPass = actions.get(1);
        Action rightMidfielderPass = actions.get(2);
        Action centreRightMidfielderPass = actions.get(3);
        Action centreRightForwardPass = actions.get(4);

        assertEquals(5, actions.size());
        assertEquals(1.0, goalkeeperPass.getGeometryFactor(), 0.0);
        assertEquals(Position.GK, goalkeeperPass.getTarget().getPosition());
        assertEquals(1.0, centreRightDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CR, centreRightDefenderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), rightMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_R, rightMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreRightMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_CR, centreRightMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreRightForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CR, centreRightForwardPass.getTarget().getPosition());
    }

    @Test
    public void testLeftDefenderActions() {
        Player leftDefender = team.getPlayerByPosition(Position.D_L);

        List<Action> actions = leftDefender.getPermissibleActions();
        Action goalkeeperPass = actions.get(0);
        Action centreLeftDefenderPass = actions.get(1);
        Action centreLeftMidfielderPass = actions.get(2);
        Action leftMidfielderPass = actions.get(3);
        Action centreLeftForwardPass = actions.get(4);

        assertEquals(5, actions.size());
        assertEquals(1.0, goalkeeperPass.getGeometryFactor(), 0.0);
        assertEquals(Position.GK, goalkeeperPass.getTarget().getPosition());
        assertEquals(1.0, centreLeftDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CL, centreLeftDefenderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), leftMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_L, leftMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreLeftMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_CL, centreLeftMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreLeftForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CL, centreLeftForwardPass.getTarget().getPosition());
    }

    @Test
    public void testCentreRightDefenderActions() {
        Player centreRightDefender = team.getPlayerByPosition(Position.D_CR);

        List<Action> actions = centreRightDefender.getPermissibleActions();
        Action goalkeeperPass = actions.get(0);
        Action rightDefenderPass = actions.get(1);
        Action centreLeftDefenderPass = actions.get(2);
        Action rightMidfielderPass = actions.get(3);
        Action centreRightMidfielderPass = actions.get(4);
        Action centreRightForwardPass = actions.get(5);

        assertEquals(6, actions.size());
        assertEquals(1.0, goalkeeperPass.getGeometryFactor(), 0.0);
        assertEquals(Position.GK, goalkeeperPass.getTarget().getPosition());
        assertEquals(1.0, rightDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_R, rightDefenderPass.getTarget().getPosition());
        assertEquals(1.0, centreLeftDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CL, centreLeftDefenderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), rightMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_R, rightMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), centreRightMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_CR, centreRightMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4), centreRightForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CR, centreRightForwardPass.getTarget().getPosition());
    }

    @Test
    public void testCentreLeftDefenderActions() {
        Player centreLeftDefender = team.getPlayerByPosition(Position.D_CL);

        List<Action> actions = centreLeftDefender.getPermissibleActions();
        Action goalkeeperPass = actions.get(0);
        Action centreRightDefenderPass = actions.get(1);
        Action leftDefenderPass = actions.get(2);
        Action centreLeftMidfielderPass = actions.get(3);
        Action leftMidfielderPass = actions.get(4);
        Action centreLeftForwardPass = actions.get(5);

        assertEquals(6, actions.size());
        assertEquals(1.0, goalkeeperPass.getGeometryFactor(), 0.0);
        assertEquals(Position.GK, goalkeeperPass.getTarget().getPosition());
        assertEquals(1.0, leftDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_L, leftDefenderPass.getTarget().getPosition());
        assertEquals(1.0, centreRightDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CR, centreRightDefenderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), centreLeftMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_CL, centreLeftMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), leftMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_L, leftMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4), centreLeftForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CL, centreLeftForwardPass.getTarget().getPosition());
    }

    @Test
    public void testRightMidfielderActions() {
        Player rightMidfielder = team.getPlayerByPosition(Position.M_R);

        List<Action> actions = rightMidfielder.getPermissibleActions();
        Action centreRightMidfielderPass = actions.get(0);
        Action centreRightForwardPass = actions.get(1);

        assertEquals(2, actions.size());
        assertEquals(1.0, centreRightMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_CR, centreRightMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreRightForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CR, centreRightForwardPass.getTarget().getPosition());
    }

    @Test
    public void testLeftMidfielderActions() {
        Player leftMidfielder = team.getPlayerByPosition(Position.M_L);

        List<Action> actions = leftMidfielder.getPermissibleActions();
        Action centreLeftMidfielderPass = actions.get(0);
        Action centreLeftForwardPass = actions.get(1);

        assertEquals(2, actions.size());
        assertEquals(1.0, centreLeftMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_CL, centreLeftMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreLeftForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CL, centreLeftForwardPass.getTarget().getPosition());
    }

    @Test
    public void testCentreRightMidfielderActions() {
        Player centreRightMidfielder = team.getPlayerByPosition(Position.M_CR);

        List<Action> actions = centreRightMidfielder.getPermissibleActions();
        Action rightMidfielderPass = actions.get(0);
        Action centreLeftMidfielderPass = actions.get(1);
        Action centreRightForwardPass = actions.get(2);

        assertEquals(3, actions.size());
        assertEquals(1.0, rightMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_R, rightMidfielderPass.getTarget().getPosition());
        assertEquals(1.0, centreLeftMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_CL, centreLeftMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), centreRightForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CR, centreRightForwardPass.getTarget().getPosition());
    }

    @Test
    public void testCentreLeftMidfielderActions() {
        Player centreLeftMidfielder = team.getPlayerByPosition(Position.M_CL);

        List<Action> actions = centreLeftMidfielder.getPermissibleActions();
        Action centreRightMidfielderPass = actions.get(0);
        Action leftMidfielderPass = actions.get(1);
        Action centreLeftForwardPass = actions.get(2);

        assertEquals(3, actions.size());
        assertEquals(1.0, leftMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_L, leftMidfielderPass.getTarget().getPosition());
        assertEquals(1.0, centreRightMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_CR, centreRightMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), centreLeftForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CL, centreLeftForwardPass.getTarget().getPosition());
    }

    @Test
    public void testCentreRightForwardActions() {
        Player centreRightForward = team.getPlayerByPosition(Position.F_CR);

        List<Action> actions = centreRightForward.getPermissibleActions();
        Action centreLeftForwardPass = actions.get(0);

        assertEquals(1, actions.size());
        assertEquals(1.0, centreLeftForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CL, centreLeftForwardPass.getTarget().getPosition());
    }

    @Test
    public void testCentreLeftForwardActions() {
        Player centreLeftForward = team.getPlayerByPosition(Position.F_CL);

        List<Action> actions = centreLeftForward.getPermissibleActions();
        Action centreRightForwardPass = actions.get(0);

        assertEquals(1, actions.size());
        assertEquals(1.0, centreRightForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_CR, centreRightForwardPass.getTarget().getPosition());
    }
}
