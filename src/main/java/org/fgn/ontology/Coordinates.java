package org.fgn.ontology;

import java.util.Map;
import java.util.stream.Collectors;

public class Coordinates {

    private static Map<String, CoordinateObject> entities;

    public static CoordinateObject getEntity(String key) {
        return entities.get(key);
    }

    public static void load(Ontology ontology) {
        entities = ontology.getCoordinates().stream().
                collect(Collectors.toMap(CoordinateObject::getId, coordinateObject -> coordinateObject));
    }
}
