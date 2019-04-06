package org.openfootie.openengine.util.analysis;

import org.junit.Before;
import org.junit.Test;
import org.openfootie.openengine.domain.Player;
import org.openfootie.openengine.domain.Squad;
import org.openfootie.openengine.domain.Team;
import org.openfootie.openengine.domain.TeamImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MatchReportTest {

    private final String TEST_DATA_PATH = "src/test/resources/data/samples";

    private Team homeTeam;
    private Team awayTeam;

    private MatchReport matchReport;

    @Before
    public void setUp() {
        this.homeTeam = new TeamImpl("Atletico Madrid",
                new Squad(Arrays.asList(
                        new Player(1, 56),
                        new Player(13, 87),
                        new Player(20, 68),
                        new Player(3, 74),
                        new Player(4, 72),
                        new Player(2, 78),
                        new Player(15, 74),
                        new Player(24, 76),
                        new Player(21, 76),
                        new Player(23, 69),
                        new Player(10, 70),
                        new Player(5, 78),
                        new Player(14, 77),
                        new Player(11, 77),
                        new Player(6, 79),
                        new Player(8, 80),
                        new Player(9, 67),
                        new Player(19, 69),
                        new Player(22, 70),
                        new Player(7, 86)
                )));
        this.awayTeam = new TeamImpl("Juventus",
                new Squad(Arrays.asList(
                        new Player(21, 48),
                        new Player(22, 79),
                        new Player(1, 80),
                        new Player(15, 71),
                        new Player(4, 57),
                        new Player(3, 80),
                        new Player(2, 71),
                        new Player(24, 71),
                        new Player(19, 77),
                        new Player(12, 75),
                        new Player(20, 79),
                        new Player(37, 65),
                        new Player(6, 70),
                        new Player(16, 76),
                        new Player(14, 75),
                        new Player(23, 75),
                        new Player(30, 73),
                        new Player(11, 78),
                        new Player(33, 78),
                        new Player(5, 84),
                        new Player(18, 66),
                        new Player(17, 77),
                        new Player(7, 95),
                        new Player(10, 86)
                )));
        this.matchReport = new MatchReport(TEST_DATA_PATH + "/CL_Atletico_Juventus.csv");
    }

    @Test
    public void calculateParticipationRatingsTest() {

        List<Long> homeParticipationRatings = this.matchReport.calculateParticipationRatings(this.homeTeam);
        List<Long> awayParticipationRatings = this.matchReport.calculateParticipationRatings(this.awayTeam);

        assertEquals(87, homeParticipationRatings.get(0).longValue());
    }
}
