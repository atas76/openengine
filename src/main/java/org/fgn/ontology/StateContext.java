package org.fgn.ontology;

import java.util.Set;

public class StateContext {

    private static Set<String> keys;

    public static Set<String> getKeys() {
        return keys;
    }

    public static boolean hasEntity(String key) {
        return keys.contains(key);
    }

    public static void load(Ontology ontology) {
        keys = ontology.getContextIds();
    }
}
