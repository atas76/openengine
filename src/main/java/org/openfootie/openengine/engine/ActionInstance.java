package org.openfootie.openengine.engine;

import java.util.Objects;

public class ActionInstance {

    private Action action;
    private Coordinates coordinates;

    public ActionInstance(Action action, Coordinates coordinates) {
        this.action = action;
        this.coordinates = coordinates;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionInstance that = (ActionInstance) o;
        return action == that.action &&
                coordinates == that.coordinates;
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, coordinates);
    }
}
