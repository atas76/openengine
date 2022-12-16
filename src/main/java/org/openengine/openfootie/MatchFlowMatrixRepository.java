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
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(KICK_OFF, true, 0, 0, 0)
                }));
        T_CLF19.addRow(KICK_OFF,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 4, 0, 0)
                }));
        T_CLF19.addRow(INTERCEPTION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(TRANSITION, false, 11, 0, 0)
                }));
        T_CLF19.addRow(PRESSURE,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(TRANSITION, false, 20, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 17, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 25, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 21, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 10, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 17, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 10, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 22, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 20, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 16, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 14, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 5, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 21, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 27, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 10, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 16, 0, 0),
                        new MatchPhaseTransition(INTERCEPTION, false, 10, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 17, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 22, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 22, 0, 0)
                }));
        T_CLF19.addRow(POSSESSION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(TRANSITION, false, 4, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 12, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 20, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 23, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 5, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 1, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 27, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 6, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 13, 0, 0),
                        new MatchPhaseTransition(INTERCEPTION, false, 3, 0, 0),
                        new MatchPhaseTransition(INTERCEPTION, false, 3, 0, 0),
                        new MatchPhaseTransition(INTERCEPTION, false, 8, 0, 0),
                        new MatchPhaseTransition(INTERCEPTION, false, 4, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 32, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 29, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 23, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 52, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 6, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 7, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 12, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 14, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 12, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 26, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 26, 0, 0),
                        new MatchPhaseTransition(PRESSURE, false, 36, 0, 0),
                        new MatchPhaseTransition(PRESSURE, false, 9, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 4, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 20, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 8, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 10, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 8, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 19, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 3, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 18, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 6, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 39, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 19, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 18, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 23, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, false, 44, 0, 0),
                        new MatchPhaseTransition(TRANSITION, true, 6, 0, 0)
                }));
        T_CLF19.addRow(CORNER_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 12, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 24, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 2, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 8, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 3, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 24, 0, 0)
                }));
        T_CLF19.addRow(THROW_IN,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 5, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 13, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 6, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 9, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 4, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 10, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 9, 0,0),
                        new MatchPhaseTransition(THROW_IN, true, 15, 0,0),
                        new MatchPhaseTransition(ATTACK, true, 3, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 1, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 4, 0, 0),
                        new MatchPhaseTransition(PRESSURE, false, 11, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 8, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 2, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, true, 37, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, true, 35, 0, 0),
                        new MatchPhaseTransition(ATTACK, false, 3, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 15, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 9, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 6, 0, 0),
                }));
        T_CLF19.addRow(TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(TRANSITION, false, 9, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 9, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 27, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 7, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 5, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 4, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 3, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 9, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 18, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 2, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 1, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 5, 0, 0),
                }));
        T_CLF19.addRow(ATTACKING_POSSESSION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(TRANSITION, false, 5, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 12, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 15, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 3, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 21, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 30, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 14, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 14, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 26, 88, 88),
                        new MatchPhaseTransition(ATTACK, true, 2, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 5, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 17, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 20, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 9, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 7, 0, 0),
                }));
        T_CLF19.addRow(ATTACK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 12, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 36, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 7, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 6, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 23, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 8, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 25, 0, 0),
                        new MatchPhaseTransition(PRESSURE, false, 36, 0, 0),
                        new MatchPhaseTransition(PRESSURE, false, 10, 0, 0),
                        new MatchPhaseTransition(PRESSURE, false, 30, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 28, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 24, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, true, 29, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 6, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 17, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 15, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, false, 18, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, false, 10, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, false, 4, 0, 0),
                }));
        T_CLF19.addRow(FREE_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 6, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 10, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 6, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 32, 0, 0)
                }));
        T_CLF19.addRow(COUNTER_ATTACK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(THROW_IN, false, 26, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 28, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 9, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 2, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 10, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 10, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, false, 13, 0, 0)
                }));
        T_CLF19.addRow(DEFENSIVE_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 10, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 5, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 21, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 35, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 9, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 20, 20, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 19, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, false, 26, 26, 0)
                }));
        T_CLF19.addRow(ATTACKING_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(CORNER_KICK, true, 8, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 24, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 2, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 7, 0, 0)
                }));
    }

    public static void loadHomeTeam() {
        L_CLF19.addRow(MAIN,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(KICK_OFF, true, 0, 0, 0)
                }));
        L_CLF19.addRow(KICK_OFF,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 2, 0, 0)
                }));
        L_CLF19.addRow(POSSESSION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(INTERCEPTION, false, 2, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 66, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 30, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 10, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 38, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 31, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 8, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 19, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, true, 44, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, false, 29, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 15, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 4, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 6, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, false, 6, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, false, 6, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 10, 0, 0),
                }));
        L_CLF19.addRow(TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(THROW_IN, false, 11, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 14, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 3, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 5, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 5, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 9, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 5, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 6, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 5, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 6, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 2, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 4, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 7, 0, 0)
                }));
        L_CLF19.addRow(ATTACKING_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(CORNER_KICK, true, 44, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 35, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 30, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 9, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 10, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 9, 0, 0),
                        new MatchPhaseTransition(PENALTY, true, 87, 0, 0),
                        new MatchPhaseTransition(PRESSURE, false, 19, 0, 0),
                        new MatchPhaseTransition(PRESSURE, false, 29, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 5, 0, 0)
                }));
        L_CLF19.addRow(PENALTY,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(GOAL, false, 91, 91, 0)
                }));
        L_CLF19.addRow(THROW_IN,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 3, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 14, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 30, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 21, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 39, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 9, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 10, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 4, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 5, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 5, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 11, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 13, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 8, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 20, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 16, 34, 0),
                        new MatchPhaseTransition(POSSESSION, false, 17, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 11, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 18, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 8, 0, 0),
                        new MatchPhaseTransition(TRANSITION, true, 14, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 8, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, false, 7, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, true, 20, 0, 0)
                }));
        L_CLF19.addRow(ATTACKING_POSSESSION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 5, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 3, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 5, 0, 0),
                }));
        L_CLF19.addRow(ATTACK,
                new MatchSequence(new MatchPhaseTransition[]{
                        new MatchPhaseTransition(CORNER_KICK, true, 39, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 13, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 20, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 13, 0, 0),
                }));
        L_CLF19.addRow(PRESSURE,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 14, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 19, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 27, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 8, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 19, 0, 0),
                        new MatchPhaseTransition(TRANSITION, true, 6, 0, 0),
                        new MatchPhaseTransition(TRANSITION, true, 8, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 7, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 11, 0, 0)
                }));
        L_CLF19.addRow(CORNER_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 24, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 35, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 20, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 16, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 36, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, false, 1, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 3, 0, 41),
                        new MatchPhaseTransition(GOAL, false, 7, 27, 0)
                }));
        L_CLF19.addRow(DEFENSIVE_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(THROW_IN, false, 12, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 29, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 5, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 9, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 34, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, false, 9, 0, 0),
                }));
        L_CLF19.addRow(COUNTER_ATTACK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 18, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 46, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 36, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 13, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 25, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 8, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 11, 0, 0)
                }));
        L_CLF19.addRow(FREE_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 3, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 48, 0, 0),
                        new MatchPhaseTransition(PRESSURE, false, 19, 0, 0)
                }));
        L_CLF19.addRow(INTERCEPTION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(TRANSITION, false, 10, 0, 0),
                        new MatchPhaseTransition(TRANSITION, true, 4, 0, 0),
                        new MatchPhaseTransition(TRANSITION, true, 4, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, false, 1, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, false, 5, 0, 0),
                }));
    }
}
