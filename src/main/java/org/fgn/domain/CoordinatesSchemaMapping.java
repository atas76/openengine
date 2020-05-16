package org.fgn.domain;

import java.util.HashMap;
import java.util.Map;

public class CoordinatesSchemaMapping {

    private static Map<String, Coordinates> coordinatesShortcutMap = new HashMap<>();

    static {
        // TODO this is put here as an example
        //  this kind of mapping will be only needed for non-trivial conventions:
        //   a typical example would be lateral ('wing') positions coordinates
        coordinatesShortcutMap.put("DM", new Coordinates(Coordinates.X.DM));
    }

    public static Coordinates getCoordinates(String schemaReference) {
        return coordinatesShortcutMap.get(schemaReference);
    }
}
