package org.fgn.domain;

import org.fgn.ontology.BaseObject;
import org.fgn.ontology.Ontology;
import org.fgn.ontology.OntologyException;

import java.util.Map;
import java.util.stream.Collectors;

public class ActionOutcome {

    private static Map<String, BaseObject> entities;

    public static boolean hasEntity(String key) {
        return entities.containsKey(key);
    }

    public static BaseObject getEntity(String key) {

        if (entities.get(key) == null) {
            throw new OntologyException("Object '" + key + "' not supported by ontology");
        }

        return entities.get(key);
    }

    public static void load(Ontology ontology) {
        entities = ontology.getOutcome().stream().collect(Collectors.toMap(BaseObject::getId, baseObject -> baseObject));
    }
}
