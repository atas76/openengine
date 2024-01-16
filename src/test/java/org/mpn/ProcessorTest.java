package org.mpn;

import org.junit.Test;
import org.mpn.exceptions.UnknownDirectiveException;

import static org.junit.Assert.*;

public class ProcessorTest {

    private Processor processor = new Processor();

    @Test
    public void testBreakDirective() throws Exception {
        String line = "#! BREAK";

        ProcessUnit directive = processor.process(line);

        assertTrue(directive instanceof Directive);
        assertEquals(Directive.BREAK, directive);
    }

    @Test
    public void testHalfTimeDirective() throws Exception {
        String line = "#! HT";

        ProcessUnit directive = processor.process(line);

        assertTrue(directive instanceof Directive);
        assertEquals(Directive.HT, directive);
    }

    @Test
    public void testUnknownDirective() throws Exception {
        String line = "#! SOME_DIRECTIVE";

        try {
            processor.process(line);
        } catch (UnknownDirectiveException exception) {
            return;
        }
        fail("UnknownDirectiveException expected");
    }

    @Test
    public void testProcessStatement() throws Exception {
        String line = "L: 00:00 KickOff -> Attack";

        ProcessUnit statement = processor.process(line);

        assertTrue(statement instanceof Statement);
    }
}
