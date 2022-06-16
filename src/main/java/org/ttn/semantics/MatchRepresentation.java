package org.ttn.semantics;

import org.ttn.parser.output.Directive;
import org.ttn.parser.output.MatchDataElement;
import org.ttn.semantics.exceptions.InvalidPhaseException;

import java.util.ArrayList;
import java.util.List;

import static org.ttn.engine.rules.SetPiece.KICK_OFF;
import static org.ttn.parser.output.MatchDataElement.DirectiveType.SET_PIECE_EXECUTION_BLOCK;

public class MatchRepresentation {

    private List<MatchPhase> matchPhases = new ArrayList<>();

    public MatchRepresentation(List<MatchDataElement> matchDataElements) throws InvalidPhaseException {

        MatchDataElement kickOffElement =  matchDataElements.get(0);
        MatchPhase currentMatchPhase;

        if (kickOffElement instanceof Directive directive) {
            if (SET_PIECE_EXECUTION_BLOCK.equals(directive.getType())) {
                if (KICK_OFF.equals(directive.getSetPiece())) {
                    currentMatchPhase = new SetPieceExecutionPhase(KICK_OFF);
                    matchPhases.add(currentMatchPhase);
                } else {
                    throw new InvalidPhaseException("Match data should start with a kick-off");
                }
            } else {
                throw new InvalidPhaseException("Match data should start with a kick-off directive");
            }
        } else {
            throw new InvalidPhaseException("Match data should start with a kick-off directive");
        }
    }

    public MatchPhase getMatchPhase(int index) {
        return matchPhases.get(index);
    }
}
