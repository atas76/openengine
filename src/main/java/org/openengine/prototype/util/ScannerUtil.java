package org.openengine.prototype.util;

import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.openengine.prototype.util.fgn.StatementWrapper;
import org.openengine.prototype.util.fgn.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ScannerUtil {

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

            } catch (ScannerException ex) {
                System.out.println("Error in line " + lineCount);
                System.out.println("Invalid token: " + ex.getMessage());
                System.exit(1);
            }
        }

        symbolTable.printReport();
    }
}
