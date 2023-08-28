package org.openengine.vanilla;

import java.util.Map;

import static org.openengine.vanilla.Tactics._4_3_3;
import static org.openengine.vanilla.Tactics._4_4_2;

public class TacticsRepository {

    private static Map<Tactics, Tactic> tacticsRepository = Map.of(
            _4_4_2, new Tactic(new boolean [][]
                    {
                            new boolean[] {true, false, true, false, true, false, true},
                            new boolean[] {false, false, false, false, false, false, false},
                            new boolean[] {true, false, true, false, true, false, true},
                            new boolean[] {false, false, false, false, false, false, false},
                            new boolean[] {false, false, true, false, true, false, false},
                    }),
            _4_3_3, new Tactic(new boolean [][]
                    {
                            new boolean[] {true, false, true, false, true, false, true},
                            new boolean[] {false, false, false, false, false, false, false},
                            new boolean[] {false, true, false, true, false, true, false},
                            new boolean[] {false, false, false, false, false, false, false},
                            new boolean[] {false, true, false, true, false, true, false},
                    })

    );

    public static Tactic get(Tactics tactics) {
        return tacticsRepository.get(tactics);
    }
}
