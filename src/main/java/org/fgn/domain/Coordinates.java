package org.fgn.domain;

import org.fgn.ontology.CoordinateObject;
import org.fgn.ontology.Ontology;
import org.fgn.ontology.OntologyException;

import java.util.Map;
import java.util.stream.Collectors;

public class Coordinates {

    private static Map<String, CoordinateObject> entities;

    public static CoordinateObject getEntity(String key) {

        if (entities.get(key) == null) {
            throw new OntologyException("Object '" + key + "' not supported by ontology");
        }

        return entities.get(key);
    }

    public static void load(Ontology ontology) {
        entities = ontology.getCoordinates().stream().
                collect(Collectors.toMap(CoordinateObject::getId, coordinateObject -> coordinateObject));
    }
}
