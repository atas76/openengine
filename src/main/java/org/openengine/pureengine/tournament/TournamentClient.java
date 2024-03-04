package org.openengine.pureengine.tournament;

public class TournamentClient {
    public static void main(String[] args) {
        Tournament tournament = TournamentRepository.ENGLISH_FA_CUP_2024;

        tournament.displayHeader();
        System.out.println();
        tournament.displayParticipants();

        System.out.println();
        tournament.play();
    }
}
