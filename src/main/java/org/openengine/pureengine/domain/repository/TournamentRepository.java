package org.openengine.pureengine.domain.repository;

import org.openengine.pureengine.Team;
import org.openengine.pureengine.domain.*;
import org.openengine.pureengine.domain.model.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TournamentRepository {

    private static final int FA_CUP_2024_TOURNAMENT_ID = 1;
    private static final int FA_CUP_2023_TOURNAMENT_ID = 2;

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/tournament.csv";

    private static Map<Integer, Integer> tournaments;
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
        ENGLISH_FA_CUP_2023 = new Tournament(tournaments.get(FA_CUP_2023_TOURNAMENT_ID), participantsFACup2023,
                CompetitionRepository.ENGLISH_FA_CUP);
        ENGLISH_FA_CUP_2024 = new Tournament(tournaments.get(FA_CUP_2024_TOURNAMENT_ID), participantsFACup2024,
                        CompetitionRepository.ENGLISH_FA_CUP);
    }

    public static void loadData() {
        tournaments = new HashMap<>();
        try (var records = Files.lines(Paths.get(datasource))) {
            records.forEach(record -> {
                try {
                    if (!record.startsWith("#")) {
                        String [] csvRecord = CommonUtil.parseCsv(record);
                        Integer tournamentId = Integer.valueOf(csvRecord[0]);
                        Integer year = Integer.valueOf(csvRecord[2]);
                        tournaments.put(tournamentId, year);
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
