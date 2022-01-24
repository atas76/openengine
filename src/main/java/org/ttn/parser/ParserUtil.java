package org.ttn.parser;

import org.ttn.engine.space.PitchPosition;

public class ParserUtil {

    public static PitchPosition getPitchPosition(String pitchPosition) throws IllegalArgumentException {
        return PitchPosition.valueOf(pitchPosition);
    }
}
