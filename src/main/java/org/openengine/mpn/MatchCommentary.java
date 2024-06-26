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
            Map.entry(PitchPosition.D, "defensive third"),
            Map.entry(PitchPosition.Dh, "defensive third"),
            Map.entry(PitchPosition.Dw, "defensive third, wing"),
            Map.entry(PitchPosition.DM, "their own half"),
            Map.entry(PitchPosition.DMh, "their own half"),
            Map.entry(PitchPosition.DMw, "their own half, wing"),
            Map.entry(PitchPosition.DB20, "penalty box, between penalty spot and penalty area line"),
            Map.entry(PitchPosition.DB22, "penalty box corner"),
            Map.entry(PitchPosition.AB02, "off goal area, wing"),
            Map.entry(PitchPosition.AB10, "penalty box, between penalty spot and goal area"),
            Map.entry(PitchPosition.AB11, "penalty box diagonally near the goal"),
            Map.entry(PitchPosition.AB12, "penalty box diagonally to the goal"),
            Map.entry(PitchPosition.AB20, "penalty box, between penalty spot and penalty area line"),
            Map.entry(PitchPosition.AB21, "penalty box diagonally to the goal"),
            Map.entry(PitchPosition.AB22, "penalty box corner"),
            Map.entry(PitchPosition.M, "attacking half"),
            Map.entry(PitchPosition.Mh, "attacking half, half space"),
            Map.entry(PitchPosition.Mw, "attacking half, wing"),
            Map.entry(PitchPosition.AM, "attacking third"),
            Map.entry(PitchPosition.AMh, "attacking third, half space"),
            Map.entry(PitchPosition.AMw, "attacking third, wing"),
            Map.entry(PitchPosition.A, "outside penalty box"),
            Map.entry(PitchPosition.Ah, "outside penalty box, half space"),
            Map.entry(PitchPosition.Aw, "wing, near penalty area"),
            Map.entry(PitchPosition.AB, "wide, at penalty box height"),
            Map.entry(PitchPosition.ABw, "wide, off the penalty box")
    );

    public static Map<PitchPosition, String> throwInPositionMappings = Map.ofEntries(
        Map.entry(PitchPosition.A, "near the height of the penalty box")
    );

    public static Map<PitchPosition, String> possessionChangePitchPositionMappings = Map.ofEntries(
            Map.entry(PitchPosition.D, "opponent's defensive third"),
            Map.entry(PitchPosition.DM, "opponent's half"),
            Map.entry(PitchPosition.DMh, "opponent's half"),
            Map.entry(PitchPosition.DMw, "opponent's half, wing"),
            Map.entry(PitchPosition.DB10, "opponent's penalty box, between penalty spot and goal area"),
            Map.entry(PitchPosition.DB22, "opponent's penalty box corner"),
            Map.entry(PitchPosition.M, "opponent's attacking half, centre"),
            Map.entry(PitchPosition.Mh, "opponent's attacking half, half space"),
            Map.entry(PitchPosition.Mw, "opponent's attacking half, wide"),
            Map.entry(PitchPosition.AM, "opponent's attacking third"),
            Map.entry(PitchPosition.AMh, "opponent's attacking third, half space"),
            Map.entry(PitchPosition.AMw, "opponent's attacking third, wing"),
            Map.entry(PitchPosition.Ah, "opponent's shooting range, half space"),
            Map.entry(PitchPosition.Aw, "opponent's attacking wing, near penalty area")
    );

    public static void main(String[] args) {
        Match match = new Match();
        match.display();
    }
}
