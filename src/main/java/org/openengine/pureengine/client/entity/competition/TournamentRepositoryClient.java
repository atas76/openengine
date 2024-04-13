package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.domain.model.Tournament;
import org.openengine.pureengine.domain.repository.hardcoded.TournamentRepository;

import java.util.List;

public class TournamentRepositoryClient {
    public static void main(String[] args) {

        List<Tournament> tournamentHistory = List.of(
                TournamentRepository.ENGLISH_FA_CUP_2023,
                TournamentRepository.ENGLISH_FA_CUP_2024);

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
