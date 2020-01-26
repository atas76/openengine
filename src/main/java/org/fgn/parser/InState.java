package org.fgn.parser;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InState extends State {

    public InState() { }

    public InState(String description) {
        defineStateSpace(description);
    }

    void defineStateSpace(String description) {
        if (Arrays.stream(InStateContext.values()).map(Enum::toString).collect(Collectors.toList()).contains(description)) {
            setContext(StateContext.valueOf(description));
        } else {
            setSpace(Coordinates.valueOf(description));
        }
    }
}
