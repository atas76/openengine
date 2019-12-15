package org.openfootie.openengine.util;

import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.fgn.parser.Parser;
import org.fgn.parser.Statement;
import org.fgn.parser.exceptions.ParserException;
import org.openfootie.openengine.util.fgn.StatementWrapper;
import org.openfootie.openengine.util.fgn.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MatchNotationParsing {

    private static final String FGN_ROOT = "src/main/resources/data/fgn";

    public static void main(String[] args) throws IOException {

        String file = FGN_ROOT + "/" + args[0];

        SymbolTable symbolTable = new SymbolTable();

        BufferedReader reader = new BufferedReader(new FileReader(file));

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
                symbolTable.addStateIn(statement.getStateIn());
                symbolTable.addStateOut(statement.getStateOut());
                // TODO Optional may also be used
                if (statement.getAction() != null) {
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
            }
        }

        symbolTable.printReport();
    }
}
