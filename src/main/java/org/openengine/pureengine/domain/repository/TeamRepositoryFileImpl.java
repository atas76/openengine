package org.openengine.pureengine.domain.repository;

import org.openengine.pureengine.Team;
import org.openengine.pureengine.domain.CommonUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TeamRepositoryFileImpl implements Repository<Team> {

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/team.csv";

    private static Map<String, Team> teams;

    public TeamRepositoryFileImpl() {
        loadData();
    }

    @Override
    public Team findById(int id) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Collection<Team> findByReferenceId(int id) {
        throw new IllegalArgumentException("Not applicable");
    }

    @Override
    public Team findByName(String name) {
        return teams.get(name);
    }

    @Override
    public Set<Team> findAll() {
        Set<Team> entries = new HashSet<>();
        teams.forEach((k, v) -> entries.add(new Team(k, v.getFullName(), v.getSkill())));
        return entries;
    }

    @Override
    public void loadData() {
        teams = new HashMap<>();
        try (var records = Files.lines(Paths.get(datasource))) {
            records.forEach(record -> {
                try {
                    if (!record.startsWith("#")) {
                        String [] csvRecord = CommonUtil.parseCsv(record);
                        teams.put(csvRecord[0], new Team(csvRecord[1], Integer.parseInt(csvRecord[2])));
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
