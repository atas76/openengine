package org.openengine.openfootie;

import org.openengine.prototype.engine.Match;

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
                new MatchSequence(new MatchDataElement[] {
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
                new MatchSequence(new MatchDataElement[] {
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
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(GOAL, false, 91, 91, 0)
                }));
        L_CLF19.addRow(THROW_IN,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 29, 0, 0),
                        new MatchDataElement(THROW_IN, false, 35, 0, 0),
                        new MatchDataElement(THROW_IN, false, 2, 0, 0),
                        new MatchDataElement(THROW_IN, false, 3, 0, 0),
                        new MatchDataElement(THROW_IN, true, 11, 0, 0),
                        new MatchDataElement(THROW_IN, true, 20, 0, 0),
                        new MatchDataElement(THROW_IN, true, 18, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 5, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 49, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 6, 0, 0),
                        new MatchDataElement(TRANSITION, false, 5, 0, 0),
                        new MatchDataElement(TRANSITION, false, 9, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 81, 81, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 5, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 34, 34, 0),
                        new MatchDataElement(POSSESSION, false, 23, 0, 0),
                        new MatchDataElement(POSSESSION, false, 13, 0, 0),
                        new MatchDataElement(POSSESSION, false, 6, 0, 0),
                        new MatchDataElement(ATTACK, true, 39, 0, 0),
                        new MatchDataElement(TRANSITION, true, 26, 26, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, false, 12, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, false, 14, 0, 0),
                        new MatchDataElement(FREE_KICK, true, 3, 0, 0)
                }));
        L_CLF19.addRow(ATTACKING_POSSESSION,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(ATTACK, true, 55, 55, 0),
                        new MatchDataElement(ATTACK, true, 20, 0, 0),
                        new MatchDataElement(ATTACK, true, 15, 15, 0),
                }));
        L_CLF19.addRow(ATTACK,
                new MatchSequence(new MatchDataElement[]{
                        new MatchDataElement(CORNER_KICK, true, 61, 0, 0),
                        new MatchDataElement(THROW_IN, true, 5, 0, 0),
                        new MatchDataElement(THROW_IN, true, 8, 0, 0),
                        new MatchDataElement(POSSESSION, false, 32, 0, 0),
                }));
        L_CLF19.addRow(PRESSURE,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 66, 0, 0),
                        new MatchDataElement(POSSESSION, true, 38, 0, 0),
                        new MatchDataElement(THROW_IN, true, 39, 0, 0),
                        new MatchDataElement(TRANSITION, false, 4, 0, 0),
                        new MatchDataElement(POSSESSION, false, 29, 0, 0),
                        new MatchDataElement(TRANSITION, true, 25, 25, 0),
                        new MatchDataElement(TRANSITION, true, 5, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, true, 46, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, true, 36, 0, 0)
                }));
    }
}
