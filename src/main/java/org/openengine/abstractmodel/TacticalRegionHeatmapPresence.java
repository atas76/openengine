package org.openengine.abstractmodel;

public record TacticalRegionHeatmapPresence(String teamTag, TacticalPosition tacticalPosition, double weight) {
    public TacticalRegionHeatmapPresence(TacticalPosition tacticalPosition, double weight) {
        this(null, tacticalPosition, weight);
    }
}

