package org.openengine.openfootie;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openengine.openfootie.MatchPhase.*;
import static org.openengine.openfootie.SetPiece.*;

public class MatchEngineTest {

    private MatchEngine matchEngine = new MatchEngine();

    @Test
    public void testGetNextSequenceElement() {

        MatchSequence matchSequence = new MatchSequence(
                new MatchPhaseTransition[] {
                        new MatchPhaseTransition(POSSESSION, true, 0, 0, 0), // 2
                        new MatchPhaseTransition(POSSESSION, true, 0, 0, 0),
                        new MatchPhaseTransition(THROW_IN, true ,0, 0, 0), // 1
                        new MatchPhaseTransition(TRANSITION, false , 0, 0, 0), // 1
                        new MatchPhaseTransition(POSSESSION, false , 0, 0, 0), // 1
                        new MatchPhaseTransition(TRANSITION, true , 0, 0, 0), // 2
                        new MatchPhaseTransition(TRANSITION, true , 1, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true , 0, 0, 0),
                        new MatchPhaseTransition(COUNTER_ATTACK, true , 0, 0, 0),
                }
        );
        int index = 6;

        MatchPhaseTransition sequence = matchEngine.getNextSequenceElement(matchSequence, index);

        assertEquals(TRANSITION, sequence.outcomeType());
        assertTrue(sequence.retainPossession());
        assertEquals(1, sequence.duration());
    }
}
