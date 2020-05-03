package org.fgn.domain;

import org.fgn.schema.BaseObject;
import org.fgn.schema.Schema;
import org.fgn.schema.SchemaException;

import java.util.Map;
import java.util.stream.Collectors;

public class ActionOutcome {

    private static Map<String, BaseObject> entities;

    public static boolean hasEntity(String key) {
        return entities.containsKey(key);
    }

    public static BaseObject getEntity(String key) {

        if (entities.get(key) == null) {
            throw new SchemaException("Object '" + key + "' not supported by ontology");
        }

        return entities.get(key);
    }

    public static void load(Schema schema) {
        entities = schema.getOutcome().stream().collect(Collectors.toMap(BaseObject::getId, baseObject -> baseObject));
    }
}
