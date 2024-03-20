package org.openengine.pureengine.client;

import org.openengine.pureengine.domain.Competition;
import org.openengine.pureengine.domain.CompetitionRepository;

public class CompetitionRepositoryClient {

    public static void main(String[] args) {
        Competition competition = CompetitionRepository.ENGLISH_FA_CUP;

        competition.displayName();
        System.out.println();
        competition.displayStageNames();
    }
}
