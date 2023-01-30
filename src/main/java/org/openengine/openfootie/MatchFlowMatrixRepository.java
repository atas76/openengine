package org.openengine.openfootie;

import org.fgn.domain.Match;

import static org.openengine.openfootie.MatchEvent.*;
import static org.openengine.openfootie.MatchPhase.*;
import static org.openengine.openfootie.SetPiece.*;
import static org.openengine.openfootie.Special.MAIN;

public class MatchFlowMatrixRepository {

    public static final MatchFlowMatrix L_CLF19_DEPRECATED = new MatchFlowMatrix();
    public static final MatchFlowMatrix T_CLF19_DEPRECATED = new MatchFlowMatrix();

    public static final MatchFlowMatrix L_CLF19 = new MatchFlowMatrix();
    public static final MatchFlowMatrix T_CLF19 = new MatchFlowMatrix();

    public static void load() {
        loadHomeTeamDeprecated();
        loadAwayTeamDeprecated();
    }

    public static void loadAwayTeam() {
        T_CLF19.addRow(MAIN,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(KICK_OFF, true, 0, 0, 0)
                }));
        T_CLF19.addRow(ATTACK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 17, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 15, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 15, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 41, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, false, 12, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, false, 58, 58, 0),
                        new MatchPhaseTransition(FREE_KICK, true, 90, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 14, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 2, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 1, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 1, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 2, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 3, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 2, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 4, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, false, 4, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, false, 11, 0, 0),
                        new MatchPhaseTransition(GOAL_KICK, false, 31, 0, 0),
                        new MatchPhaseTransition(GOAL_KICK, false, 89, 17, 72),
                        new MatchPhaseTransition(THROW_IN, false, 40, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 26, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 22, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 21, 21, 0),
                        new MatchPhaseTransition(THROW_IN, true, 9, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 28, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 55, 41, 14),
                }));
        T_CLF19.addRow(ATTACKING_POSSESSION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 20, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 14, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 8, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 2, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 3, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 24, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 18, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 19, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 5, 0, 0),
                        new MatchPhaseTransition(BUILDUP, true, 10, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 31, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 17, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 5, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 16, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 46, 46, 0),
                        new MatchPhaseTransition(THROW_IN, false, 23, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 19, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 10, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 2, 0, 0)
                }));
        T_CLF19.addRow(ATTACKING_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 1, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 9, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 15, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 14, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 36, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 2, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 1, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 3, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 7, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 10, 0, 0)
                }));
        T_CLF19.addRow(BUILDUP,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 10, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 27, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, false, 6, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, false, 17, 0, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 22, 0, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 7, 0, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 4, 0, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 2, 0, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 3, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 10, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 18, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 13, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 6, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 4, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 2, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 23, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 12, 0, 0)
                }));
        T_CLF19.addRow(CLEARANCE,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(CONTESTED_BALL, false, 4, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, true, 2, 0, 0)
                }));
        T_CLF19.addRow(CONTESTED_BALL,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 3, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, true, 9, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 8, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 5, 0, 0),
                        new MatchPhaseTransition(TRANSITION, true, 5, 0, 0)
                }));
        T_CLF19.addRow(CORNER_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 1, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 1, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 5, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 7, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 4, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 4, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 2, 0, 0),
                        new MatchPhaseTransition(INDIRECT_FREE_KICK, false, 43, 43, 0),
                        new MatchPhaseTransition(POSSESSION, true, 12, 0, 0)
                }));
        T_CLF19.addRow(COUNTER_ATTACK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 5, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 9, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 14, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 4, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, false, 5, 0, 0)
                }));
        T_CLF19.addRow(DEFENSIVE_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 15, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 2, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 13, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 9, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 4, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 22, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 10, 0, 0)
                }));
        T_CLF19.addRow(FAST_BREAK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 6, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, false, 6, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 26, 0, 0)
                }));
    }

    public static void loadAwayTeamDeprecated() {
        T_CLF19_DEPRECATED.addRow(MAIN,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(KICK_OFF, true, 0, 0, 0)
                }));
        T_CLF19_DEPRECATED.addRow(KICK_OFF,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 4, 0, 0)
                }));
        T_CLF19_DEPRECATED.addRow(INTERCEPTION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(TRANSITION, false, 11, 0, 0)
                }));
        T_CLF19_DEPRECATED.addRow(PRESSURE,
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
        T_CLF19_DEPRECATED.addRow(POSSESSION,
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
        T_CLF19_DEPRECATED.addRow(CORNER_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 12, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 24, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 2, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 8, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 3, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 24, 0, 0)
                }));
        T_CLF19_DEPRECATED.addRow(THROW_IN,
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
        T_CLF19_DEPRECATED.addRow(TRANSITION,
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
        T_CLF19_DEPRECATED.addRow(ATTACKING_POSSESSION,
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
        T_CLF19_DEPRECATED.addRow(ATTACK,
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
        T_CLF19_DEPRECATED.addRow(FREE_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 6, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 10, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 6, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 32, 0, 0)
                }));
        T_CLF19_DEPRECATED.addRow(COUNTER_ATTACK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(THROW_IN, false, 26, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 28, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 9, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 2, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 10, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 10, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, false, 13, 0, 0)
                }));
        T_CLF19_DEPRECATED.addRow(DEFENSIVE_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 14, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 49, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 7, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 6, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 23, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 23, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 8, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, false, 10, 0, 0)
                }));
        T_CLF19_DEPRECATED.addRow(ATTACKING_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(CORNER_KICK, true, 19, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 35, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 13, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 12, 0, 0)
                }));
    }

    public static void loadHomeTeam() {
        L_CLF19.addRow(MAIN,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(KICK_OFF, true, 0, 0, 0)
                }));
        L_CLF19.addRow(KICK_OFF,
                new MatchSequence((new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 2, 0, 0)
                })));
        L_CLF19.addRow(ATTACK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(CORNER_KICK, true, 40, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 34, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, true, 18, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 2, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 10, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 7, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 12, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 1, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, false, 8, 8, 0),
                        new MatchPhaseTransition(GOAL_KICK, false, 17, 0, 0),
                        new MatchPhaseTransition(PENALTY, true, 87, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 32, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 57, 57, 0),
                }));
        L_CLF19.addRow(ATTACKING_POSSESSION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 4, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 26, 0, 0)

                }));
        L_CLF19.addRow(ATTACKING_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 3, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 6, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 7, 0, 0),
                        new MatchPhaseTransition(CLEARANCE, false, 5, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 30, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 6, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 5, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 2, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 2, 0, 0),
                        new MatchPhaseTransition(GOAL_KICK, false, 29, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 32, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 20, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 8, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 2, 0, 0)
                }));
        L_CLF19.addRow(BUILDUP,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 12, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 77, 0, 31),
                        new MatchPhaseTransition(GOAL_KICK, false, 34, 34, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 3, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 4, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 18, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 3, 0, 0),
                        new MatchPhaseTransition(PRESSURE, true, 4, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 43, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 31, 31, 0),
                }));
        L_CLF19.addRow(CLEARANCE,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(THROW_IN, false, 10, 0, 0)
                }));
        L_CLF19.addRow(CONTESTED_BALL,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 3, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 7, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, true, 18, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, false, 32, 0, 0),
                        new MatchPhaseTransition(INDIRECT_FREE_KICK, false, 17, 17, 0),
                        new MatchPhaseTransition(POSSESSION, false, 1, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 5, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 9, 0, 0)
                }));
        L_CLF19.addRow(CORNER_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 5, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 6, 0, 0),
                        new MatchPhaseTransition(FREE_KICK, false, 60, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 4, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, true, 11, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 36, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 20, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 16, 0, 0)
                }));
        L_CLF19.addRow(COUNTER_ATTACK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 11, 0, 0),
                        new MatchPhaseTransition(GOAL_ATTEMPT, true, 7, 0, 0),
                        new MatchPhaseTransition(CLEARANCE, false, 10, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 7, 0, 0)
                }));
        L_CLF19.addRow(DEFENSIVE_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_POSSESSION, false, 4, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 8, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 29, 0, 0)
                }));
        L_CLF19.addRow(FAST_BREAK,
                new MatchSequence((new MatchPhaseTransition[] {
                        new MatchPhaseTransition(CORNER_KICK, true, 8, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 30, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 9, 0, 0)
                })));
        L_CLF19.addRow(FREE_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(GOAL_KICK, false, 19, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 2, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 47, 0, 35),
                }));
        L_CLF19.addRow(GOAL_ATTEMPT,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 1, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 5, 0, 0),
                        new MatchPhaseTransition(GOAL, false, 84, 84, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION,false, 2, 0, 0),
                        new MatchPhaseTransition(GOAL_KICK, false, 26, 26, 0),
                        new MatchPhaseTransition(GOAL_KICK, false, 40, 0, 32),
                        new MatchPhaseTransition(GOAL_KICK, false, 32, 32, 0),
                        new MatchPhaseTransition(GOAL_KICK, false, 9, 9, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 42, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 31, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 25, 0, 0)
                }));
        L_CLF19.addRow(GOALKEEPER_POSSESSION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 12, 0, 0),
                        new MatchPhaseTransition(BUILDUP, true, 14, 0, 0),
                        new MatchPhaseTransition(BUILDUP, true, 6, 0, 0),
                        new MatchPhaseTransition(BUILDUP, true, 8, 0, 0),
                        new MatchPhaseTransition(BUILDUP, true, 6, 6, 0),
                        new MatchPhaseTransition(BUILDUP, true, 3, 0, 0),
                        new MatchPhaseTransition(FAST_BREAK, true, 3, 0, 0),
                        new MatchPhaseTransition(FAST_BREAK, true, 13, 13, 0),
                        new MatchPhaseTransition(FAST_BREAK, true, 13, 0, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 14, 0, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 15, 0, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 15, 0, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 20, 0, 0),
                }));
        L_CLF19.addRow(GOAL_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(BUILDUP, true, 1, 0, 0),
                        new MatchPhaseTransition(BUILDUP, true, 1, 0, 0),
                        new MatchPhaseTransition(LONG_BALL, true, 0, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 28, 0, 0)
                }));
        L_CLF19.addRow(LONG_BALL,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_TRANSITION,false, 10, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, false, 5, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 5, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 26, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 11, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 11, 0, 0),
                }));
        L_CLF19.addRow(PENALTY,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(GOAL, false, 91, 91, 0)
                }));
        L_CLF19.addRow(POSSESSION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 18, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, true, 5, 0, 0),
                        new MatchPhaseTransition(CONTESTED_BALL, false, 19, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 66, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, false, 7, 0, 0),
                        new MatchPhaseTransition(GOAL_KICK, false, 26, 0, 0),
                        new MatchPhaseTransition(GOAL_KICK, false, 40, 40, 0),
                        new MatchPhaseTransition(PRESSURE, true, 7, 0, 0)
                }));
        L_CLF19.addRow(PRESSURE,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(BUILDUP, true, 9, 0, 0),
                        new MatchPhaseTransition(BUILDUP, true, 9, 0, 0),
                        new MatchPhaseTransition(CONTESTED_BALL, false, 8, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true, 10, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, false, 16, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 12, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 14, 14, 0),
                }));
        L_CLF19.addRow(THROW_IN,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 3, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 7, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 1, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 2, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 1, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 16, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 16, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 6, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 6, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, false, 11, 0, 0),
                        new MatchPhaseTransition(CONTESTED_BALL, false, 18, 0, 0),
                        new MatchPhaseTransition(CONTESTED_BALL, false, 3, 0, 0),
                        new MatchPhaseTransition(CONTESTED_BALL, false, 3, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 7, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 4, 0, 0),
                        new MatchPhaseTransition(POSSESSION, true, 4, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 10, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 12, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 10, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 12, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 21, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 39, 0, 23),
                        new MatchPhaseTransition(THROW_IN, true, 9, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 14, 0, 0)
                }));
        L_CLF19.addRow(TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 8, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 5, 0, 0),
                        new MatchPhaseTransition(ATTACKING_TRANSITION, true, 19, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 6, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 6, 0, 0),
                        new MatchPhaseTransition(GOALKEEPER_POSSESSION, false, 5, 0, 0),
                        new MatchPhaseTransition(GOAL_KICK, false, 21, 21, 0),
                        new MatchPhaseTransition(POSSESSION, true, 5, 0, 0)
                }));
    }

    public static void loadHomeTeamDeprecated() {
        L_CLF19_DEPRECATED.addRow(MAIN,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(KICK_OFF, true, 0, 0, 0)
                }));
        L_CLF19_DEPRECATED.addRow(KICK_OFF,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 2, 0, 0)
                }));
        L_CLF19_DEPRECATED.addRow(POSSESSION,
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
        L_CLF19_DEPRECATED.addRow(TRANSITION,
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
        L_CLF19_DEPRECATED.addRow(ATTACKING_TRANSITION,
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
        L_CLF19_DEPRECATED.addRow(PENALTY,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(GOAL, false, 91, 91, 0)
                }));
        L_CLF19_DEPRECATED.addRow(THROW_IN,
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
        L_CLF19_DEPRECATED.addRow(ATTACKING_POSSESSION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(ATTACK, true, 5, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 3, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 5, 0, 0),
                }));
        L_CLF19_DEPRECATED.addRow(ATTACK,
                new MatchSequence(new MatchPhaseTransition[]{
                        new MatchPhaseTransition(CORNER_KICK, true, 39, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 13, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 20, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 13, 0, 0),
                }));
        L_CLF19_DEPRECATED.addRow(PRESSURE,
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
        L_CLF19_DEPRECATED.addRow(CORNER_KICK,
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
        L_CLF19_DEPRECATED.addRow(DEFENSIVE_TRANSITION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(THROW_IN, false, 12, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 29, 0, 0),
                        new MatchPhaseTransition(DEFENSIVE_TRANSITION, false, 5, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 9, 0, 0),
                        new MatchPhaseTransition(POSSESSION, false, 34, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, false, 9, 0, 0),
                }));
        L_CLF19_DEPRECATED.addRow(COUNTER_ATTACK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 18, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 46, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 36, 0, 0),
                        new MatchPhaseTransition(CORNER_KICK, true, 13, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true, 25, 0, 0),
                        new MatchPhaseTransition(TRANSITION, false, 8, 0, 0),
                        new MatchPhaseTransition(ATTACK, true, 11, 0, 0)
                }));
        L_CLF19_DEPRECATED.addRow(FREE_KICK,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 3, 0, 0),
                        new MatchPhaseTransition(THROW_IN, false, 48, 0, 0),
                        new MatchPhaseTransition(PRESSURE, false, 19, 0, 0)
                }));
        L_CLF19_DEPRECATED.addRow(INTERCEPTION,
                new MatchSequence(new MatchPhaseTransition[] {
                        new MatchPhaseTransition(TRANSITION, false, 10, 0, 0),
                        new MatchPhaseTransition(TRANSITION, true, 4, 0, 0),
                        new MatchPhaseTransition(TRANSITION, true, 4, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, false, 1, 0, 0),
                        new MatchPhaseTransition(ATTACKING_POSSESSION, false, 5, 0, 0),
                }));
    }
}
