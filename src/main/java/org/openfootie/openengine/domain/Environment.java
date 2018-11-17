package org.openfootie.openengine.domain;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

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

    private List<Nation> nations = new ArrayList<Nation>();
    private List<Club> clubs = new ArrayList<Club>();

    private Map<String, Nation> nationNameIndex = new HashMap<String, Nation>();
    private Map<String, Club> clubNameIndex = new HashMap<String, Club>();

    public List<Nation> getNations() {
        return this.nations;
    }

    public List<Club> getClubs() {
        return this.clubs;
    }

    public Nation getNation(String name) {
        return this.nationNameIndex.get(name);
    }

    public Club getClub(String name) {
        return this.clubNameIndex.get(name);
    }

    public Environment() {}

    public Environment(String dataPath) {
        this.dataPath = dataPath;
        this.nationsDataPath = dataPath + "/nations.csv";
        this.clubDataPath = dataPath + "/clubs.csv";
    }

    public boolean load() {
        return loadNations() && loadClubs();
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
