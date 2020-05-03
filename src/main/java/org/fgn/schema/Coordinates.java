package org.fgn.schema;

import org.fgn.schema.data.CoordinateObject;
import org.fgn.schema.data.Schema;
import org.fgn.schema.data.SchemaException;

import java.util.Map;
import java.util.stream.Collectors;

public class Coordinates {

    private static Map<String, CoordinateObject> entities;

    public static CoordinateObject getEntity(String key) {

        if (entities.get(key) == null) {
            throw new SchemaException("Object '" + key + "' not supported by ontology");
        }

        return entities.get(key);
    }

    public static void load(Schema schema) {
        entities = schema.getCoordinates().stream().
                collect(Collectors.toMap(CoordinateObject::getId, coordinateObject -> coordinateObject));
    }
}
