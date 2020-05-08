package org.fgn.domain;

public class Coordinate {

    private X x;
    private Y y = Y.getDefault();
    private Context.Coordinate context;

    private enum X {
        D, DM, M, A
    }

    private enum Y {
        C, W;

        public static Y getDefault() {
            return C;
        }
    }
}
