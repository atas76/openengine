package org.openengine.pureengine.client;

import org.openengine.pureengine.tournament.TournamentAggregator;
import org.openengine.pureengine.domain.repository.TournamentRepository;

public class TournamentAggregatorClient {

    public static void main(String[] args) {
        System.out.println("English FA Cup 2022-23");
        System.out.println();
        TournamentAggregator tournamentAggregator23 = new TournamentAggregator(TournamentRepository.ENGLISH_FA_CUP_2023);
        tournamentAggregator23.execute();
        tournamentAggregator23.displayResults();

        System.out.println();

        System.out.println("English FA Cup 2023-24");
        System.out.println();
        TournamentAggregator tournamentAggregator24 = new TournamentAggregator(TournamentRepository.ENGLISH_FA_CUP_2024);
        tournamentAggregator24.execute();
        tournamentAggregator24.displayResults();
    }
}
