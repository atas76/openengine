package org.openengine.pureengine.domain;

import org.openengine.pureengine.TieBreaker;
import org.openengine.pureengine.tournament.CompetitionRound;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CompetitionRepository {

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/competition.csv";

    private static Map<Integer, CompetitionDTO> competitions;
    private static CompetitionDTO competitionDto_FA_Cup;

    static {
        loadData();
        competitionDto_FA_Cup = competitions.get(1);
    }

    public static void loadData() {
        competitions = new HashMap<>();
        try (var records = Files.lines(Paths.get(datasource))) {
            records.forEach(record -> {
                try {
                    if (!record.startsWith("#")) {
                        String [] csvRecord = CommonUtil.parseCsv(record);
                        competitions.put(Integer.parseInt(csvRecord[0]),
                                new CompetitionDTO(csvRecord[1], csvRecord[2], csvRecord[3]));
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

    public static final Competition ENGLISH_FA_CUP = // Modelling only later stages
            new Competition(
                    competitionDto_FA_Cup.getCountryDemonym() + " " + competitionDto_FA_Cup.getName(),
                    List.of(
                        new CompetitionRound("5th round", true, TieBreaker.EXTRA_TIME_PENALTIES),
                        new CompetitionRound("Quarter final", true, TieBreaker.EXTRA_TIME_PENALTIES),
                        new CompetitionRound("Semi final", false, TieBreaker.EXTRA_TIME_PENALTIES),
                        new CompetitionRound("Final", false, TieBreaker.EXTRA_TIME_PENALTIES)));
}
