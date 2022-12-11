package org.openengine.openfootie;

public record MatchPhaseTransition(MatchDataElementType outcomeType, boolean retainPossession,
                                   int duration, int breakTime, int injuryTime) { }
