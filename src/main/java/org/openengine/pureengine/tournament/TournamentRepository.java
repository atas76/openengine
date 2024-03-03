package org.openengine.pureengine.tournament;

import org.openengine.pureengine.domain.TeamRepository;

import java.util.List;

public class TournamentRepository {
    public static final Tournament ENGLISH_FA_CUP_2024 = new Tournament(2024, List.of(
            TeamRepository.getTeam("Coventry"),
            TeamRepository.getTeam("Maidstone"),
            TeamRepository.getTeam("Bournemouth"),
            TeamRepository.getTeam("Leicester"),
            TeamRepository.getTeam("Blackburn"),
            TeamRepository.getTeam("Newcastle"),
            TeamRepository.getTeam("Luton"),
            TeamRepository.getTeam("Manchester City"),
            TeamRepository.getTeam("Chelsea"),
            TeamRepository.getTeam("Leeds"),
            TeamRepository.getTeam("Nottingham Forest"),
            TeamRepository.getTeam("Manchester United"),
            TeamRepository.getTeam("Wolves"),
            TeamRepository.getTeam("Brighton"),
            TeamRepository.getTeam("Liverpool"),
            TeamRepository.getTeam("Southampton")
    ), CompetitionRepository.ENGLISH_FA_CUP);
}
