package org.openengine.pureengine.tournament;

public class CompetitionClient {

    public static void main(String[] args) {
        Competition competition = CompetitionRepository.ENGLISH_FA_CUP;

        competition.displayName();
        System.out.println();
        competition.displayStageNames();
    }
}
