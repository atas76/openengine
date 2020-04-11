package org.fgn.ontology;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ontology {

    private List<BaseObject> context;
    private List<BaseObject> outcome;
    private List<CoordinateObject> coordinates;
    private List<BaseObject> actions;

    private Set<String> contextIds;
    private Set<String> outcomeIds;
    private Set<String> coordinateIds;
    private Set<String> actionIds;

    public static Ontology create(String source) throws IOException {
        return new ObjectMapper().readValue(new File(source), Ontology.class);
    }

    public Set<String> getContextIds() {
        return getEntityIds(contextIds, context);
    }

    public Set<String> getOutcomeIds() {
        return getEntityIds(outcomeIds, outcome);
    }

    public Set<String> getCoordinateIds() {
        return getEntityIds(coordinateIds, coordinates);
    }

    public Set<String> getActionIds() {
        return getEntityIds(actionIds, actions);
    }

    private Set<String> getEntityIds(Set<String> entityIds, List<? extends BaseObject> entities) {
        if (entityIds == null) {
            entityIds = entities.stream().map(BaseObject::getId).collect(Collectors.toSet());
        }
        return entityIds;
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
