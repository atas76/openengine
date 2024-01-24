package org.openengine.abstractmodel;

import static org.openengine.abstractmodel.TacticalPosition.*;

public class TacticalPitch {
    private TacticalPosition[][] tacticalPitchLayout = {
            { D_R, D_RC, D_CR, D_C, D_CL, D_LC, D_L },
            { DM_R, DM_RC, DM_CR, D_C, DM_CL, DM_LC, DM_L },
            { M_R, M_RC, M_CR, M_C, M_CL, M_LC, M_L },
            { AM_R, AM_RC, AM_CR, AM_C, AM_CL, AM_LC, AM_L },
            { F_R, F_RC, F_CR, F_C, F_CL, F_LC, F_L }
    };
}