package org.fgn.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class State {

    protected BallPlay ballPlay = BallPlay.getDefault();
    protected PlayerPosition playerPosition = PlayerPosition.getDefault();
    protected List<PlayingCondition> playingConditions = new ArrayList<>();
    protected Coordinates coordinates;

    public BallPlay getBallPlay() {
        return ballPlay;
    }

    public void setBallPlay(BallPlay ballPlay) {
        this.ballPlay = ballPlay;
    }

    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(PlayerPosition playerPosition) {
        this.playerPosition = playerPosition;
    }

    public List<PlayingCondition> getPlayingConditions() {
        return playingConditions;
    }

    public void setPlayingConditions(List<PlayingCondition> playingConditions) {
        this.playingConditions = playingConditions;
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
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return ballPlay == state.ballPlay &&
                playerPosition == state.playerPosition &&
                Objects.equals(playingConditions, state.playingConditions) &&
                Objects.equals(coordinates, state.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ballPlay, playerPosition, playingConditions, coordinates);
    }
}
