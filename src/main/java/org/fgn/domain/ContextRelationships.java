package org.fgn.domain;

import java.util.HashMap;
import java.util.Map;

public class ContextRelationships {

    public static Map<OutState, InState> contextMap = new HashMap<>();
    public static Map<Context.InState, Coordinates> stateCoordinatesMap = new HashMap<>();
    public static Map<Context.InState, Action> actionMap = new HashMap<>();

    static {
        contextMap.put(new OutState(Context.Coordinate.PenaltyArea, Context.OutState.H), new InState(Context.InState.PK));
        contextMap.put(new OutState(Context.Coordinate.PenaltyArea, Context.OutState.F), new InState(Context.InState.PK));
        contextMap.put(new OutState(Context.OutState.H), new InState(Context.InState.SP));
        contextMap.put(new OutState(Context.OutState.F), new InState(Context.InState.SP));
        contextMap.put(new OutState(Context.OutState.C), new InState(Context.InState.CK));
        contextMap.put(new OutState(Context.OutState.T), new InState(Context.InState.T));
        contextMap.put(new OutState(Context.OutState.GK), new InState(Context.InState.GK));
        contextMap.put(new OutState(Context.OutState.O), new InState(Context.InState.SP));
    }

    static {
        stateCoordinatesMap.put(Context.InState.KO, new Coordinates(Coordinates.X.M));
    }

    static {
        actionMap.put(Context.InState.KO, Action.PASS);
    }
}
