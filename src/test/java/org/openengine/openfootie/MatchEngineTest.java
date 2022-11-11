package org.openengine.openfootie;

import org.junit.Test;

import java.util.OptionalInt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openengine.openfootie.MatchPhase.*;
import static org.openengine.openfootie.SetPiece.*;

public class MatchEngineTest {

    private MatchEngine matchEngine = new MatchEngine();

    @Test
    public void testGetNextSequenceElement() {

        MatchSequence matchSequence = new MatchSequence(
                new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 0, 0, 0), // 2
                        new MatchDataElement(POSSESSION, true, 0, 0, 0),
                        new MatchDataElement(THROW_IN, true ,0, 0, 0), // 1
                        new MatchDataElement(TRANSITION, false , 0, 0, 0), // 1
                        new MatchDataElement(POSSESSION, false , 0, 0, 0), // 1
                        new MatchDataElement(TRANSITION, true , 0, 0, 0), // 2
                        new MatchDataElement(TRANSITION, true , 1, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, true , 0, 0, 0),
                        new MatchDataElement(COUNTER_ATTACK, true , 0, 0, 0),
                }
        );
        int index = 6;

        MatchDataElement sequence = matchEngine.getNextSequenceElement(matchSequence, index);

        assertEquals(TRANSITION, sequence.type());
        assertTrue(sequence.retainPossession());
        assertEquals(1, sequence.duration());
    }
}
