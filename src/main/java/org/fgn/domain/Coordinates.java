package org.fgn.domain;

import java.util.Objects;

public class Coordinates {

    private X x;
    private Y y = Y.getDefault();
    private Context.Coordinate context;
    private Context.GoalAngle goalAngle = Context.GoalAngle.getDefault();

    public Coordinates(X x) {
        this.x = x;
    }

    public Coordinates(X x, Y y, Context.Coordinate context, Context.GoalAngle goalAngle) {
        this.x = x;
        this.y = y;
        this.context = context;
        this.goalAngle = goalAngle;
    }

    public Coordinates(Context.Coordinate context) {
        this.context = context;
    }

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }

    public Context.Coordinate getContext() {
        return context;
    }

    public void setContext(Context.Coordinate context) {
        this.context = context;
    }

    public Context.GoalAngle getGoalAngle() {
        return goalAngle;
    }

    public void setGoalAngle(Context.GoalAngle goalAngle) {
        this.goalAngle = goalAngle;
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

    public enum X {
        D, DM, M, A
    }

    public enum Y {
        C, W;

        public static Y getDefault() {
            return C;
        }
    }
}
