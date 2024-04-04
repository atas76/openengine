package org.openengine.pureengine.domain.repository;

import org.openengine.pureengine.client.entity.competition.TournamentParticipationFileImplRepository;
import org.openengine.pureengine.domain.CommonUtil;
import org.openengine.pureengine.domain.model.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TournamentRepositoryFileImpl implements Repository<Tournament> {

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/tournament.csv";

    private static Map<Integer, Tournament> tournaments;

    public TournamentRepositoryFileImpl() {
        loadData();
    }

    @Override
    public Tournament findById(int id) {
        return tournaments.get(id);
    }

    @Override
    public Collection<Tournament> findByReferenceId(int id) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Tournament findByName(String name) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Collection<Tournament> findAll() {
        return tournaments.values();
    }

    @Override
    public void loadData() {
        tournaments = new HashMap<>();
        try (var records = Files.lines(Paths.get(datasource))) {
            records.forEach(record -> {
                try {
                    if (!record.startsWith("#")) {
                        String [] csvRecord = CommonUtil.parseCsv(record);
                        int tournamentId = Integer.parseInt(csvRecord[0]);
                        int competitionId = Integer.parseInt(csvRecord[1]);
                        int year = Integer.parseInt(csvRecord[2]);
                        tournaments.put(tournamentId, new Tournament(year,
                                new TournamentParticipationFileImplRepository().findByReferenceId(tournamentId).stream()
                                        .map(teamName -> new TeamRepositoryFileImpl().findByName(teamName)).toList(),
                                new CompetitionRepositoryFileImpl().findById(competitionId)));
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
