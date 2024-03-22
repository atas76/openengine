package org.openengine.pureengine.tournament;

import org.openengine.pureengine.Team;
import org.openengine.pureengine.domain.CompetitionRepository;
import org.openengine.pureengine.domain.TeamRepository;
import org.openengine.pureengine.domain.TournamentParticipationRepository;

import java.util.List;

public class TournamentRepository {

    private static final int FA_CUP_2024_TOURNAMENT_ID = 1;
    private static  List<Team> participantsFACup2024 = TournamentParticipationRepository
            .getTeamNames(FA_CUP_2024_TOURNAMENT_ID)
            .stream().map(TeamRepository::getTeam).toList();
    public static final Tournament ENGLISH_FA_CUP_2024 =
            new Tournament(2024, participantsFACup2024, CompetitionRepository.ENGLISH_FA_CUP);
}
