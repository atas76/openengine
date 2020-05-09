package org.openfootie.openengine.util.fgn;

import org.fgn.domain.Event;
import org.fgn.parser.Statement;

public class EventModeler {

    public static Event model(Statement statement) {
        return new Event();
    }
}
