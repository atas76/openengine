package org.openfootie.openengine.domain;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openfootie.openengine.domain.exceptions.InvalidFlagException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {

    private String dataPath = "src/main/resources/data";
    private String nationsDataPath = dataPath + "/nations.csv";
    private String clubDataPath = dataPath + "/clubs.csv";
    private String scoresDataPath = dataPath + "/scores.csv";

    private List<Nation> nations = new ArrayList<>();
    private List<Club> clubs = new ArrayList<>();

    private List<SampleScore> scores = new ArrayList<>();

    private Map<String, Nation> nationNameIndex = new HashMap<>();
    private Map<String, Club> clubNameIndex = new HashMap<>();

    public List<Nation> getNations() {
        return this.nations;
    }

    public List<Club> getClubs() {
        return this.clubs;
    }

    public List<SampleScore> getScores() { return this.scores; }

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
        return loadNations() && loadClubs() && loadScores();
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

    private boolean loadScores() {

        int counter = 0;

        try {

            Reader reader = new FileReader(scoresDataPath);

            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);

            for (CSVRecord record : records) {
                ++counter;
                SampleScore score =
                        new SampleScore(
                                normalizeStrength(Double.parseDouble(record.get(0))),
                                normalizeStrength(Double.parseDouble(record.get(1))),
                                Integer.parseInt(record.get(2)),
                                Integer.parseInt(record.get(3)),
                                transformFlag(record.get(4)));
                this.scores.add(score);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Cannot retrieve score samples data");
            return false;
        } catch (IOException ex) {
            System.out.println("Error accessing score samples data");
            return false;
        } catch (NumberFormatException ex) {
            System.out.println("Validation error: a value should have been a number. Line: " + counter);
            return false;
        } catch (InvalidFlagException ex) {
            System.out.println("Validation error: invalid character to represent a flag: " + counter);
            return false;
        }
        return true;

    }

    private boolean loadClubs() {

        int counter = 0;

        try {

            Reader reader = new FileReader(clubDataPath);

            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);

            for (CSVRecord record : records) {
                ++counter;
                Club club = new Club(record.get(0), record.get(1), Double.parseDouble(record.get(2)));
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
                Nation nation = new Nation(record.get(0), Double.parseDouble(record.get(1)));
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
