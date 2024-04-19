package org.openengine.pureengine.client;

import org.openengine.pureengine.config.SQLiteConfig;
import org.openengine.pureengine.domain.model.Tournament;
import org.openengine.pureengine.domain.model.TournamentHistory;
import org.openengine.pureengine.domain.repository.RetrievableRepository;
import org.openengine.pureengine.domain.repository.db.TournamentRepositoryDbImpl;
import org.openengine.pureengine.tournament.TournamentAggregator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class TournamentAggregatorClient {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SQLiteConfig.class)) {
            RetrievableRepository<Tournament> tournamentRepository = context.getBean(TournamentRepositoryDbImpl.class);
            Collection<Tournament> tournaments = tournamentRepository.findAll();
            List<Tournament> competitionHistory = new ArrayList<>(tournaments);
            competitionHistory.sort(Comparator.comparingInt(Tournament::getId).reversed());
            competitionHistory.forEach(tournament -> {
                System.out.println(tournament);
                TournamentAggregator tournamentAggregator = new TournamentAggregator(tournament);
                List<TournamentHistory> tournamentResults = tournamentAggregator.execute(10);
                tournamentResults.forEach(System.out::println);
                System.out.println();
                tournamentAggregator.displayResults();
                System.out.println();
            });
        }
    }
}
