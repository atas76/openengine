package org.fgn.domain;

import java.util.HashMap;
import java.util.Map;

public class CoordinatesSchemaMapping {

    private static Map<String, Coordinates> coordinatesShortcutMap = new HashMap<>();

    static {
        coordinatesShortcutMap.put("Apc",
                new Coordinates(Coordinates.X.A, Coordinates.Y.C,
                        Context.Coordinate.PenaltyArea, Context.GoalAngle.Diagonal));
    }

    public static Coordinates getCoordinates(String schemaReference) {
        return coordinatesShortcutMap.get(schemaReference);
    }
}
