package org.openengine.pureengine.domain.repository.file;

import org.openengine.pureengine.domain.CommonUtil;
import org.openengine.pureengine.domain.dto.CompetitionDTO;
import org.openengine.pureengine.domain.model.Competition;
import org.openengine.pureengine.domain.model.CompetitionRound;
import org.openengine.pureengine.domain.repository.IdentifiableRepository;
import org.openengine.pureengine.domain.repository.LoadableRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CompetitionRepositoryFileImpl
        implements LoadableRepository<Competition>, IdentifiableRepository<Competition> {

    private static Map<Integer, CompetitionDTO> competitions;
    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/competition.csv";

    public CompetitionRepositoryFileImpl() {
        loadData();
    }

    @Override
    public Collection<Competition> findAll() {
        return competitions.keySet().stream().map(this::findById).toList();
    }

    @Override
    public Competition findById(int id) {
        CompetitionDTO competitionDTO = competitions.get(id);
        Collection<CompetitionRound> competitionRounds = new CompetitionRoundRepositoryFileImpl().findByReferenceId(id);
        return new Competition(competitionDTO.getCountryDemonym() + " " + competitionDTO.getName(),
                competitionRounds);
    }

    @Override
    public Competition findByName(String name) {
        throw new IllegalArgumentException("Not supported");
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
