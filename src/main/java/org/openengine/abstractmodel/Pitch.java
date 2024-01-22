package org.openengine.abstractmodel;

import org.mpn.PitchPosition;
import static org.mpn.PitchPosition.*;

public class Pitch {
    private PitchPosition[][] tacticalPitchLayout = {
            { Dw, Dh, D, Dh, D },
            { DMw, DMh, DM, DMh, DMw },
            { Mw, Mh, M, Mh, Mw },
            { AMw, AMh, AM, AMh, AMw },
            { Aw, Ah, A, Ah, Aw }
    };
}
