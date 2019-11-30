package org.openfootie.openengine.util;

import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.openfootie.openengine.util.fgn.Statement;
import org.openfootie.openengine.util.fgn.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MatchNotationAnalysis {

    private static final String FGN_ROOT = "src/main/resources/data/fgn";

    private static List<Statement> statements = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        String file = FGN_ROOT + "/" + args[0];

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine = "";
        int lineCount = 0;

        while ((currentLine = reader.readLine()) != null) {

            ++lineCount;

            try {

                List<String> tokens = new Scanner(currentLine).scan();

                Statement statement = new Statement();
                for (String token: tokens) {
                    statement.addToken(new Token(token));
                }

                statements.add(statement);

            } catch (ScannerException ex) {
                System.out.println("Error in line " + lineCount);
                System.out.println("Invalid token: " + ex.getMessage());
                System.exit(1);
            }
        }

        printReport(statements);
    }

    private static void printReport(List<Statement> statements) {

        System.out.println("Number of statements: " + statements.size());

        AtomicInteger nTokens = new AtomicInteger();

        statements.forEach(statement -> {
            nTokens.addAndGet(statement.getTokens().size());
        });

        System.out.println("Number of tokens: " + nTokens.intValue());
    }
}
