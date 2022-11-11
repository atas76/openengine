package org.openengine.prototype.domain;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class Environment {

    private String dataPath = "src/main/resources/data";
    private String nationsDataPath = dataPath + "/nations.csv";
    private String clubDataPath = dataPath + "/clubs.csv";
    private String samplesPath = dataPath + "/samples";

    private List<Nation> nations = new ArrayList<>();
    private List<Club> clubs = new ArrayList<>();

    private Map<String, Nation> nationNameIndex = new HashMap<>();
    private Map<String, Club> clubNameIndex = new HashMap<>();

    public List<Nation> getNations() {
        return this.nations;
    }

    public List<Club> getClubs() {
        return this.clubs;
    }

    public Club getClub(String name) {
        return this.clubNameIndex.get(name);
    }

    public Nation getNation(String name) {
        return this.nationNameIndex.get(name);
    }

    public Team getTeam(String name) {

        Club club = getClub(name);

        if (club != null) {
            return club;
        } else {
            return getNation(name);
        }
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
    }

    public boolean load() {
        return loadNations() && loadClubs();
    }

    public String getSamplesPath() {
        return this.samplesPath;
    }

    private String extractClubFilename(String clubName) {
        return clubName.toLowerCase().replace(' ', '_') + ".csv";
    }

    private boolean loadClubs() {

        try {

            Reader reader = new FileReader(clubDataPath);

            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);

            for (CSVRecord record : records) {
                Club club = new Club(record.get(0), record.get(1));
                this.clubs.add(club);
                this.clubNameIndex.put(club.getName(), club);

                loadPlayers(club.getName(), club.getSquad());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Cannot retrieve club data");
            return false;
        } catch (IOException ex) {
            System.out.println("Error accessing club data");
            return false;
        }

        return true;
    }

    private boolean loadNations() {

        try {

            Reader reader = new FileReader(nationsDataPath);

            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);

            for (CSVRecord record : records) {
                Nation nation = new Nation(record.get(0));
                this.nations.add(nation);
                this.nationNameIndex.put(nation.getName(), nation);

                loadPlayers(nation.getName(), nation.getSquad());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Cannot retrieve nations data");
            return false;
        } catch (IOException ex) {
            System.out.println("Error accessing nations data");
            return false;
        }

        return true;
    }

    private void loadPlayers(String name, Squad squad) throws IOException {
        try {
            Iterable<CSVRecord> playerRecords =
                    CSVFormat.EXCEL.parse(new FileReader(this.dataPath + "/" + extractClubFilename(name)));

            for (CSVRecord playerRecord : playerRecords) {
                squad.add(new Player(playerRecord));
                // System.out.println("Player added: " + squad.getPlayers().get(squad.getPlayers().size() - 1).getShirtNumber());
            }

        } catch (FileNotFoundException ex) {
            return;
        }
    }
}
