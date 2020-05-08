package org.fgn.domain;

import java.util.Objects;

public class Coordinates {

    private X x;
    private Y y = Y.getDefault();
    private Context.Coordinate context;

    public Coordinates(Context.Coordinate context) {
        this.context = context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y &&
                context == that.context;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, context);
    }

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
