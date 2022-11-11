package org.openengine.prototype.engine;

public class ActionOutcomeWeight implements OutcomeWeight {

    protected Coordinates coordinates;
    private SetPiece setPiece;
    protected boolean keepPossession;
    private int weight;

    public Outcome getOutcome() {
        return new ActionOutcome(this.coordinates, this.keepPossession);
    }

    public ActionOutcomeWeight(Coordinates coordinates, boolean keepPossession, int weight) {
        this.coordinates = coordinates;
        this.keepPossession = keepPossession;
        this.weight = weight;
    }

    public ActionOutcomeWeight(SetPiece setPiece, Coordinates coordinates, boolean keepPossession, int weight) {
        this(coordinates, keepPossession, weight);
        this.setPiece = setPiece;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public boolean getKeepPossession() {
        return this.keepPossession;
    }

    public int getWeight() {
        return this.weight;
    }

    public SetPiece getSetPiece()  {
        return this.setPiece;
    }
}
