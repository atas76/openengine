package org.openengine.pureengine.util;

import org.openengine.pureengine.domain.CommonUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SQLQueryGenerator {

    public static void main(String[] args) {
        // teamInitStatements(CommonUtil.DOMAIN_ROOT + "/team.csv").forEach(System.out::println);
        competitionInitStatements(CommonUtil.DOMAIN_ROOT + "/competition.csv").forEach(System.out::println);
    }

    public static List<String> competitionInitStatements(String datasource) {

        List<String> sqlStatements = new ArrayList<>();

        try (var records = Files.lines(Paths.get(datasource))) {
            records.forEach(record -> {
                try {
                    if (!record.startsWith("#")) {
                        String [] csvRecord = CommonUtil.parseCsv(record);
                        String query = "INSERT INTO Competition(id, name, country, country_demonym) " +
                                "VALUES("
                                + csvRecord[0] +
                                "," +
                                "'" + csvRecord[1] + "'" +
                                "," +
                                "'" + csvRecord[2] + "'" +
                                "," +
                                "'" + csvRecord[3] + "'" +
                                ");";
                        sqlStatements.add(query);
                    }
                } catch (Exception e) {
                    System.out.println(record);
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sqlStatements;
    }

    public static List<String> teamInitStatements(String datasource) {

        List<String> sqlStatements = new ArrayList<>();

        try (var records = Files.lines(Paths.get(datasource))) {
            records.forEach(record -> {
                try {
                    if (!record.startsWith("#")) {
                        String [] csvRecord = CommonUtil.parseCsv(record);
                        String query = "INSERT INTO Team(team_name, full_name, skill) " +
                                "VALUES(" +
                                "'" + csvRecord[0] + "'" +
                                "," +
                                "'" + csvRecord[1] + "'" +
                                "," +
                                csvRecord[2] +
                                ");";
                        sqlStatements.add(query);
                    }
                } catch (Exception e) {
                    System.out.println(record);
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sqlStatements;
    }
}
