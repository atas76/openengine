package org.fgn.ontology;

import java.util.Map;
import java.util.stream.Collectors;

public class StateContext {

    private static Map<String, BaseObject> entities;

    public static Map<String, BaseObject> getEntities() {
        return entities;
    }

    public static boolean hasEntity(String key) {
        return entities.containsKey(key);
    }

    public static void load(Ontology ontology) {
        entities = ontology.getContext().stream().collect(Collectors.toMap(BaseObject::getId, baseObject -> baseObject));
    }
}
