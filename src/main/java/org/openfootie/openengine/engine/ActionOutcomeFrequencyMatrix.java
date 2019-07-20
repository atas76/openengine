package org.openfootie.openengine.engine;

import org.openfootie.openengine.engine.util.FrequencyMatrixUtil;

import java.util.*;
import java.util.stream.Collectors;

import static org.openfootie.openengine.engine.Coordinates.*;
import static org.openfootie.openengine.engine.Action.*;
import static org.openfootie.openengine.engine.SetPiece.*;
import static org.openfootie.openengine.engine.ShotOutcome.*;

public class ActionOutcomeFrequencyMatrix {

    private Map<ActionInstance, List<OutcomeWeight>> data = new HashMap<>();

    // TODO Refactor to enum or premature optimization maybe?

    private final static ActionInstance BACK_PASS_AW = new ActionInstance(BackPass, Aw);
    private final static ActionInstance BACK_PASS_A = new ActionInstance(BackPass, A);

    private final static ActionInstance HOLD_GK = new ActionInstance(Hold, GK);
    private final static ActionInstance HOLD_DG = new ActionInstance(Hold, Dg);
    private final static ActionInstance HOLD_D = new ActionInstance(Hold, D);
    private final static ActionInstance HOLD_D_SP = new ActionInstance(Hold, D_SP);
    private final static ActionInstance HOLD_AW = new ActionInstance(Hold, Aw);
    private final static ActionInstance HOLD_A = new ActionInstance(Hold, A);

    private final static ActionInstance LOW_C = new ActionInstance(Default, C);

    private final static ActionInstance DEFAULT_D_T = new ActionInstance(Default, D_T);
    private final static ActionInstance DEFAULT_A_T = new ActionInstance(Default, A_T);
    private final static ActionInstance DEFAULT_D_SP = new ActionInstance(Default, D_SP);

    private final static ActionInstance LONG_DG = new ActionInstance(Long, Dg);
    private final static ActionInstance LONG_DP = new ActionInstance(Long, Dp);
    private final static ActionInstance LONG_D = new ActionInstance(Long, D);
    private final static ActionInstance LONG_A = new ActionInstance(Long, A);

    private final static ActionInstance LOW_CROSS_AW = new ActionInstance(LowCross, Aw);

    private final static ActionInstance LATERAL_PASS_A = new ActionInstance(LateralPass, A);

    private final static ActionInstance FORWARD_PASS_D = new ActionInstance(ForwardPass, D);
    private final static ActionInstance FORWARD_PASS_AW = new ActionInstance(ForwardPass, Aw);
    private final static ActionInstance FORWARD_PASS_A = new ActionInstance(ForwardPass, A);

    private final static ActionInstance CROSS_AW = new ActionInstance(Cross, Aw);
    private final static ActionInstance CROSS_AP = new ActionInstance(Cross, Ap);
    private final static ActionInstance CROSS_C  = new ActionInstance(Cross, C);

    private final static ActionInstance CROSS_PASS_AP = new ActionInstance(CrossPass, Ap);
    private final static ActionInstance CROSS_PASS_AW = new ActionInstance(CrossPass, Aw);

    private final static ActionInstance MOVE_D = new ActionInstance(Move, D);
    private final static ActionInstance MOVE_AW = new ActionInstance(Move, Aw);
    private final static ActionInstance MOVE_A = new ActionInstance(Move, A);

    private final static ActionInstance DRIBBLE_D = new ActionInstance(Dribble, D);
    private final static ActionInstance DRIBBLE_AP = new ActionInstance(Dribble, Ap);

    private final static ActionInstance SHOOT_A = new ActionInstance(Shoot, A);
    private final static ActionInstance SHOOT_AP = new ActionInstance(Shoot, Ap);
    private final static ActionInstance SHOOT_AP_FTA = new ActionInstance(Shoot, ApFTA);

    public Outcome getOutcome(Action action, State state) {

        List<OutcomeWeight> candidateOutcomeWeights = data.get(new ActionInstance(action, state.getBall()));
        List<Integer> weights = candidateOutcomeWeights.stream().map(OutcomeWeight::getWeight).collect(Collectors.toList());

        return candidateOutcomeWeights.get(FrequencyMatrixUtil.getWeightedElement(weights)).getOutcome();
    }

    ActionOutcomeFrequencyMatrix() {

        data.put(BACK_PASS_AW, new ArrayList<>());
        data.put(BACK_PASS_A, new ArrayList<>());

        data.put(HOLD_GK, new ArrayList<>());
        data.put(HOLD_DG, new ArrayList<>());
        data.put(HOLD_D, new ArrayList<>());
        data.put(HOLD_D_SP, new ArrayList<>());
        data.put(HOLD_AW, new ArrayList<>());
        data.put(HOLD_A, new ArrayList<>());

        data.put(LOW_C, new ArrayList<>());

        data.put(DEFAULT_D_T, new ArrayList<>());
        data.put(DEFAULT_A_T, new ArrayList<>());
        data.put(DEFAULT_D_SP, new ArrayList<>());

        data.put(LONG_DG, new ArrayList<>());
        data.put(LONG_DP, new ArrayList<>());
        data.put(LONG_D, new ArrayList<>());
        data.put(LONG_A, new ArrayList<>());

        data.put(LOW_CROSS_AW, new ArrayList<>());

        data.put(LATERAL_PASS_A, new ArrayList<>());

        data.put(FORWARD_PASS_D, new ArrayList<>());
        data.put(FORWARD_PASS_AW, new ArrayList<>());
        data.put(FORWARD_PASS_A, new ArrayList<>());

        data.put(CROSS_AW, new ArrayList<>());
        data.put(CROSS_AP, new ArrayList<>());
        data.put(CROSS_C, new ArrayList<>());

        data.put(CROSS_PASS_AP, new ArrayList<>());
        data.put(CROSS_PASS_AW, new ArrayList<>());

        data.put(MOVE_D, new ArrayList<>());
        data.put(MOVE_AW, new ArrayList<>());
        data.put(MOVE_A, new ArrayList<>());

        data.put(DRIBBLE_D, new ArrayList<>());
        data.put(DRIBBLE_AP, new ArrayList<>());

        data.put(SHOOT_A, new ArrayList<>());
        data.put(SHOOT_AP, new ArrayList<>());
        data.put(SHOOT_AP_FTA, new ArrayList<>());

        data.get(HOLD_D_SP).addAll(Arrays.asList(
                new ActionOutcomeWeight(D, true, 3),
                new ActionOutcomeWeight(F, D_SP, false, 1),
                new ActionOutcomeWeight(Dg, true, 1)
        ));

        data.get(HOLD_D).addAll(Arrays.asList(
                new ActionOutcomeWeight(D_T, false, 2),
                new ActionOutcomeWeight(D, true, 55),
                new ActionOutcomeWeight(D, false, 4),
                new ActionOutcomeWeight(Dg, true, 8),
                new ActionOutcomeWeight(D_T, true, 2),
                new ActionOutcomeWeight(A, true, 3),
                new ActionOutcomeWeight(H, D_SP,false, 1),
                new ActionOutcomeWeight(F, D_SP, true, 1)
        ));

        data.get(DEFAULT_D_T).addAll(Arrays.asList(
               new ActionOutcomeWeight(D_T, false, 1),
               new ActionOutcomeWeight(D, false, 1),
               new ActionOutcomeWeight(A, false, 1),
               new ActionOutcomeWeight(D, true, 1)
        ));

        data.get(LONG_D).addAll(Arrays.asList(
                new ActionOutcomeWeight(A_T, false, 1),
                new ActionOutcomeWeight(Aw, true, 5),
                new ActionOutcomeWeight(O, D_SP,false, 2),
                new ActionOutcomeWeight(D, false, 5),
                new ActionOutcomeWeight(D, true, 6),
                new ActionOutcomeWeight(Dg, false, 1),
                new ActionOutcomeWeight(A, true, 3),
                new ActionOutcomeWeight(D_T, false, 1),
                new ActionOutcomeWeight(A_T, true, 1)
        ));

        data.get(LOW_CROSS_AW).addAll(Arrays.asList(
                new ActionOutcomeWeight(Dp, false, 1),
                new ActionOutcomeWeight(Aw, true, 1)
        ));

        data.get(LONG_DP).addAll(Arrays.asList(
                new ActionOutcomeWeight(D, false, 1),
                new ActionOutcomeWeight(D, true, 1)
        ));

        data.get(FORWARD_PASS_D).addAll(Arrays.asList(
                new ActionOutcomeWeight(Aw, true, 5),
                new ActionOutcomeWeight(GK, true, 1),
                new ActionOutcomeWeight(D, true, 12),
                new ActionOutcomeWeight(A, true, 9),
                new ActionOutcomeWeight(O, D_SP,false, 1),
                new ActionOutcomeWeight(Ap, true, 2),
                new ActionOutcomeWeight(D, false, 1),
                new ActionOutcomeWeight(C, true, 1),
                new ActionOutcomeWeight(F, D_SP, true, 1),
                new ActionOutcomeWeight(A_T, true, 1)
        ));

        data.get(CROSS_AW).addAll(Arrays.asList(
                new ActionOutcomeWeight(A_T, true, 1),
                new ActionOutcomeWeight(GK, false, 3),
                new ActionOutcomeWeight(D, false, 1)
        ));

        data.get(DEFAULT_A_T).addAll(Arrays.asList(
                new ActionOutcomeWeight(Aw, true, 3)
        ));

        data.get(HOLD_AW).addAll(Arrays.asList(
                new ActionOutcomeWeight(Aw, true, 3),
                new ActionOutcomeWeight(A, false, 2),
                new ActionOutcomeWeight(A, true, 2),
                new ActionOutcomeWeight(D, false, 2)
        ));

        data.get(BACK_PASS_AW).addAll(Arrays.asList(
                new ActionOutcomeWeight(D, true, 3)
        ));

        data.get(MOVE_D).addAll(Arrays.asList(
                new ActionOutcomeWeight(A_T, true, 1),
                new ActionOutcomeWeight(D, false, 2),
                new ActionOutcomeWeight(D, true, 9),
                new ActionOutcomeWeight(Aw, true, 5),
                new ActionOutcomeWeight(A, true, 5),
                new ActionOutcomeWeight(F, D_SP, true, 1)
        ));

        data.get(FORWARD_PASS_AW).addAll(Arrays.asList(
                new ActionOutcomeWeight(Ap, true, 2),
                new ActionOutcomeWeight(O, Dg, false, 1)
        ));

        data.get(LONG_DG).addAll(Arrays.asList(
                new ActionOutcomeWeight(D, true, 5),
                new ActionOutcomeWeight(D_T, false, 2),
                new ActionOutcomeWeight(Dg, false, 1),
                new ActionOutcomeWeight(D, false, 3)
        ));

        // Attempts at goal

        data.get(SHOOT_A).addAll(Arrays.asList(
                new ShotOutcomeWeight(false, BLOCK, 0, A_T, true, 1),
                new ShotOutcomeWeight(false, BLOCK, 0, D, false, 1),
                new ShotOutcomeWeight(false, GOAL_KICK_HIGH, 0, GK, false, 2),
                new ShotOutcomeWeight(false, GOAL_KICK, 0.5, GK, false, 1),
                new ShotOutcomeWeight(false, GOAL_KICK, 0, GK, false, 2),
                new ShotOutcomeWeight(false, CATCH, 0.25, Dg, false, 2),
                new ShotOutcomeWeight(false, SAVE, 0.5, Ap, true, 1)
        ));

        data.get(SHOOT_AP).addAll(Arrays.asList(
                new ShotOutcomeWeight(false, GOAL_KICK, 0.5, GK, false, 1),
                new ShotOutcomeWeight(false, CATCH, 0.25, Dg, false, 1),
                new ShotOutcomeWeight(false, SAVE, 0.5, Dp, false, 1),
                new ShotOutcomeWeight(true, GOAL, 1, D, false, 2)
        ));

        data.get(SHOOT_AP_FTA).addAll(Arrays.asList(
                new ShotOutcomeWeight(false, POST, 0.75, Ap, true, 1),
                new ShotOutcomeWeight(false, GOAL_KICK, 0.75, GK, false, 1)
        ));

        data.get(HOLD_DG).addAll(Arrays.asList(
                new ActionOutcomeWeight(D, true, 1),
                new ActionOutcomeWeight(D, false, 2)
        ));

        data.get(HOLD_A).addAll(Arrays.asList(
                new ActionOutcomeWeight(D, false, 3),
                new ActionOutcomeWeight(A, true, 6),
                new ActionOutcomeWeight(D, true, 2)
        ));

        data.get(BACK_PASS_A).addAll(Arrays.asList(
                new ActionOutcomeWeight(D, true, 3)
        ));

        data.get(DRIBBLE_D).addAll(Arrays.asList(
                new ActionOutcomeWeight(F, D, true, 2),
                new ActionOutcomeWeight(D, false, 1),
                new ActionOutcomeWeight(D, true, 1)
        ));

        data.get(DEFAULT_D_SP).addAll(Arrays.asList(
                new ActionOutcomeWeight(D, true, 3),
                new ActionOutcomeWeight(F, D, false, 1),
                new ActionOutcomeWeight(Dg, true, 1)
        ));

        data.get(FORWARD_PASS_A).addAll(Arrays.asList(
                new ActionOutcomeWeight(D, false, 4),
                new ActionOutcomeWeight(Ap, true, 1)
        ));

        data.get(LATERAL_PASS_A).addAll(Arrays.asList(
                new ActionOutcomeWeight(Aw, true, 2)
        ));

        data.get(MOVE_AW).addAll(Arrays.asList(
                new ActionOutcomeWeight(Ap, true, 1),
                new ActionOutcomeWeight(Aw, true, 1)
        ));

        data.get(LOW_C).addAll(
                Arrays.asList(new ActionOutcomeWeight(ApFTA, true, 1))
        );

        data.get(CROSS_C).addAll(
                Arrays.asList(new ActionOutcomeWeight(A, true, 1))
        );

        data.get(LONG_A).addAll(Arrays.asList(
                new ActionOutcomeWeight(ApFTA, true, 1)
        ));

        data.get(MOVE_A).addAll(Arrays.asList(
                new ActionOutcomeWeight(Ap, true, 1)
        ));

        data.get(DRIBBLE_AP).addAll(Arrays.asList(
                new ActionOutcomeWeight(Ap, true, 1)
        ));

        data.get(HOLD_GK).addAll(Arrays.asList(
                new ActionOutcomeWeight(Dg, true, 1)
        ));

        data.get(CROSS_AP).addAll(Arrays.asList(
                new ActionOutcomeWeight(GK, false, 2)
        ));

        data.get(CROSS_PASS_AP).addAll(Arrays.asList(
                new ActionOutcomeWeight(C, true, 1)
        ));

        data.get(CROSS_PASS_AW).addAll(Arrays.asList(
                new ActionOutcomeWeight(D, false, 1),
                new ActionOutcomeWeight(A, true, 1)
        ));
    }
}
