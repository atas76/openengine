package org.openengine.pureengine.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TournamentParticipationRepository {

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/tournament_participation.csv";

    private static Map<Integer, List<String>> tournamentParticipationMap;

    static {
        loadData();
    }

    public static List<String> getTeamNames(int tournamentId) {
        return tournamentParticipationMap.get(tournamentId);
    }

    public static void loadData() {
        tournamentParticipationMap = new HashMap<>();
        try (var records = Files.lines(Paths.get(datasource))) {
            records.forEach(record -> {
                try {
                    if (!record.startsWith("#")) {
                        String [] csvRecord = CommonUtil.parseCsv(record);

                        String teamName = csvRecord[0];
                        Integer tournamentId = Integer.valueOf(csvRecord[1]);

                        tournamentParticipationMap.putIfAbsent(tournamentId, new ArrayList<>());
                        tournamentParticipationMap.get(tournamentId).add(teamName);
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
