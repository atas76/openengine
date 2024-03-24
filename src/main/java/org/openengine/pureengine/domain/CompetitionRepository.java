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

    public static final Competition ENGLISH_FA_CUP;

    static {
        loadData();
        competitionDtoFACup = competitions.get(FA_CUP_COMPETITION_ID);
        competitionRoundsDtoFACup = CompetitionRoundRepository.getCompetitionRounds(FA_CUP_COMPETITION_ID);
        ENGLISH_FA_CUP = new Competition(
                competitionDtoFACup.getCountryDemonym() + " " + competitionDtoFACup.getName(),
                competitionRoundsDtoFACup.stream().map(competitionRoundDTO ->
                        new CompetitionRound(competitionRoundDTO.getName(),
                                competitionRoundDTO.getHomeAdvantage(),
                                TieBreaker.valueOf(competitionRoundDTO.getTieBreaker()))).toList());
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
}
