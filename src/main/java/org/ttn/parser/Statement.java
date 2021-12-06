package org.ttn.parser;

import org.ttn.engine.input.TacticalPosition;
import org.ttn.engine.space.PitchPosition;

public class Statement {

    private int time;
    private TacticalPosition.X tacticalPositionX;
    private TacticalPosition.Y tacticalPositionY;
    private PitchPosition pitchPosition;

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public TacticalPosition.X getTacticalPositionX() {
        return tacticalPositionX;
    }

    public void setTacticalPositionX(TacticalPosition.X tacticalPositionX) {
        this.tacticalPositionX = tacticalPositionX;
    }

    public TacticalPosition.Y getTacticalPositionY() {
        return tacticalPositionY;
    }

    public void setTacticalPositionY(TacticalPosition.Y tacticalPositionY) {
        this.tacticalPositionY = tacticalPositionY;
    }

    public PitchPosition getPitchPosition() {
        return pitchPosition;
    }

    public void setPitchPosition(PitchPosition pitchPosition) {
        this.pitchPosition = pitchPosition;
    }
}
