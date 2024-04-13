package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.domain.model.Competition;
import org.openengine.pureengine.domain.repository.IdentifiableRepository;
import org.openengine.pureengine.domain.repository.hardcoded.CompetitionRepository;
import org.openengine.pureengine.domain.repository.file.CompetitionRepositoryFileImpl;

public class CompetitionRepositoryFileImplClient {

    public static void main(String[] args) {
        IdentifiableRepository<Competition> competitionRepository = new CompetitionRepositoryFileImpl();
        Competition competition = competitionRepository.findById(CompetitionRepository.FA_CUP_COMPETITION_ID);

        competition.displayName();
        System.out.println();
        competition.displayStageNames();
    }
}
