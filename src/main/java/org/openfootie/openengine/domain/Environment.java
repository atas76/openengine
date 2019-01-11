package org.openfootie.openengine.domain;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openfootie.openengine.domain.exceptions.InvalidFlagException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class Environment {

    private String dataPath = "src/main/resources/data";
    private String nationsDataPath = dataPath + "/nations.csv";
    private String clubDataPath = dataPath + "/clubs.csv";
    private String scoresDataPath = dataPath + "/scores.csv";

    private List<Nation> nations = new ArrayList<>();
    private List<Club> clubs = new ArrayList<>();

    private List<SampleScore> scores = new ArrayList<>();
    private List<SampleScore> neutralVenueScores = new ArrayList<>();
    private List<SampleScore> homeAwayVenueScores = new ArrayList<>();

    private Map<String, Nation> nationNameIndex = new HashMap<>();
    private Map<String, Club> clubNameIndex = new HashMap<>();

    public List<Nation> getNations() {
        return this.nations;
    }

    public List<Club> getClubs() {
        return this.clubs;
    }

    public List<SampleScore> getScores() { return this.scores; }

    public List<SampleScore> getNeutralVenueScores() {
        return neutralVenueScores;
    }

    public List<SampleScore> getHomeAwayVenueScores() {
        return homeAwayVenueScores;
    }

    public Nation getNation(String name) {
        return this.nationNameIndex.get(name);
    }

    public Club getClub(String name) {
        return this.clubNameIndex.get(name);
    }

    public Club getClub(int rank) {

        int index = rank - 1;

        if (index < 0 || index > this.clubs.size() - 1) {
            return null;
        }

        return this.clubs.get(index);
    }

    public Nation getNation(int rank) {

        int index = rank - 1;

        if (index < 0 || index > this.nations.size() - 1) {
            return null;
        }

        return this.nations.get(index);
    }

    public Environment() {}

    public Environment(String dataPath) {
        this.dataPath = dataPath;
        this.nationsDataPath = dataPath + "/nations.csv";
        this.clubDataPath = dataPath + "/clubs.csv";
        this.scoresDataPath = dataPath + "/scores.csv";
    }

    public boolean load() {
        return loadNations() && loadClubs();
    }

    private static boolean transformFlag(String flag) throws InvalidFlagException {
        if ("*".equals(flag)) {
            return false;
        } else if ("-".equals(flag)) {
            return true;
        } else {
            throw new InvalidFlagException();
        }
    }

    private static double normalizeStrength(double input) {
        return Math.log10(input);
    }

    private boolean loadClubs() {

        int counter = 0;

        try {

            Reader reader = new FileReader(clubDataPath);

            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);

            for (CSVRecord record : records) {
                ++counter;
                Club club = new Club(record.get(0), record.get(1));
                this.clubs.add(club);
                this.clubNameIndex.put(club.getName(), club);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Cannot retrieve nations data");
            return false;
        } catch (IOException ex) {
            System.out.println("Error accessing nations data");
            return false;
        } catch (NumberFormatException ex) {
            System.out.println("Validation error: nation strength must be a decimal number. Line: " + counter);
        }
        return true;
    }

    private boolean loadNations() {

        int counter = 0;

        try {

            Reader reader = new FileReader(nationsDataPath);

            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);

            for (CSVRecord record : records) {
                ++counter;
                Nation nation = new Nation(record.get(0));
                this.nations.add(nation);
                this.nationNameIndex.put(nation.getName(), nation);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Cannot retrieve nations data");
            return false;
        } catch (IOException ex) {
            System.out.println("Error accessing nations data");
            return false;
        } catch (NumberFormatException ex) {
            System.out.println("Validation error: nation strength must be a decimal number. Line: " + counter);
        }
        return true;
    }
}
