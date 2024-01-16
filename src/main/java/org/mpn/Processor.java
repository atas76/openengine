package org.mpn;

import org.mpn.exceptions.UnknownDirectiveException;

public class Processor {

    public ProcessUnit process(String line) throws Exception {
        if (line.startsWith("#!")) {
            return processDirective(line.substring(2).trim());
        } else {
            return new Parser().parse(line);
        }
    }

    private Directive processDirective(String line) throws UnknownDirectiveException {
        return switch(line) {
            case "BREAK" -> Directive.BREAK;
            case "HT" -> Directive.HT;
            default -> throw new UnknownDirectiveException(line);
        };
    }
}
