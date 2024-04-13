package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.domain.model.Tournament;
import org.openengine.pureengine.domain.repository.file.TournamentRepositoryFileImpl;

import java.util.List;

import static org.openengine.pureengine.domain.repository.TournamentRepository.FA_CUP_2023_TOURNAMENT_ID;
import static org.openengine.pureengine.domain.repository.TournamentRepository.FA_CUP_2024_TOURNAMENT_ID;

public class TournamentRepositoryFileImplClient {

    public static void main(String[] args) {

        TournamentRepositoryFileImpl tournamentRepositoryFileImpl = new TournamentRepositoryFileImpl();

        List<Tournament> tournamentHistory = List.of(
                tournamentRepositoryFileImpl.findById(FA_CUP_2023_TOURNAMENT_ID),
                tournamentRepositoryFileImpl.findById(FA_CUP_2024_TOURNAMENT_ID));

        tournamentHistory.forEach(tournament -> {
            tournament.displayHeader();
            System.out.println();
            tournament.displayParticipants();

            System.out.println();
            tournament.play();
            System.out.println();
        });
    }
}
