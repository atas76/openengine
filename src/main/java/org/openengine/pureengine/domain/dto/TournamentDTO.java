package org.openengine.pureengine.domain.dto;

public class TournamentDTO {

    private Integer competitionId;
    private Integer year;

    public TournamentDTO(Integer competitionId, Integer year) {
        this.competitionId = competitionId;
        this.year = year;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public Integer getYear() {
        return year;
    }
}
