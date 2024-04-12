package org.openengine.pureengine.domain.repository;

import org.openengine.pureengine.domain.CommonUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TournamentParticipationRepositoryFileImpl implements Repository<String> {

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/tournament_participation.csv";

    private static Map<Integer, List<String>> tournamentParticipationMap;

    public TournamentParticipationRepositoryFileImpl() {
        loadData();
    }

    @Override
    public String findById(int id) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Collection<String> findByReferenceId(int id) {
        return tournamentParticipationMap.get(id);
    }

    @Override
    public String findByName(String name) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Collection<String> findAll() {
        throw new IllegalArgumentException("Not supported");
    }

    @Override
    public void loadData() {
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
