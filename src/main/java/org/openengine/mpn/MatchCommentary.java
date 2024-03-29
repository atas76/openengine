package org.openengine.mpn;

import org.mpn.PitchPosition;
import org.mpn.State;

import java.util.Map;

public class MatchCommentary {

    public static Map<State, String> stateMappings = Map.ofEntries(
            Map.entry(State.KICK_OFF, "Kick-off"),
            Map.entry(State.POSSESSION, "Possession"));

    public static Map<PitchPosition, String> pitchPositionMappings = Map.ofEntries(
            Map.entry(PitchPosition.DM, "their own half"),
            Map.entry(PitchPosition.DB22, "penalty box corner"),
            Map.entry(PitchPosition.AB22, "penalty box corner"),
            Map.entry(PitchPosition.Mh, "attacking half, half space")
    );

    public static void main(String[] args) {
        Match match = new Match();
        match.display();
    }
}
