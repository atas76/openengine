package org.ttn.semantics;

import org.ttn.parser.output.Directive;
import org.ttn.parser.output.MatchDataElement;
import org.ttn.semantics.exceptions.InvalidPhaseException;
import org.ttn.semantics.exceptions.InvalidPhaseStartException;

import java.util.ArrayList;
import java.util.List;

import static org.ttn.engine.rules.SetPiece.KICK_OFF;
import static org.ttn.parser.output.MatchDataElement.DirectiveType.SET_PIECE_EXECUTION_BLOCK;

public class MatchRepresentation {

    private List<MatchPhase> matchPhases = new ArrayList<>();
    private MatchPhase currentPhase;
    private String currentTeam;
    private List<MatchDataElement> matchDataElements;

    public MatchRepresentation(List<MatchDataElement> matchDataElements) throws InvalidPhaseException {
        this.matchDataElements = matchDataElements;
        initializeKickOffPhase();
    }

    @Deprecated
    private void initializeKickOffPhase() throws InvalidPhaseException {
        MatchDataElement kickOffElement = matchDataElements.get(0);

        if (kickOffElement instanceof Directive directive) {
            if (SET_PIECE_EXECUTION_BLOCK.equals(directive.getType())) {
                if (KICK_OFF.equals(directive.getSetPiece())) {
                    currentTeam = directive.getTeam();
                    currentPhase = new SetPieceExecutionPhase(KICK_OFF, currentTeam);
                    matchPhases.add(currentPhase);
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

    private void processPhase(int index) throws InvalidPhaseStartException, InvalidPhaseException {

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
            throw new InvalidPhaseStartException("Directive expected");
        }
    }

    public MatchPhase getPhase(int index) {
        return this.matchPhases.get(index);
    }
}
