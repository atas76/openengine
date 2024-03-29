package org.openengine.prototype.util;

import org.fgn.schema.ActionOutcome;
import org.fgn.schema.ActionType;
import org.fgn.schema.Coordinates;
import org.fgn.schema.StateContext;
import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.fgn.parser.Parser;
import org.fgn.parser.Statement;
import org.fgn.parser.exceptions.ParserException;
import org.fgn.schema.data.Schema;
import org.openengine.prototype.util.fgn.StatementWrapper;
import org.openengine.prototype.util.fgn.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.nonNull;

public class ParserUtil {

    private static final String FGN_ROOT = "src/main/resources/data/fgn";

    public static void main(String[] args) throws IOException, ScannerException {

        String file = FGN_ROOT + "/" + args[0];

        SymbolTable symbolTable = new SymbolTable();

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

                StatementWrapper statementWrapper = new StatementWrapper();
                for (String token: tokens) {
                    statementWrapper.addToken(new Token(token));
                }

                symbolTable.addStatementTokens(statementWrapper);

                Statement statement = new Parser(tokens).parse();

                if (nonNull(statement.getStateIn())) { // Commented-out statement
                    symbolTable.addStateIn(statement.getStateIn());
                    symbolTable.addStateOut(statement.getStateOut());
                } else {
                    continue;
                }

                // TODO Optional may also be used
                if (nonNull(statement.getAction())) {
                    symbolTable.addAction(statement.getAction());
                }

            } catch (ScannerException ex) {
                System.out.println("Error in line " + lineCount);
                System.out.println("Invalid token: " + ex.getMessage());
                System.exit(1);
            } catch (ParserException ex) {
                System.out.println("Error in line " + lineCount);
                System.out.println("Error message: " + ex.getMessage());
                System.exit(2);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Unknown error in line " + lineCount);
                printCurrentErrorState(currentLine, new Scanner(currentLine).scan());
                System.exit(3);
            }
        }

        symbolTable.printReport();
    }

    private static void printCurrentErrorState(String currentLine, List<String> tokens) {
        System.out.println("Current line: " + currentLine);
        System.out.println("Token size: " + tokens.size());
        System.out.println("Tokens");
        for (String token: tokens) {
            System.out.println(token);
        }
    }
}
