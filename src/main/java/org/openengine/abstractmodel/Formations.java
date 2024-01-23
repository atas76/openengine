package org.openengine.abstractmodel;

import static org.openengine.abstractmodel.TacticalPosition.*;

public class Formations {
    public static TacticalPosition [] formation_4_4_2 = {
            D_R, D_CR, D_CL, D_L,
            M_R, M_CR, M_CL, M_L,
            F_CR, F_CL
    };

    public static TacticalPosition [] formation_4_3_3 = {
            D_R, D_CR, D_CL, D_L,
            M_RC, M_C, M_LC,
            F_RC, F_C, F_LC
    };

    public static TacticalPosition [] formation_4_2_3_1 = {
            D_R, D_CR, D_CL, D_L,
            M_CR, M_CL,
            AM_R, AM_C, AM_L,
            F_C
    };
}
