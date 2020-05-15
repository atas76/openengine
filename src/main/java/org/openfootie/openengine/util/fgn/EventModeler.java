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

        Coordinates contextCoordinates = ContextRelationships.stateCoordinatesMap.get(inputState.getContext());

        if (nonNull(contextCoordinates)) {
            inputState.setCoordinates(contextCoordinates);
        }

        OutState outputState = event.getOutputState();
        outputState.setCoordinates(new Coordinates(Coordinates.X.valueOf(statement.getStateOut().getSpace().getId())));

        return event;
    }
}
