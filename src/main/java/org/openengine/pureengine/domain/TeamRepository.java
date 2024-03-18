package org.openengine.pureengine.domain;

import org.openengine.pureengine.Team;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TeamRepository {

    private static final String datasource = CommonUtil.DOMAIN_ROOT + "/team.csv";

    private static Map<String, Team> teams;

    static {
        loadData();
    }

    public static void loadData() {
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

    public static Set<String> getEntries() {
        Set<String> entries = new HashSet<>();
        teams.forEach((k, v) -> entries.add(k + ": " + v.toString()));
        return entries;
    }

    public static Team getTeam(String name) {
        return teams.get(name);
    }
}
