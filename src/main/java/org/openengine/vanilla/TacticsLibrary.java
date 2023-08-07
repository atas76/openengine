package org.openengine.vanilla;

import java.util.Map;

import static org.openengine.vanilla.Tactics._4_4_2;

public class TacticsLibrary {

    public static Map<Tactics, Tactic> tacticsRepository = Map.of(
            _4_4_2, new Tactic(new boolean [][]
                    {
                            new boolean[] {true, false, true, false, true, false, true},
                            new boolean[] {false, false, false, false, false, false, false},
                            new boolean[] {true, false, true, false, true, false, true},
                            new boolean[] {false, false, false, false, false, false, false},
                            new boolean[] {false, false, true, false, true, false, false},
                    }
    ));
}
