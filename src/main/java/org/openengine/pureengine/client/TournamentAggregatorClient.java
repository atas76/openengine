package org.openengine.pureengine.client;

import org.openengine.pureengine.config.SQLiteConfig;
import org.openengine.pureengine.domain.model.Tournament;
import org.openengine.pureengine.domain.model.TournamentHistory;
import org.openengine.pureengine.domain.model.TournamentReplayHistory;
import org.openengine.pureengine.domain.repository.RetrievableRepository;
import org.openengine.pureengine.domain.repository.WriteableRepository;
import org.openengine.pureengine.domain.repository.db.TournamentHistoryRepositoryDbImpl;
import org.openengine.pureengine.domain.repository.db.TournamentReplayHistoryDbImpl;
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
            WriteableRepository<TournamentHistory> tournamentHistoryRepository =
                    context.getBean(TournamentHistoryRepositoryDbImpl.class);
            TournamentReplayHistoryDbImpl tournamentReplayHistoryRepository =
                    context.getBean(TournamentReplayHistoryDbImpl.class);
            Collection<Tournament> tournaments = tournamentRepository.findAll();
            List<Tournament> competitionHistory = new ArrayList<>(tournaments);
            competitionHistory.sort(Comparator.comparingInt(Tournament::getId).reversed());
            competitionHistory.forEach(tournament -> {
                System.out.println(tournament);
                TournamentAggregator tournamentAggregator = new TournamentAggregator(tournament);
                List<TournamentHistory> tournamentResults = tournamentAggregator.execute(10);
                tournamentResults.forEach(tournamentResult -> {
                    tournamentHistoryRepository.save(tournamentResult);
                    System.out.println(tournamentResult);
                });
                System.out.println();
                tournamentAggregator.displayResults();
                System.out.println();

                tournamentAggregator.getRankedTeams().forEach(team -> {
                    TournamentReplayHistory tournamentReplayWinnerHistory =
                            tournamentReplayHistoryRepository.findByTournamentAndTeam(tournament.getId(), team);
                    if (tournamentReplayWinnerHistory == null) {
                        tournamentReplayHistoryRepository.save(new TournamentReplayHistory(tournament.getId(), team,
                                tournamentAggregator.getWins(team),
                                tournamentAggregator.getFinalParticipations(team),
                                tournamentAggregator.getHistoricalCoefficient(team)));
                    } else {
                        tournamentReplayHistoryRepository.update(tournament.getId(), team,
                                tournamentReplayWinnerHistory.getWinsNum()
                                        + tournamentAggregator.getWins(team),
                                tournamentReplayWinnerHistory.getFinalsParticipationsNum()
                                        + tournamentAggregator.getFinalParticipations(team),
                                tournamentReplayWinnerHistory.getHistorialCoefficient()
                                        + tournamentAggregator.getHistoricalCoefficient(team));
                    }
                });
            });
        }
    }
}
