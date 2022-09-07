package org.ttn.semantics;

import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.rules.SetPiece;
import org.ttn.parser.output.Directive;
import org.ttn.parser.output.MatchDataElement;
import org.ttn.parser.output.Statement;
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
            if (matchDataElements.get(index) instanceof Directive directive) {
                switch (directive.getType()) {
                    case BREAK:
                        this.matchPhases.get(matchPhases.size() - 1).setFlowBreak();
                        ++index; // TODO remove duplication
                        continue;
                }
            }
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
                case SET_PIECE_EXECUTION_BLOCK ->
                        matchPhases.add(new SetPieceExecutionPhase(phaseDefinitionDirective.getSetPiece(),
                                phaseDefinitionDirective.getTeam()));
                case INPLAY_PHASE -> matchPhases.add(new InPlayPhase(phaseDefinitionDirective.getInPlayPhase(),
                        phaseDefinitionDirective.getTeam()));
                default -> throw new InvalidPhaseException("Unsupported phase");
            }
        } else {
            throw new InvalidPhaseDefinitionException("Directive expected");
        }
        index++;
        while (index < matchDataElements.size()) {
            if (matchDataElements.get(index) instanceof Directive directive &&
                    directive.getType().equals(MatchDataElement.DirectiveType.POSSESSOR_DEFINITION)) {
                TacticalPosition directiveTacticalPosition = directive.getTacticalPosition();
                MatchPhase.TacticalPosition matchPhaseTacticalPosition =
                        new MatchPhase.TacticalPosition(directiveTacticalPosition.getX(), directiveTacticalPosition.getY());
                this.matchPhases.get(matchPhases.size() - 1).setInitialPossessor(matchPhaseTacticalPosition);
                index++; // TODO remove duplication
            }
            if (matchDataElements.get(index) instanceof Statement event) {
                this.matchPhases.get(matchPhases.size() - 1).addEvent(event);
                index++;
            } else {
                break;
            }
        }
        return index;
    }

    private int readEvent(int index) {
        if (matchDataElements.get(index) instanceof Statement event) {
            this.matchPhases.get(matchPhases.size() - 1).addEvent(event);
            return ++index;
        }
        return index;
    }

    public MatchPhase getPhase(int index) {
        return this.matchPhases.get(index);
    }

    public int getNumberOfPhases() {
        return this.matchPhases.size();
    }
}
