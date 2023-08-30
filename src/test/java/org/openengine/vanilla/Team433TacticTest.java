package org.openengine.vanilla;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void testGoalkeeperActions433() {
        Player goalkeeper = team.getGoalkeeper();

        List<Action> actions = goalkeeper.getPermissibleActions();
        Action rightDefenderPass = actions.get(0);
        Action centreRightDefenderPass = actions.get(1);
        Action centreLeftDefenderPass = actions.get(2);
        Action leftDefenderPass = actions.get(3);
        Action rightCentreMidfielderPass = actions.get(4);
        Action centreMidfielderPass = actions.get(5);
        Action leftCentreMidfielderPass = actions.get(6);
        Action rightCentreForwardPass = actions.get(7);
        Action centreForwardPass = actions.get(8);
        Action leftCentreForwardPass = actions.get(9);

        assertEquals(10, actions.size());
        assertEquals(1.0, rightDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_R, rightDefenderPass.getTarget().getPosition());
        assertEquals(1.0, centreRightDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CR, centreRightDefenderPass.getTarget().getPosition());
        assertEquals(1.0, centreLeftDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CL, centreLeftDefenderPass.getTarget().getPosition());
        assertEquals(1.0, leftDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_L, leftDefenderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), rightCentreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_RC, rightCentreMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), centreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_C, centreMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), leftCentreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_LC, leftCentreMidfielderPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4), rightCentreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_RC, rightCentreForwardPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4), centreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_C, centreForwardPass.getTarget().getPosition());
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4), leftCentreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_LC, leftCentreForwardPass.getTarget().getPosition());
    }

    @Test
    public void testRightDefenderActions() {
        Player rightDefender = team.getPlayerByPosition(Position.D_R);

        List<Action> actions = rightDefender.getPermissibleActions();
        Action goalkeeperPass = actions.get(0);
        Action centreRightDefenderPass = actions.get(1);
        Action rightCentreMidfielderPass = actions.get(2);
        Action centreMidfielderPass = actions.get(3);
        Action rightCentreForwardPass = actions.get(4);
        Action centreForwardPass = actions.get(5);

        assertEquals(6, actions.size());
        assertEquals(1.0, goalkeeperPass.getGeometryFactor(), 0.0);
        assertEquals(Position.GK, goalkeeperPass.getTarget().getPosition());
        //
        assertEquals(1.0, centreRightDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CR, centreRightDefenderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR,
                rightCentreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_RC, rightCentreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_C, centreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR,
                rightCentreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_RC, rightCentreForwardPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_C, centreForwardPass.getTarget().getPosition());
    }

    @Test
    public void testLeftDefenderActions() {
        Player leftDefender = team.getPlayerByPosition(Position.D_L);

        List<Action> actions = leftDefender.getPermissibleActions();
        Action goalkeeperPass = actions.get(0);
        Action centreLeftDefenderPass = actions.get(1);
        Action centreMidfielderPass = actions.get(2);
        Action leftCentreMidfielderPass = actions.get(3);
        Action centreForwardPass = actions.get(4);
        Action leftCentreForwardPass = actions.get(5);

        assertEquals(6, actions.size());
        assertEquals(1.0, goalkeeperPass.getGeometryFactor(), 0.0);
        assertEquals(Position.GK, goalkeeperPass.getTarget().getPosition());
        //
        assertEquals(1.0, centreLeftDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CL, centreLeftDefenderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_C, centreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR,
                leftCentreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_LC, leftCentreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_C, centreForwardPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR,
                leftCentreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_LC, leftCentreForwardPass.getTarget().getPosition());
    }

    @Test
    public void testCentreRightDefenderActions() {
        Player centreRightDefender = team.getPlayerByPosition(Position.D_CR);

        List<Action> actions = centreRightDefender.getPermissibleActions();
        Action goalkeeperPass = actions.get(0);
        Action rightDefenderPass = actions.get(1);
        Action centreLeftDefenderPass = actions.get(2);
        Action rightCentreMidfielderPass = actions.get(3);
        Action centreMidfielderPass = actions.get(4);
        Action rightCentreForwardPass = actions.get(5);
        Action centreForwardPass = actions.get(6);

        assertEquals(7, actions.size());
        assertEquals(1.0, goalkeeperPass.getGeometryFactor(), 0.0);
        assertEquals(Position.GK, goalkeeperPass.getTarget().getPosition());
        //
        assertEquals(1.0, rightDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_R, rightDefenderPass.getTarget().getPosition());
        //
        assertEquals(1.0, centreLeftDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CL, centreLeftDefenderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), rightCentreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_RC, rightCentreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR,
                centreMidfielderPass.getGeometryFactor() , 0.0);
        assertEquals(Position.M_C, centreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4), rightCentreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_RC, rightCentreForwardPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR,
                centreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_C, centreForwardPass.getTarget().getPosition());
    }

    @Test
    public void testCentreLeftDefenderActions() {
        Player centreLeftDefender = team.getPlayerByPosition(Position.D_CL);

        List<Action> actions = centreLeftDefender.getPermissibleActions();
        Action goalkeeperPass = actions.get(0);
        Action centreRightDefenderPass = actions.get(1);
        Action leftDefenderPass = actions.get(2);
        Action centreMidfielderPass = actions.get(3);
        Action centreLeftCentreMidfielderPass = actions.get(4);
        Action centreForwardPass = actions.get(5);
        Action centreLeftForwardPass = actions.get(6);

        assertEquals(7, actions.size());
        assertEquals(1.0, goalkeeperPass.getGeometryFactor(), 0.0);
        assertEquals(Position.GK, goalkeeperPass.getTarget().getPosition());
        //
        assertEquals(1.0, centreRightDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_CR, centreRightDefenderPass.getTarget().getPosition());
        //
        assertEquals(1.0, leftDefenderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.D_L, leftDefenderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR,
                centreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_C, centreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), centreLeftCentreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_LC, centreLeftCentreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR,
                centreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_C, centreForwardPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(4), centreLeftForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_LC, centreLeftForwardPass.getTarget().getPosition());
    }

    @Test
    public void testRightCentreMidfielderActions() {
        Player rightCentreMidfielder = team.getPlayerByPosition(Position.M_RC);

        List<Action> actions = rightCentreMidfielder.getPermissibleActions();
        Action centreMidfielderPass = actions.get(0);
        Action rightCentreForwardPass = actions.get(1);
        Action centreForwardPass = actions.get(2);

        assertEquals(3 , actions.size());
        //
        assertEquals(1.0, centreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_C, centreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), rightCentreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_RC, rightCentreForwardPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2) * State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5,
                centreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_C, centreForwardPass.getTarget().getPosition());
    }

    @Test
    public void testCentreMidfielderActions() {
        Player centreMidfielder = team.getPlayerByPosition(Position.M_C);

        List<Action> actions = centreMidfielder.getPermissibleActions();
        Action rightCentreMidfielderPass = actions.get(0);
        Action leftCentreMidfielderPass = actions.get(1);
        Action rightCentreForwardPass = actions.get(2);
        Action centreForwardPass = actions.get(3);
        Action leftCentreForwardPass = actions.get(4);

        assertEquals(5, actions.size());
        //
        assertEquals(1.0, rightCentreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_RC, rightCentreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(1.0, leftCentreMidfielderPass.getGeometryFactor(), 0.0);
        assertEquals(Position.M_LC, leftCentreMidfielderPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), rightCentreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_RC, rightCentreForwardPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), centreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_C, centreForwardPass.getTarget().getPosition());
        //
        assertEquals(State.DISTANCE_UNIT_FACTORS.get(2), leftCentreForwardPass.getGeometryFactor(), 0.0);
        assertEquals(Position.F_LC, leftCentreForwardPass.getTarget().getPosition());
    }
}
