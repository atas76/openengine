package org.openengine.prototype.engine;

import org.openengine.prototype.domain.Team;

public class State {

    private Coordinates ball;
    private Team possession;

    State(Coordinates ball, Team possession) {
        this.ball = ball;
        this.possession = possession;
    }

    Coordinates getBall() {
        return this.ball;
    }

    public Team getPossession() {
        return this.possession;
    }
}
