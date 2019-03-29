package org.openfootie.openengine.util.analysis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class MatchSample {

    private List<ParticipationUnit> matchParticipation = new ArrayList<>();

    public MatchSample(String matchFile) {
        try {

            Reader reader = new FileReader(matchFile);

            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);

            for (CSVRecord record : records) {
                matchParticipation.add(
                        new ParticipationUnit(
                                record.get(0),
                                record.get(1),
                                Integer.parseInt(record.get(2)),
                                Integer.parseInt(record.get(3)))
                );
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot retrieve samples data");
        } catch (IOException ex) {
            System.out.println("Error accessing sample data");
        }
    }

    public List<ParticipationUnit> getMatchParticipation() {
        return this.matchParticipation;
    }
}
