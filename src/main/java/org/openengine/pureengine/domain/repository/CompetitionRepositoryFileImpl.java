package org.openengine.pureengine.domain.repository;

import org.openengine.pureengine.TieBreaker;
import org.openengine.pureengine.domain.CommonUtil;
import org.openengine.pureengine.domain.dto.CompetitionDTO;
import org.openengine.pureengine.domain.dto.CompetitionRoundDTO;
import org.openengine.pureengine.domain.model.Competition;
import org.openengine.pureengine.domain.model.CompetitionRound;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetitionRepositoryFileImpl implements Repository<Competition> {

    private static Map<Integer, CompetitionDTO> competitions;
    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/competition.csv";

    public CompetitionRepositoryFileImpl() {
        loadData();
    }

    @Override
    public Competition findById(int id) {
        CompetitionDTO competitionDTO = competitions.get(id);
        List<CompetitionRoundDTO> competitionRoundDTOs = CompetitionRoundRepository.getCompetitionRounds(id);
        return new Competition(
                competitionDTO.getCountryDemonym() + " " + competitionDTO.getName(),
                competitionRoundDTOs.stream().map(competitionRoundDTO ->
                        new CompetitionRound(competitionRoundDTO.getName(),
                                competitionRoundDTO.getHomeAdvantage(),
                                TieBreaker.valueOf(competitionRoundDTO.getTieBreaker()))).toList());
    }

    @Override
    public void loadData() {
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
