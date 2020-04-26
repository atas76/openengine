package org.fgn;

import org.fgn.ontology.Ontology;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OntologyTest {

    private static final String FGN_ROOT = "src/test/resources/data/fgn";

    @Test
    public void testLoad() throws IOException {

        Ontology ontology = Ontology.create(FGN_ROOT + "/ontology/classic.json");

        assertEquals(12, ontology.getContext().size());
        assertEquals(2, ontology.getOutcome().size());
        assertEquals(18, ontology.getCoordinates().size());
        assertEquals(16, ontology.getActions().size());

        assertEquals("FREE", ontology.getContext().get(0).getId());
        assertEquals("Free play", ontology.getContext().get(0).getName());
        assertNull(ontology.getContext().get(0).getDescription());

        assertEquals("PST", ontology.getOutcome().get(0).getId());
        assertEquals("Ball hitting the post", ontology.getOutcome().get(0).getDescription());

        assertEquals("Awp", ontology.getCoordinates().get(14).getId());
        assertEquals("0.9", ontology.getCoordinates().get(14).getX());
        assertEquals("Apw", ontology.getCoordinates().get(14).getLateral());

        assertEquals("SwapWing", ontology.getActions().get(15).getId());
    }
}
