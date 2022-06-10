package org.ttn.parser.output;

public interface MatchDataElement {

    enum DirectiveType {
        INPLAY_PHASE,
        INTENTION,
        SET_PIECE_EXECUTION_BLOCK,
        TRANSITION_CHAIN_BLOCK,
        POSSESSOR_DEFINITION,
        BREAK,
        HALF_TIME,
        INJURY,
        SUBSTITUTION,
        FAIR_PLAY
    }

    enum StatementType {
        INDIRECT_OUTCOME,
        STANDARD,
        TRIVIAL_POSSESSION_CHAIN
    }
}
