package org.ttn.parser.output;

public interface MatchDataElement {

    enum DirectiveType {
        BUILDUP_PRESSURE_BLOCK,
        SET_PIECE_EXECUTION_BLOCK,
        POSSESSION_CHAIN_BLOCK,
        BALL_RECOVERY_BLOCK,
        ATTACK_CHAIN_BLOCK,
        TRANSITION_CHAIN_BLOCK,
        POSSESSOR_DEFINITION,
        BREAK,
        HALF_TIME,
        ATTACKING_POSSESSION,
        DEFENSIVE_TRANSITION,
        ATTACKING_TRANSITION,
        COUNTER_ATTACK,
        SUBSTITUTION,
        BREAK_BALL_ACTION,
        ATTACK_ACTION,
        RELEASE_PRESSING_ACTION,
        COOL_DOWN_ACTION,
        WITHDRAW_PRESSURE_ACTION
    }

    enum StatementType {
        INDIRECT_OUTCOME,
        STANDARD,
        TRIVIAL_POSSESSION_CHAIN
    }
}
