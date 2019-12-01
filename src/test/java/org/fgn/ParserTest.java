package org.fgn;

import org.fgn.lexan.Scanner;
import org.fgn.lexan.exceptions.ScannerException;
import org.fgn.parser.MatchTime;
import org.fgn.parser.Parser;
import org.fgn.parser.Statement;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void testDefaultActionParsing() throws ScannerException {

        String strStatement = "00:00 L: KO => DM";

        List<String> tokens = new Scanner(strStatement).scan();

        Statement parsedStatement = new Parser(tokens).parse();

        assertEquals(new MatchTime(0, 0), parsedStatement.getTime());
    }
}
