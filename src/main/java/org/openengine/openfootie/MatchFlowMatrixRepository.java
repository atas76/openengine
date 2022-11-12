package org.openengine.openfootie;

import static org.openengine.openfootie.MatchPhase.POSSESSION;
import static org.openengine.openfootie.SetPiece.*;

public class MatchFlowMatrixRepository {

    public static final MatchFlowMatrix L_CLF19 = new MatchFlowMatrix();

    public static void load() {
        L_CLF19.addRow(KICK_OFF,
                new MatchSequence(new MatchDataElement[] {
                        new MatchDataElement(POSSESSION, true, 2, 0, 0)
                }));
    }
}
