package org.fgn.domain;

public class Context {

    public enum InState  {
        KO, // Kick-off
        FREE,
        PK, // Penalty-kick
        T, // Throw-in
        CK, // Corner kick
        GK, // Goal-kick
        SP, // Set piece
    }

    public enum OutState {
        FREE,
        H, // Handball
        G, // Goal
        C, // Corner
        T, // Throw-in
        GK, // Goal-kick
        F, // Foul
        O // Offside
    }

    public enum Coordinate {
        PenaltyArea
    }

    public enum GoalAngle {
        Straight,
        Diagonal,
        Side;

        public static GoalAngle getDefault() {
            return Straight;
        }
    }
}
