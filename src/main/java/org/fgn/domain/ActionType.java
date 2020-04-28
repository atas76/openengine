package org.fgn.domain;

import org.fgn.ontology.BaseObject;
import org.fgn.ontology.Ontology;

import java.util.Map;
import java.util.stream.Collectors;

public class ActionType {

    private static Map<String, BaseObject> entities;

    public static boolean hasEntity(String key) {
        return entities.containsKey(key);
    }

    public static void load(Ontology ontology) {
        entities = ontology.getActions().stream().collect(Collectors.toMap(BaseObject::getId, baseObject -> baseObject));
    }
}
