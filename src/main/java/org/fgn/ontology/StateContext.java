package org.fgn.ontology;

import java.util.Map;
import java.util.stream.Collectors;

public class StateContext {

    private static Map<String, BaseObject> entities;

    public static boolean hasEntity(String key) {
        return entities.containsKey(key);
    }

    public static BaseObject getEntity(String key) {
        return entities.get(key);
    }

    public static BaseObject getDefault() {
        return entities.get("FREE");
    }

    public static void load(Ontology ontology) {
        entities = ontology.getContext().stream().collect(Collectors.toMap(BaseObject::getId, baseObject -> baseObject));
    }
}
