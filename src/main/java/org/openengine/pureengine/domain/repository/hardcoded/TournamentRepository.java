package org.openengine.pureengine.domain.repository.hardcoded;

import org.openengine.pureengine.Team;
import org.openengine.pureengine.domain.*;
import org.openengine.pureengine.domain.dto.TournamentDTO;
import org.openengine.pureengine.domain.model.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TournamentRepository {

    public static final int FA_CUP_2024_TOURNAMENT_ID = 1;
    public static final int FA_CUP_2023_TOURNAMENT_ID = 2;

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/tournament.csv";

    private static Map<Integer, TournamentDTO> tournaments;
    private static List<Team> participantsFACup2024 = TournamentParticipationRepository
            .getTeamNames(FA_CUP_2024_TOURNAMENT_ID)
            .stream().map(TeamRepository::getTeam).toList();

    private static List<Team> participantsFACup2023 = TournamentParticipationRepository
            .getTeamNames(FA_CUP_2023_TOURNAMENT_ID)
            .stream().map(TeamRepository::getTeam).toList();
    public static Tournament ENGLISH_FA_CUP_2024;
    public static Tournament ENGLISH_FA_CUP_2023;

    static {
        loadData();
        TournamentDTO faCup2023DTO = tournaments.get(FA_CUP_2023_TOURNAMENT_ID);
        TournamentDTO faCup2024DTO = tournaments.get(FA_CUP_2024_TOURNAMENT_ID);
        ENGLISH_FA_CUP_2023 = new Tournament(faCup2023DTO.getYear(), participantsFACup2023,
               CompetitionRepository.getCompetition(faCup2023DTO.getCompetitionId()));
        ENGLISH_FA_CUP_2024 = new Tournament(faCup2024DTO.getYear(), participantsFACup2024,
                CompetitionRepository.getCompetition(faCup2024DTO.getCompetitionId()));
    }

    public static void loadData() {
        tournaments = new HashMap<>();
        try (var records = Files.lines(Paths.get(datasource))) {
            records.forEach(record -> {
                try {
                    if (!record.startsWith("#")) {
                        String [] csvRecord = CommonUtil.parseCsv(record);
                        Integer tournamentId = Integer.valueOf(csvRecord[0]);
                        Integer competitionId = Integer.valueOf(csvRecord[1]);
                        Integer year = Integer.valueOf(csvRecord[2]);
                        tournaments.put(tournamentId, new TournamentDTO(competitionId, year));
                    }
                } catch (Exception e) {
                    System.out.println(record);
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
