package org.fgn.ontology;

import java.util.Map;
import java.util.stream.Collectors;

public class ActionOutcomeContext {

    private static Map<String, BaseObject> entities;

    public static boolean hasEntity(String key) {
        return entities.containsKey(key);
    }

    public static BaseObject getEntity(String key) {
        return entities.get(key);
    }

    public static void load(Ontology ontology) {
        entities = ontology.getOutcome().stream().collect(Collectors.toMap(BaseObject::getId, baseObject -> baseObject));
    }
}
