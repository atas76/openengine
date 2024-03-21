package org.openengine.pureengine.domain;

import org.openengine.pureengine.TieBreaker;
import org.openengine.pureengine.tournament.CompetitionRound;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CompetitionRepository {

    private static final int FA_CUP_COMPETITION_ID = 1;

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/competition.csv";

    private static Map<Integer, CompetitionDTO> competitions;
    private static CompetitionDTO competitionDtoFACup;
    private static List<CompetitionRoundDTO> competitionRoundsDtoFACup;

    static {
        loadData();
        competitionDtoFACup = competitions.get(FA_CUP_COMPETITION_ID);
        competitionRoundsDtoFACup = CompetitionRoundRepository.getCompetitionRounds(FA_CUP_COMPETITION_ID);
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
                    competitionDtoFACup.getCountryDemonym() + " " + competitionDtoFACup.getName(),
                    List.of(
                        new CompetitionRound(
                                competitionRoundsDtoFACup.get(0).getName(),
                                competitionRoundsDtoFACup.get(0).getHomeAdvantage(),
                                TieBreaker.valueOf(competitionRoundsDtoFACup.get(0).getTieBreaker())),
                        new CompetitionRound(
                                competitionRoundsDtoFACup.get(1).getName(),
                                competitionRoundsDtoFACup.get(1).getHomeAdvantage(),
                                TieBreaker.valueOf(competitionRoundsDtoFACup.get(1).getTieBreaker())),
                        new CompetitionRound(
                                competitionRoundsDtoFACup.get(2).getName(),
                                competitionRoundsDtoFACup.get(2).getHomeAdvantage(),
                                TieBreaker.valueOf(competitionRoundsDtoFACup.get(2).getTieBreaker())),
                        new CompetitionRound(
                                competitionRoundsDtoFACup.get(3).getName(),
                                competitionRoundsDtoFACup.get(3).getHomeAdvantage(),
                                TieBreaker.valueOf(competitionRoundsDtoFACup.get(3).getTieBreaker()))));
}
