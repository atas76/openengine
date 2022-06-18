package org.ttn.semantics;

import org.ttn.engine.rules.SetPiece;
import org.ttn.parser.output.Directive;
import org.ttn.parser.output.MatchDataElement;
import org.ttn.semantics.exceptions.InvalidPhaseException;
import org.ttn.semantics.exceptions.InvalidPhaseDefinitionException;

import java.util.ArrayList;
import java.util.List;

public class MatchRepresentation {

    private List<MatchPhase> matchPhases = new ArrayList<>();
    private List<MatchDataElement> matchDataElements;

    public MatchRepresentation(List<MatchDataElement> matchDataElements)
            throws InvalidPhaseDefinitionException, InvalidPhaseException {
        this.matchDataElements = matchDataElements;
        int index = 0;
        while (index < this.matchDataElements.size()) {
            index = processPhase(index);
        }
        checkKickOff();
    }

    private void checkKickOff() throws InvalidPhaseException {
        MatchPhase initialMatchPhase = matchPhases.get(0);
        if (initialMatchPhase instanceof SetPieceExecutionPhase setPiecePhase) {
            if (!setPiecePhase.getType().equals(SetPiece.KICK_OFF)) {
                throw new InvalidPhaseException("Kick-off exception");
            }
        } else {
            throw new InvalidPhaseException("Kick-off phase expected");
        }
    }

    private int processPhase(int index) throws InvalidPhaseDefinitionException, InvalidPhaseException {

        MatchDataElement initialMatchElement = matchDataElements.get(index);

        if (initialMatchElement instanceof Directive phaseDefinitionDirective) {
            switch (phaseDefinitionDirective.getType()) {
                case SET_PIECE_EXECUTION_BLOCK:
                    matchPhases.add(new SetPieceExecutionPhase(phaseDefinitionDirective.getSetPiece(),
                            phaseDefinitionDirective.getTeam()));
                    break;
                default:
                    throw new InvalidPhaseException("Unsupported phase");
            }
        } else {
            throw new InvalidPhaseDefinitionException("Directive expected");
        }
        index++;
        while (index < matchDataElements.size()) {
            index = processEvent(index);
        }
        return index;
    }

    private int processEvent(int index) {
        return ++index;
    }

    public MatchPhase getPhase(int index) {
        return this.matchPhases.get(index);
    }
}
