package org.openengine.openfootie;

public record MatchDataElement(MatchDataElementType type, boolean retainPossession,
                               int duration, int breakTime, int injuryTime) { }
