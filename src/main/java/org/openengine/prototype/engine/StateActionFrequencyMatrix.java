package org.openengine.prototype.engine;

import org.openengine.prototype.engine.util.FrequencyMatrixUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.openengine.prototype.engine.Coordinates.*;
import static org.openengine.prototype.engine.Action.*;

public class StateActionFrequencyMatrix {

    private Map<Coordinates, List<ActionWeight>> data = new HashMap<>();

    public StateActionFrequencyMatrix() {

        Stream.of(Coordinates.values()).forEach( value -> data.put(value, new ArrayList<>()));

        data.get(GK).add(new ActionWeight(Hold, 1));

        data.get(Dg).add(new ActionWeight(Long, 11));
        data.get(Dg).add(new ActionWeight(Hold, 3));

        data.get(Dp).add(new ActionWeight(Long, 2));

        data.get(D).add(new ActionWeight(Hold, 76));
        data.get(D).add(new ActionWeight(Long, 25));
        data.get(D).add(new ActionWeight(ForwardPass, 34));
        data.get(D).add(new ActionWeight(Move, 23));
        data.get(D).add(new ActionWeight(Dribble, 4));

        data.get(D_T).add(new ActionWeight(Default, 4));

        data.get(D_SP).add(new ActionWeight(Hold, 5));

        data.get(A_T).add(new ActionWeight(Default, 1));

        data.get(C).add(new ActionWeight(Default, 2));

        data.get(Aw).add(new ActionWeight(LowCross, 2));
        data.get(Aw).add(new ActionWeight(Cross, 3));
        data.get(Aw).add(new ActionWeight(CrossPass, 2));
        data.get(Aw).add(new ActionWeight(Hold, 9));
        data.get(Aw).add(new ActionWeight(Move, 2));
        data.get(Aw).add(new ActionWeight(BackPass, 3));
        data.get(Aw).add(new ActionWeight(ForwardPass, 3));

        data.get(A).add(new ActionWeight(BackPass, 3));
        data.get(A).add(new ActionWeight(Hold, 11));
        data.get(A).add(new ActionWeight(Long, 1));
        data.get(A).add(new ActionWeight(Move, 1));
        data.get(A).add(new ActionWeight(Shoot, 10));
        data.get(A).add(new ActionWeight(ForwardPass, 5));
        data.get(A).add(new ActionWeight(LateralPass, 2));

        data.get(Ap).add(new ActionWeight(Shoot, 8));
        data.get(Ap).add(new ActionWeight(Dribble, 1));
        data.get(Ap).add(new ActionWeight(Cross, 2));
        data.get(Ap).add(new ActionWeight(CrossPass, 1));

        data.get(ApFTA).add(new ActionWeight(Shoot, 1)); // First touch attempts: always 1 by definition
    }

    Action getAction(Coordinates coordinates) {

        List<ActionWeight> stateActions = data.get(coordinates);
        List<Integer> weights = stateActions.stream().map(ActionWeight::getWeight).collect(Collectors.toList());

        return stateActions.get(FrequencyMatrixUtil.getWeightedElement(weights)).getAction();
    }
}
