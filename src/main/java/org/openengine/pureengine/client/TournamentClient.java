package org.openengine.pureengine.client;

import org.openengine.pureengine.config.SQLiteConfig;
import org.openengine.pureengine.domain.model.Tournament;
import org.openengine.pureengine.domain.model.TournamentHistory;
import org.openengine.pureengine.domain.repository.RetrievableRepository;
import org.openengine.pureengine.domain.repository.WriteableRepository;
import org.openengine.pureengine.domain.repository.db.TournamentHistoryRepositoryDbImpl;
import org.openengine.pureengine.domain.repository.db.TournamentRepositoryDbImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class TournamentClient {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SQLiteConfig.class)) {
            RetrievableRepository<Tournament> tournamentRepository = context.getBean(TournamentRepositoryDbImpl.class);
            WriteableRepository<TournamentHistory> tournamentHistoryRepository =
                    context.getBean(TournamentHistoryRepositoryDbImpl.class);
            Collection<Tournament> tournaments = tournamentRepository.findAll();
            List<Tournament> competitionHistory = new ArrayList<>(tournaments);
            competitionHistory.sort(Comparator.comparingInt(Tournament::getId).reversed());

            competitionHistory.forEach(tournament -> {
                tournament.displayHeader();
                System.out.println();
                tournament.displayParticipants();

                System.out.println();
                TournamentHistory tournamentHistoryRecord = tournament.play();
                tournamentHistoryRepository.save(tournamentHistoryRecord);
                System.out.println();
            });
        }
    }
}
