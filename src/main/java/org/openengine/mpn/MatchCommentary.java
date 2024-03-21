package org.openengine.mpn;

import org.mpn.State;

import java.util.Map;

public class MatchCommentary {

    public static Map<State, String> stateMappings = Map.ofEntries(
            Map.entry(State.KICK_OFF, "Kick-off"));

    public static void main(String[] args) {
        Match match = new Match();
        match.display();
    }
}
