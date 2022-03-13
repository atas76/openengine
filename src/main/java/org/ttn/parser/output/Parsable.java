package org.ttn.parser.output;

public interface Parsable {

    enum DirectiveType {
        BUILDUP_PRESSURE_BLOCK,
        SET_PIECE_EXECUTION_BLOCK,
        POSSESSION_CHAIN_BLOCK,
        BALL_RECOVERY_BLOCK,
        ATTACK_CHAIN_BLOCK,
        TRANSITION_CHAIN_BLOCK,
        POSSESSOR_DEFINITION,
        BREAK,
        ATTACKING_POSSESSION,
        BREAK_BALL_ACTION,
        ATTACK_ACTION,
        RELEASE_PRESSING_ACTION
    }

    enum StatementType {
        INDIRECT_OUTCOME,
        STANDARD,
        TRIVIAL_POSSESSION_CHAIN
    }
}
