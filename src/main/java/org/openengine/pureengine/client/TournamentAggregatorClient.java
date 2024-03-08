package org.openengine.pureengine.client;

import org.openengine.pureengine.tournament.TournamentAggregator;
import org.openengine.pureengine.tournament.TournamentRepository;

public class TournamentAggregatorClient {

    public static void main(String[] args) {
        TournamentAggregator tournamentAggregator = new TournamentAggregator(TournamentRepository.ENGLISH_FA_CUP_2024);
        tournamentAggregator.execute();
        tournamentAggregator.displayResults();
    }
}
