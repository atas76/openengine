package org.openengine.openfootie;

import static org.openengine.openfootie.MatchEvent.*;
import static org.openengine.openfootie.MatchPhase.*;
import static org.openengine.openfootie.SetPiece.*;

public class MatchFlowMatrixRepository {

    public static final MatchFlowMatrix L_CLF19 = new MatchFlowMatrix();

    public static void load() {
        L_CLF19.addRow(KICK_OFF,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 2, 0, 0)
                }));
        L_CLF19.addRow(POSSESSION,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(INTERCEPTION, false, 11, 0, 0),
                        new MatchDataElement(CORNER_KICK, true, 24, 0, 0),
                        new MatchDataElement(THROW_IN, false, 37, 0, 0),
                        new MatchDataElement(THROW_IN, false, 7, 0, 0),
                        new MatchDataElement(THROW_IN, true, 8, 0, 0),
                        new MatchDataElement(THROW_IN, true, 11, 0, 0),
                        new MatchDataElement(PRESSURE, true, 8, 0, 0),
                        new MatchDataElement(PRESSURE, true, 19, 0, 0),
                        new MatchDataElement(FREE_KICK, true, 19, 0, 0),
                        new MatchDataElement(FREE_KICK, false, 10, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 15, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 10, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 8, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, false, 10, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, false, 37, 37, 0), // for now duration = breakTime
                        new MatchDataElement(TRANSITION, false, 7, 0, 0),
                }));
        L_CLF19.addRow(TRANSITION,
                new MatchSequence(new MatchDataElement[]{
                        new MatchDataElement(THROW_IN, false, 13, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 17, 17, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 23, 0, 0),
                        new MatchDataElement(TRANSITION, false, 9, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 87, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 19, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 44, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 10, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 9, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 5, 0, 0),
                        new MatchDataElement(POSSESSION, false, 36, 0, 0),
                        new MatchDataElement(POSSESSION, false, 12, 0, 0),
                        new MatchDataElement(POSSESSION, false, 12, 0, 0)
                }));
        L_CLF19.addRow(ATTACKING_TRANSITION,
                new MatchSequence(new MatchDataElement[]{
                        new MatchDataElement(CORNER_KICK, true, 36, 0, 0),
                        new MatchDataElement(CORNER_KICK, true, 35, 0, 0),
                        new MatchDataElement(THROW_IN, false, 11, 0, 0),
                        new MatchDataElement(TRANSITION, false, 5, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 3, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 5, 0, 0),
                        new MatchDataElement(PENALTY, true, 0, 0, 0),
                        new MatchDataElement(PRESSURE, false, 16, 0, 0),
                        new MatchDataElement(PRESSURE, false, 10, 0, 0),
                        new MatchDataElement(ATTACK, true, 13, 0, 0)
                }));
        L_CLF19.addRow(PENALTY,
                new MatchSequence(new MatchDataElement[]{
                        new MatchDataElement(GOAL, false, 91, 91, 0)
                }));
    }
}
