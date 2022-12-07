package org.openengine.openfootie;

import static org.openengine.openfootie.MatchEvent.*;
import static org.openengine.openfootie.MatchPhase.*;
import static org.openengine.openfootie.SetPiece.*;
import static org.openengine.openfootie.Special.MAIN;

public class MatchFlowMatrixRepository {

    public static final MatchFlowMatrix L_CLF19 = new MatchFlowMatrix();
    public static final MatchFlowMatrix T_CLF19 = new MatchFlowMatrix();

    public static void load() {
        loadHomeTeam();
        loadAwayTeam();
    }

    public static void loadAwayTeam() {
        T_CLF19.addRow(MAIN,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(KICK_OFF, true, 4, 0, 0)
                }));
        T_CLF19.addRow(KICK_OFF,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 6, 0, 0)
                }));
        T_CLF19.addRow(INTERCEPTION,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(TRANSITION, false, 5, 0, 0)
                }));
        T_CLF19.addRow(PRESSURE,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(TRANSITION, false, 4, 0, 0),
                        new MatchDataElement(POSSESSION, true, 1, 0, 0),
                        new MatchDataElement(POSSESSION, true, 52, 0, 0),
                        new MatchDataElement(POSSESSION, true, 6, 0, 0),
                        new MatchDataElement(POSSESSION, true, 20, 0, 0),
                        new MatchDataElement(POSSESSION, true, 8, 0, 0),
                        new MatchDataElement(POSSESSION, true, 23, 0, 0),
                        new MatchDataElement(POSSESSION, true, 26, 0, 0),
                        new MatchDataElement(POSSESSION, true, 4, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, true, 2, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, true, 26, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, true, 28, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, true, 9, 0, 0),
                        new MatchDataElement(THROW_IN, false, 45, 45, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 12, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 34, 0, 0),
                        new MatchDataElement(INTERCEPTION, false, 4, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, false, 29, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, false, 30, 0, 0),
                        new MatchDataElement(THROW_IN, true, 4, 0, 0)
                }));
        T_CLF19.addRow(POSSESSION,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(TRANSITION, false, 5, 0, 0),
                        new MatchDataElement(TRANSITION, false, 7, 0, 0),
                        new MatchDataElement(TRANSITION, false, 5, 0, 0),
                        new MatchDataElement(TRANSITION, false, 6, 0, 0),
                        new MatchDataElement(TRANSITION, false, 11, 0, 0),
                        new MatchDataElement(TRANSITION, false, 2, 0, 0),
                        new MatchDataElement(THROW_IN, false, 17, 0, 0),
                        new MatchDataElement(THROW_IN, false, 4, 0, 0),
                        new MatchDataElement(THROW_IN, false, 13, 0, 0),
                        new MatchDataElement(INTERCEPTION, false, 4, 0, 0),
                        new MatchDataElement(INTERCEPTION, false, 10, 0, 0),
                        new MatchDataElement(INTERCEPTION, false, 1, 0, 0),
                        new MatchDataElement(INTERCEPTION, false, 5, 0, 0),
                        new MatchDataElement(INTERCEPTION, false, 13, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, false, 9, 0, 0),
                        new MatchDataElement(THROW_IN, true, 9, 0, 0),
                        new MatchDataElement(THROW_IN, true, 6, 0, 0),
                        new MatchDataElement(CORNER_KICK, true, 12, 0, 0),
                        new MatchDataElement(ATTACK, true, 23, 0, 0),
                        new MatchDataElement(ATTACK, true, 28, 0, 0),
                        new MatchDataElement(ATTACK, true, 12, 0, 0),
                        new MatchDataElement(ATTACK, true, 17, 0, 0),
                        new MatchDataElement(ATTACK, true, 10, 0, 0),
                        new MatchDataElement(ATTACK, true, 41, 41, 0),
                        new MatchDataElement(ATTACK, true, 29, 0, 0),
                        new MatchDataElement(PRESSURE, false, 19, 0, 0),
                        new MatchDataElement(PRESSURE, false, 11, 0, 0),
                        new MatchDataElement(PRESSURE, true, 10, 0, 0),
                        new MatchDataElement(PRESSURE, true, 27, 0, 0),
                        new MatchDataElement(PRESSURE, true, 23, 23, 0),
                        new MatchDataElement(PRESSURE, true, 10, 0, 0),
                        new MatchDataElement(PRESSURE, true, 10, 0, 0),
                        new MatchDataElement(PRESSURE, true, 14, 0, 0),
                        new MatchDataElement(PRESSURE, true, 17, 0, 0),
                        new MatchDataElement(PRESSURE, true, 5, 0, 0),
                        new MatchDataElement(PRESSURE, true, 22, 0, 0),
                        new MatchDataElement(PRESSURE, true, 22, 0, 0),
                        new MatchDataElement(POSSESSION, false, 10, 0, 0),
                        new MatchDataElement(POSSESSION, false, 6, 0, 0),
                        new MatchDataElement(POSSESSION, false, 10, 0, 0),
                        new MatchDataElement(FREE_KICK, false, 48, 0, 0),
                        new MatchDataElement(TRANSITION, true, 27, 0, 0)
                }));
        T_CLF19.addRow(CORNER_KICK,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 20, 0, 0),
                        new MatchDataElement(CORNER_KICK, true, 48, 48, 0),
                        new MatchDataElement(ATTACK, true, 58, 58, 0),
                        new MatchDataElement(ATTACK, true, 24, 0, 0),
                        new MatchDataElement(ATTACK, true, 25, 0, 0),
                        new MatchDataElement(POSSESSION, false, 6, 0, 0)
                }));
        T_CLF19.addRow(THROW_IN,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 19, 0, 0),
                        new MatchDataElement(POSSESSION, true, 9, 0, 0),
                        new MatchDataElement(POSSESSION, true, 23, 0, 0),
                        new MatchDataElement(POSSESSION, true, 27, 0, 0),
                        new MatchDataElement(POSSESSION, true, 8, 0, 0),
                        new MatchDataElement(POSSESSION, true, 7, 0, 0),
                        new MatchDataElement(THROW_IN, true, 6, 0,0),
                        new MatchDataElement(THROW_IN, true, 15, 0,0),
                        new MatchDataElement(ATTACK, true, 10, 0, 0),
                        new MatchDataElement(ATTACK, true, 6, 0, 0),
                        new MatchDataElement(ATTACK, true, 4, 0, 0),
                        new MatchDataElement(PRESSURE, false, 7, 0, 0),
                        new MatchDataElement(PRESSURE, true, 21, 0, 0),
                        new MatchDataElement(PRESSURE, true, 22, 0, 0),
                        new MatchDataElement(FREE_KICK, true, 47, 47, 0),
                        new MatchDataElement(FREE_KICK, true, 6, 0, 0),
                        new MatchDataElement(ATTACK, false, 13, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 12, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 14, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 15, 0, 0),
                }));
        T_CLF19.addRow(TRANSITION,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(TRANSITION, false, 3, 0, 0),
                        new MatchDataElement(POSSESSION, true, 18, 0, 0),
                        new MatchDataElement(THROW_IN, false, 16, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 9, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 20, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 5, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 9, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 21, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 10, 10, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 31, 31, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 35, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 13, 0, 0),
                }));
        T_CLF19.addRow(ATTACKING_POSSESSION,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(TRANSITION, false, 9, 0, 0),
                        new MatchDataElement(POSSESSION, true, 3, 0, 0),
                        new MatchDataElement(POSSESSION, true, 12, 0, 0),
                        new MatchDataElement(POSSESSION, true, 8, 0, 0),
                        new MatchDataElement(POSSESSION, true, 26, 0, 0),
                        new MatchDataElement(CORNER_KICK, true, 24, 0, 0),
                        new MatchDataElement(CORNER_KICK, true, 3, 0, 0),
                        new MatchDataElement(ATTACK, true, 7, 0, 0),
                        new MatchDataElement(ATTACK, true, 91, 88, 88),
                        new MatchDataElement(ATTACK, true, 36, 0, 0),
                        new MatchDataElement(ATTACK, true, 15, 0, 0),
                        new MatchDataElement(ATTACK, true, 18, 0, 0),
                        new MatchDataElement(ATTACK, true, 30, 0, 0),
                        new MatchDataElement(ATTACK, true, 8, 0, 0),
                        new MatchDataElement(ATTACK, true, 36, 0, 0),
                }));
        T_CLF19.addRow(ATTACK,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 18, 0, 0),
                        new MatchDataElement(THROW_IN, false, 8, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 2, 0, 0),
                        new MatchDataElement(THROW_IN, true, 3, 0, 0),
                        new MatchDataElement(THROW_IN, true, 1, 0, 0),
                        new MatchDataElement(THROW_IN, true, 4, 0, 0),
                        new MatchDataElement(THROW_IN, true, 15, 0, 0),
                        new MatchDataElement(PRESSURE, false, 27, 0, 0),
                        new MatchDataElement(PRESSURE, false, 6, 0, 0),
                        new MatchDataElement(PRESSURE, false, 14, 0, 0),
                        new MatchDataElement(POSSESSION, false, 15, 0, 0),
                        new MatchDataElement(POSSESSION, false, 6, 0, 0),
                        new MatchDataElement(FREE_KICK, true, 32, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 6, 6, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 26, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, true, 3, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, false, 25, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, false, 11, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, false, 18, 0, 0),
                }));
        T_CLF19.addRow(FREE_KICK,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 4, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 5, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 39, 0, 0),
                        new MatchDataElement(CORNER_KICK, true, 43, 43, 0)
                }));
        T_CLF19.addRow(COUNTER_ATTACK,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(THROW_IN, false, 8, 0, 0),
                        new MatchDataElement(THROW_IN, false, 10, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 29, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 9, 0, 0),
                        new MatchDataElement(POSSESSION, false, 19, 0, 0),
                        new MatchDataElement(POSSESSION, false, 31, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, false, 13, 0, 0)
                }));
        T_CLF19.addRow(DEFENSIVE_TRANSITION,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(COUNTER_ATTACK, true, 10, 0, 0),
                        new MatchDataElement(THROW_IN, false, 5, 0, 0),
                        new MatchDataElement(THROW_IN, false, 21, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, false, 35, 0, 0),
                        new MatchDataElement(THROW_IN, true, 9, 0, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 20, 20, 0),
                        new MatchDataElement(ATTACKING_TRANSITION, true, 19, 0, 0),
                        new MatchDataElement(CORNER_KICK, false, 26, 26, 0)
                }));
    }

    public static void loadHomeTeam() {
        L_CLF19.addRow(MAIN,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(KICK_OFF, true, 2, 0, 0)
                }));
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
        L_CLF19.addRow(CORNER_KICK,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 30, 0, 0),
                        new MatchDataElement(CORNER_KICK, true, 20, 0, 0),
                        new MatchDataElement(THROW_IN, true, 3, 0, 0),
                        new MatchDataElement(THROW_IN, true, 9, 0, 0),
                        new MatchDataElement(THROW_IN, true, 30, 0, 0),
                        new MatchDataElement(FREE_KICK, false, 6, 0, 0),
                        new MatchDataElement(POSSESSION, false, 4, 0, 0),
                        new MatchDataElement(GOAL, false, 27, 27, 0)
                }));
        L_CLF19.addRow(DEFENSIVE_TRANSITION,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(THROW_IN, false, 9, 0, 0),
                        new MatchDataElement(THROW_IN, true, 20, 0, 0),
                        new MatchDataElement(DEFENSIVE_TRANSITION, false, 14, 0, 0),
                        new MatchDataElement(TRANSITION, false, 3, 0, 0),
                        new MatchDataElement(POSSESSION, false, 44, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, false, 30, 0, 0),
                }));
        L_CLF19.addRow(COUNTER_ATTACK,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 8, 0, 0),
                        new MatchDataElement(CORNER_KICK, true, 3, 0, 41),
                        new MatchDataElement(CORNER_KICK, true, 16, 0, 0),
                        new MatchDataElement(CORNER_KICK, true, 7, 0, 0),
                        new MatchDataElement(THROW_IN, true, 14, 0, 0),
                        new MatchDataElement(TRANSITION, false, 18, 0, 0),
                        new MatchDataElement(ATTACK, true, 4, 0, 0)
                }));
        L_CLF19.addRow(FREE_KICK,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 45, 45, 0),
                        new MatchDataElement(THROW_IN, false, 10, 0, 0),
                        new MatchDataElement(PRESSURE, false, 30, 30, 0)
                }));
        L_CLF19.addRow(INTERCEPTION,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(TRANSITION, false, 1, 0, 0),
                        new MatchDataElement(TRANSITION, true, 4, 0, 0),
                        new MatchDataElement(TRANSITION, true, 6, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, false, 5, 0, 0),
                        new MatchDataElement(ATTACKING_POSSESSION, false, 17, 0, 0),
                }));
    }
}
