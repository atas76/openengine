package org.fgn.domain;

import java.util.List;

public class State {

    private BallPlay ballPlay = BallPlay.getDefault();
    private PlayerPosition playerPosition = PlayerPosition.getDefault();
    private List<PlayingCondition> playingConditions;
}
