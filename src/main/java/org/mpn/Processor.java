package org.mpn;

import org.mpn.exceptions.UnknownDirectiveException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Processor {

    public List<ProcessUnit> process(Path path) {

        List<ProcessUnit> dataset = new ArrayList<>();

        try (var lines = Files.lines(path)) {
            lines.forEach(line -> {
                try {
                    dataset.add(process(line));
                } catch (Exception e) {
                    System.out.println(line);
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }

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
