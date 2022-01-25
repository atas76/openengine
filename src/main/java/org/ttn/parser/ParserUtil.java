package org.ttn.parser;

import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;

import java.util.List;

public class ParserUtil {

    public static PitchPosition getPitchPosition(String pitchPosition) throws IllegalArgumentException {
        return PitchPosition.valueOf(pitchPosition);
    }

    public static TacticalPosition.X getTacticalPositionX(String tacticalPositionX) throws IllegalArgumentException {
        return TacticalPosition.X.valueOf(tacticalPositionX);
    }

    public static TacticalPosition.Y getTacticalPositionY(String tacticalPositionY) throws IllegalArgumentException {
        return TacticalPosition.Y.valueOf(tacticalPositionY);
    }

    public static TacticalPosition parseTacticalPosition(List<String> tokens) {
        return new TacticalPositionImpl(getTacticalPositionX(tokens.get(0)), getTacticalPositionY(tokens.get(1)));
    }
}
