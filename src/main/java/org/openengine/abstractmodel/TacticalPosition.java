package org.openengine.abstractmodel;

public enum TacticalPosition {
    D_R(0, 0), D_RC(0, 1), D_CR(0, 2), D_C(0, 3), D_CL(0, 4), D_LC(0, 5), D_L(0, 6),
    DM_R(1, 0), DM_RC(1, 1), DM_CR(1, 2), DM_C(1, 3), DM_CL(1, 4), DM_LC(1, 5), DM_L(1, 6),
    M_R(2, 0), M_RC(2, 1), M_CR(2, 2), M_C(2, 3), M_CL(2, 4), M_LC(2, 5), M_L(2, 6),
    AM_R(3, 0), AM_RC(3, 1), AM_CR(3, 2), AM_C(3, 3), AM_CL(3, 4), AM_LC(3, 5), AM_L(3, 6),
    F_R(4, 0), F_RC(4, 1), F_CR(4, 2), F_C(4, 3), F_CL(4, 4), F_LC(4, 5), F_L(4, 6);

    private int x;
    private int y;

    TacticalPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
