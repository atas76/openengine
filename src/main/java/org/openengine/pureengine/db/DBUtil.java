package org.openengine.pureengine.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBUtil {

    static final String DB_URL =
            "jdbc:sqlite:src/main/resources/db/pureengine/domain.db";

    static final String DOMAIN_INIT_SCRIPT = "src/main/resources/db/pureengine/sql/domain_init.sql";

    public static void main(String[] args) throws IOException {
        // clean(DB_URL);
        create(DOMAIN_INIT_SCRIPT, DB_URL);
    }

    public static void create(String initScript, String dbUrl) throws IOException {
        executeStatements(dbUrl,
                Arrays.stream(new String(Files.readAllBytes(Paths.get(initScript))).split(";")).toList());
    }

    public static void clean(String dbUrl) {
        List<String> sqlStatements = new ArrayList<>();

        sqlStatements.add("drop table TournamentParticipation");
        sqlStatements.add("drop table Tournament");
        sqlStatements.add("drop table CompetitionRound");
        sqlStatements.add("drop table Competition");
        sqlStatements.add("drop table Team");
        sqlStatements.add("drop table TournamentReplayHistory");

        executeStatements(dbUrl, sqlStatements);
    }

    public static void deleteData(String dbUrl) {

        List<String> sqlStatements = new ArrayList<>();

        sqlStatements.add("delete from TournamentParticipation");
        sqlStatements.add("delete from Tournament");
        sqlStatements.add("delete from CompetitionRound");
        sqlStatements.add("delete from Competition");
        sqlStatements.add("delete from Team");

        executeStatements(dbUrl, sqlStatements);
    }

    static void executeStatements(String dbUrl, List<String> sqlStatements) {
        try {
            try (Connection conn = DriverManager.getConnection(dbUrl);

                 Statement dbStatement = conn.createStatement()) {

                for (String sqlStatement : sqlStatements) {
                    if (!sqlStatement.trim().isEmpty()) {
                        dbStatement.execute(sqlStatement.trim());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
