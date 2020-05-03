package org.fgn.schema;

import org.fgn.schema.data.BaseObject;
import org.fgn.schema.data.Schema;

import java.util.Map;
import java.util.stream.Collectors;

public class ActionType {

    private static Map<String, BaseObject> entities;

    public static boolean hasEntity(String key) {
        return entities.containsKey(key);
    }

    public static void load(Schema schema) {
        entities = schema.getActions().stream().collect(Collectors.toMap(BaseObject::getId, baseObject -> baseObject));
    }
}
