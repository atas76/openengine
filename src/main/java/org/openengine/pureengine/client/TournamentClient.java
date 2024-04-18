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
            TournamentReplayHistoryDbImpl tournamentReplayHistoryRepository =
                    context.getBean(TournamentReplayHistoryDbImpl.class);
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

                // Update winner tournament replay history
                TournamentReplayHistory tournamentReplayWinnerHistory =
                        tournamentReplayHistoryRepository.findByTournamentAndTeam(tournament.getId(),
                                tournamentHistoryRecord.getWinner());
                if (tournamentReplayWinnerHistory == null) {
                    tournamentReplayHistoryRepository.save(new TournamentReplayHistory(tournament.getId(),
                            tournamentHistoryRecord.getWinner(),
                                    1, 1, 2));
                } else {
                    tournamentReplayHistoryRepository.update(tournament.getId(), tournamentHistoryRecord.getWinner(),
                            tournamentReplayWinnerHistory.getWinsNum() + 1,
                            tournamentReplayWinnerHistory.getFinalsParticipationsNum() + 1,
                            tournamentReplayWinnerHistory.getHistorialCoefficient() + 2);
                }

                // Update runner-up tournament replay history
                TournamentReplayHistory tournamentReplayRunnerUpHistory =
                        tournamentReplayHistoryRepository.findByTournamentAndTeam(tournament.getId(),
                                tournamentHistoryRecord.getRunnerUp());
                if (tournamentReplayRunnerUpHistory == null) {
                    tournamentReplayHistoryRepository.save(
                            new TournamentReplayHistory(tournament.getId(), tournamentHistoryRecord.getRunnerUp(),
                                    0, 1, 1));
                } else {
                    tournamentReplayHistoryRepository.update(tournament.getId(), tournamentHistoryRecord.getRunnerUp(),
                            tournamentReplayRunnerUpHistory.getWinsNum(),
                            tournamentReplayRunnerUpHistory.getFinalsParticipationsNum() + 1,
                            tournamentReplayRunnerUpHistory.getHistorialCoefficient() + 1);
                }
                System.out.println();
            });
        }
    }
}
