package org.openfootie.openengine.util.analysis;

public class ParticipationUnit {

    private String team;
    private String position;
    private int shirtNo;
    private int minutesPlayed;

    public ParticipationUnit(String team, String position, int shirtNo, int minutesPlayed) {
        this.team = team;
        this.position = position;
        this.shirtNo = shirtNo;
        this.minutesPlayed = minutesPlayed;
    }

    public String getTeam() {
        return team;
    }

    public String getPosition() {
        return position;
    }

    public int getShirtNo() {
        return shirtNo;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }
}
