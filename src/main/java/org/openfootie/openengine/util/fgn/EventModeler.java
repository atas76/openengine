package org.openfootie.openengine.util.fgn;

import org.fgn.domain.*;
import org.fgn.parser.Statement;

import static java.util.Objects.nonNull;

public class EventModeler {

    public static Event model(Statement statement) {

        Event event = new Event();

        event.setTime(statement.getTime().getTotalSeconds());
        event.setTeam(statement.getTeam());

        InState inputState = event.getInputState();
        inputState.setContext(Context.InState.valueOf(statement.getStateIn().getContext().getId()));

        if (nonNull(statement.getAction())) { // Action is defined in statement
            event.setAction(Action.valueOf(statement.getAction().toString().toUpperCase()));
        } else { // Action is implicit from context
            event.setAction(ContextRelationships.actionMap.get(inputState.getContext()));
        }

        Coordinates contextCoordinates = ContextRelationships.stateCoordinatesMap.get(inputState.getContext());

        if (nonNull(contextCoordinates)) {
            inputState.setCoordinates(contextCoordinates);
        } else {
            inputState.setCoordinates(new Coordinates(Coordinates.X.valueOf(statement.getStateIn().getSpace().getId())));
        }

        OutState outputState = event.getOutputState();
        outputState.setCoordinates(new Coordinates(Coordinates.X.valueOf(statement.getStateOut().getSpace().getId())));

        return event;
    }
}
