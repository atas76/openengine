package org.openengine.pureengine.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetitionRoundRepository {

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/competition_round.csv";

    private static Map<Integer, List<CompetitionRoundDTO>> competitionRounds;

    static {
        loadData();
    }

    public static List<CompetitionRoundDTO> getCompetitionRounds(int competitionId) {
        return competitionRounds.get(competitionId);
    }

    public static void loadData() {
        competitionRounds = new HashMap<>();
        try (var records = Files.lines(Paths.get(datasource))) {
            records.forEach(record -> {
                try {
                    if (!record.startsWith("#")) {
                        String [] csvRecord = CommonUtil.parseCsv(record);

                        String name = csvRecord[0];
                        Boolean homeAdvantage = Boolean.valueOf(csvRecord[1]);
                        String tieBreaker = csvRecord[2];
                        Integer competitionId = Integer.valueOf(csvRecord[3]);

                        competitionRounds.putIfAbsent(competitionId, new ArrayList<>());
                        competitionRounds.get(competitionId)
                                .add(new CompetitionRoundDTO(name, homeAdvantage, tieBreaker));
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
