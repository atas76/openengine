package org.openengine.mpn;

import org.mpn.PitchPosition;
import org.mpn.State;

import java.util.Map;

public class MatchCommentary {

    public static Map<State, String> stateMappings = Map.ofEntries(
            Map.entry(State.KICK_OFF, "Kick-off"),
            Map.entry(State.POSSESSION, "Possession"));

    public static Map<PitchPosition, String> pitchPositionMappings = Map.ofEntries(
            Map.entry(PitchPosition.DBw, "defensive third, wide off penalty box"),
            Map.entry(PitchPosition.Dw, "defensive third, wing"),
            Map.entry(PitchPosition.DM, "their own half"),
            Map.entry(PitchPosition.DMh, "their own half"),
            Map.entry(PitchPosition.DMw, "their own half, wing"),
            Map.entry(PitchPosition.DB22, "penalty box corner"),
            Map.entry(PitchPosition.AB22, "penalty box corner"),
            Map.entry(PitchPosition.Mh, "attacking half, half space"),
            Map.entry(PitchPosition.AM, "attacking third"),
            Map.entry(PitchPosition.AMh, "attacking third, half space"),
            Map.entry(PitchPosition.AMw, "attacking third, wing"),
            Map.entry(PitchPosition.A, "outside penalty box"),
            Map.entry(PitchPosition.Ah, "shooting range, half space"),
            Map.entry(PitchPosition.Aw, "wings, near penalty area"),
            Map.entry(PitchPosition.AB, "wide, at penalty box height"),
            Map.entry(PitchPosition.ABw, "wide, off the penalty box")
    );

    public static Map<PitchPosition, String> throwInPositionMappings = Map.ofEntries(
        Map.entry(PitchPosition.A, "near the height of the penalty box")
    );

    public static Map<PitchPosition, String> possessionChangePitchPositionMappings = Map.ofEntries(
            Map.entry(PitchPosition.DM, "opponent's half"),
            Map.entry(PitchPosition.DMh, "opponent's half"),
            Map.entry(PitchPosition.DMw, "opponent's half, wing"),
            Map.entry(PitchPosition.DB22, "opponent's penalty box corner"),
            Map.entry(PitchPosition.AB22, "opponent's penalty box corner"),
            Map.entry(PitchPosition.M, "opponent's attacking half, centre"),
            Map.entry(PitchPosition.Mh, "opponent's attacking half, half space"),
            Map.entry(PitchPosition.Mw, "opponent's attacking half, wide"),
            Map.entry(PitchPosition.AM, "opponent's attacking third"),
            Map.entry(PitchPosition.AMh, "opponent's attacking third, half space"),
            Map.entry(PitchPosition.Ah, "opponent's shooting range, half space"),
            Map.entry(PitchPosition.AB, "wide, at opponent's penalty box height")
    );

    public static void main(String[] args) {
        Match match = new Match();
        match.display();
    }
}
