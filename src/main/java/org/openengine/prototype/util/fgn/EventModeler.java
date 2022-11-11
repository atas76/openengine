package org.openengine.prototype.util.fgn;

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
        Coordinates customCoordinatesOut = CoordinatesSchemaMapping.getCoordinates(
                statement.getStateOut().getSpace().getId());

        if (nonNull(contextCoordinates)) {
            inputState.setCoordinates(contextCoordinates);
        } else {
            Coordinates customCoordinatesIn =
                    CoordinatesSchemaMapping.getCoordinates(statement.getStateIn().getSpace().getId());

            if (nonNull(customCoordinatesIn)) {
                inputState.setCoordinates(customCoordinatesIn);
            } else {
                inputState.setCoordinates(new Coordinates(
                        Coordinates.X.valueOf(statement.getStateIn().getSpace().getId())));
            }
        }

        OutState outputState = event.getOutputState();
        outputState.setContext(Context.OutState.valueOf(statement.getStateOut().getContext().getId()));

        if (nonNull(customCoordinatesOut)) {
            outputState.setCoordinates(customCoordinatesOut);
        } else {
            outputState.setCoordinates(
                    new Coordinates(Coordinates.X.valueOf(statement.getStateOut().getSpace().getId())));
        }

        return event;
    }
}
