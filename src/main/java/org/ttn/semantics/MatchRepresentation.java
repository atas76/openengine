package org.ttn.semantics;

import org.ttn.parser.output.Directive;
import org.ttn.parser.output.MatchDataElement;
import org.ttn.semantics.exceptions.InvalidPhaseException;
import org.ttn.semantics.exceptions.InvalidPhaseDefinitionException;

import java.util.ArrayList;
import java.util.List;

public class MatchRepresentation {

    private List<MatchPhase> matchPhases = new ArrayList<>();
    private MatchPhase currentPhase;
    private String currentTeam;
    private List<MatchDataElement> matchDataElements;

    public MatchRepresentation(List<MatchDataElement> matchDataElements)
            throws InvalidPhaseDefinitionException, InvalidPhaseException {
        this.matchDataElements = matchDataElements;
        initializeKickOffPhase();
    }

    @Deprecated
    private void initializeKickOffPhase() throws InvalidPhaseDefinitionException, InvalidPhaseException {
        processPhase(0);
    }

    private void processPhase(int index) throws InvalidPhaseDefinitionException, InvalidPhaseException {

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
    }

    public MatchPhase getPhase(int index) {
        return this.matchPhases.get(index);
    }
}
