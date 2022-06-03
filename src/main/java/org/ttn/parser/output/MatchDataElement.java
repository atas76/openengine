package org.ttn.parser.output;

public interface MatchDataElement {

    enum DirectiveType {
        INPLAY_PHASE,
        SET_PIECE_EXECUTION_BLOCK,
        TRANSITION_CHAIN_BLOCK,
        POSSESSOR_DEFINITION,
        BREAK,
        HALF_TIME,
        INJURY,
        ATTACKING_TRANSITION,
        SUBSTITUTION,
        FAIR_PLAY,
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
