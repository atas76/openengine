package org.openengine.pureengine.domain.repository.file;

import org.openengine.pureengine.TieBreaker;
import org.openengine.pureengine.domain.CommonUtil;
import org.openengine.pureengine.domain.dto.CompetitionRoundDTO;
import org.openengine.pureengine.domain.model.CompetitionRound;
import org.openengine.pureengine.domain.repository.LoadableRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CompetitionRoundRepositoryFileImpl implements LoadableRepository<CompetitionRound> {

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/competition_round.csv";

    private static Map<Integer, List<CompetitionRoundDTO>> competitionRounds;

    public CompetitionRoundRepositoryFileImpl() {
        loadData();
    }

    @Override
    public CompetitionRound findById(int id) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Collection<CompetitionRound> findByReferenceId(int id) {
        return competitionRounds.get(id).stream().map(dto -> new CompetitionRound(dto.getName(),
                dto.getHomeAdvantage(),
                TieBreaker.valueOf(dto.getTieBreaker()))).toList();
    }

    @Override
    public CompetitionRound findByName(String name) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Collection<CompetitionRound> findAll() {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public void loadData() {
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
