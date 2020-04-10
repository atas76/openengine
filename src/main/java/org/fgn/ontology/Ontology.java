package org.fgn.ontology;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Ontology {

    private List<BaseObject> context;
    private List<BaseObject> outcome;
    private List<CoordinateObject> coordinates;
    private List<BaseObject> actions;

    public static Ontology create(String source) throws IOException {
        return new ObjectMapper().readValue(new File(source), Ontology.class);
    }

    public List<BaseObject> getContext() {
        return context;
    }

    public List<BaseObject> getOutcome() {
        return outcome;
    }

    public List<CoordinateObject> getCoordinates() {
        return coordinates;
    }

    public List<BaseObject> getActions() {
        return actions;
    }
}
