package org.fgn;

import org.fgn.lexan.Scanner;
import org.fgn.lexan.Token;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScannerTest {

    private String statement = "00:00 L: KO => DM";

    @Test
    public void testStatementScan() {

        List<Token> tokens = Scanner.scan(statement);

        assertEquals(4, tokens.size());
    }
}
