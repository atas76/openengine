package org.openengine.openfootie;

import java.util.Random;

public class MatchEngine {

    private static Random rnd = new Random();

    public MatchDataElement getNextSequenceElement(MatchSequence sequence) {
        return sequence.matchDataElements()[rnd.nextInt(sequence.matchDataElements().length)];
    }

    /**
     *
     * To be used only for testing or for when we would like a definite outcome for some reason
     *
     * @param sequence
     * @param sequenceIndex
     * @return
     */
    public MatchDataElement getNextSequenceElement(MatchSequence sequence, int sequenceIndex) {
        return sequence.matchDataElements()[sequenceIndex];
    }
}
