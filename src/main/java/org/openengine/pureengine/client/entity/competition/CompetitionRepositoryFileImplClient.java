package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.domain.model.Competition;
import org.openengine.pureengine.domain.repository.CompetitionRepository;
import org.openengine.pureengine.domain.repository.CompetitionRepositoryFileImpl;
import org.openengine.pureengine.domain.repository.Repository;

public class CompetitionRepositoryFileImplClient {

    public static void main(String[] args) {
        Repository<Competition> competitionRepository = new CompetitionRepositoryFileImpl();
        Competition competition = competitionRepository.findById(CompetitionRepository.FA_CUP_COMPETITION_ID);

        competition.displayName();
        System.out.println();
        competition.displayStageNames();
    }
}
