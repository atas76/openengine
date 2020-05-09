package org.fgn;

import org.fgn.schema.data.Schema;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SchemaTest {

    private static final String FGN_ROOT = "src/test/resources/data/fgn";

    @Test
    public void testLoad() throws IOException {

        Schema schema = Schema.create(FGN_ROOT + "/schema/classic.json");

        assertEquals(12, schema.getContext().size());
        assertEquals(2, schema.getOutcome().size());
        assertEquals(18, schema.getCoordinates().size());
        assertEquals(16, schema.getActions().size());

        assertEquals("FREE", schema.getContext().get(0).getId());
        assertEquals("Free play", schema.getContext().get(0).getName());
        assertNull(schema.getContext().get(0).getDescription());

        assertEquals("PST", schema.getOutcome().get(0).getId());
        assertEquals("Ball hitting the post", schema.getOutcome().get(0).getDescription());

        assertEquals("Awp", schema.getCoordinates().get(14).getId());
        assertEquals("0.9", schema.getCoordinates().get(14).getX());
        assertEquals("Apw", schema.getCoordinates().get(14).getLateral());

        assertEquals("SwapWing", schema.getActions().get(15).getId());
    }
}
