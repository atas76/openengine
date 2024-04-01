package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.domain.model.Competition;
import org.openengine.pureengine.domain.repository.CompetitionRepository;

public class CompetitionRepositoryClient {

    public static void main(String[] args) {
        Competition competition = CompetitionRepository.ENGLISH_FA_CUP;

        competition.displayName();
        System.out.println();
        competition.displayStageNames();
    }
}
