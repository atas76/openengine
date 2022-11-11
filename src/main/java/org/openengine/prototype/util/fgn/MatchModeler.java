package org.openengine.prototype.util.fgn;

import org.fgn.domain.Event;
import org.fgn.domain.EventSequence;
import org.fgn.domain.Match;
import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.fgn.parser.Parser;
import org.fgn.parser.Statement;
import org.fgn.parser.exceptions.ParserException;
import org.fgn.schema.ActionOutcome;
import org.fgn.schema.ActionType;
import org.fgn.schema.Coordinates;
import org.fgn.schema.StateContext;
import org.fgn.schema.data.Schema;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MatchModeler {

    private static final String FGN_ROOT = "src/main/resources/data/fgn";

    private static EventModeler eventModeler = new EventModeler();

    private static Match match = new Match();

    public static void main(String[] args) throws IOException {

        EventSequence sequence = new EventSequence();

        String file = FGN_ROOT + "/" + args[0];

        BufferedReader reader = new BufferedReader(new FileReader(file));

        Schema schema = Schema.create(FGN_ROOT + "/schema/classic.json");
        StateContext.load(schema);
        ActionOutcome.load(schema);
        Coordinates.load(schema);
        ActionType.load(schema);

        String currentLine = "";
        int lineCount = 0;

        while ((currentLine = reader.readLine()) != null) {

            ++lineCount;

            try {

                List<String> tokens = new Scanner(currentLine).scan();

                Statement statement = new Parser(tokens).parse();

                // Magic method that transforms an FGN statement to an event model
                Event event = EventModeler.model(statement);

                sequence.add(event);

            } catch (ScannerException ex) {
                System.out.println("Error in line " + lineCount);
                System.out.println("Invalid token: " + ex.getMessage());
                System.exit(1);
            } catch (ParserException ex) {
                System.out.println("Error in line " + lineCount);
                System.out.println("Error message: " + ex.getMessage());
                System.exit(2);
            }
        }

        // Just use one default sequence for now
        match.add(sequence);
    }
}
