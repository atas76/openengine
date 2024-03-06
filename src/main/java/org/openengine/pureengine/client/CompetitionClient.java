package org.openengine.pureengine.client;

import org.openengine.pureengine.tournament.Competition;
import org.openengine.pureengine.tournament.CompetitionRepository;

public class CompetitionClient {

    public static void main(String[] args) {
        Competition competition = CompetitionRepository.ENGLISH_FA_CUP;

        competition.displayName();
        System.out.println();
        competition.displayStageNames();
    }
}
