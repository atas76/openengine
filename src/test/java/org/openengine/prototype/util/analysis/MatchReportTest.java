package org.openengine.prototype.util.analysis;

import org.junit.Before;
import org.junit.Test;
import org.openengine.prototype.domain.Player;
import org.openengine.prototype.domain.Squad;
import org.openengine.prototype.domain.Team;
import org.openengine.prototype.domain.TeamImpl;

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
    public void calculateFormationRatingsTest() {

        Long homeFormationRating = this.matchReport.getFormationRating(this.homeTeam);
        Long awayFormationRating = this.matchReport.getFormationRating(this.awayTeam);

        assertEquals(850, homeFormationRating.longValue());
        assertEquals(870, awayFormationRating.longValue());
    }

    @Test
    public void calculateParticipationRatingsTest() {

        List<Long> homeParticipationRatings = this.matchReport.calculateParticipationRatings(this.homeTeam);
        List<Long> awayParticipationRatings = this.matchReport.calculateParticipationRatings(this.awayTeam);

        assertEquals(87, homeParticipationRatings.get(0).longValue());
        assertEquals(68, homeParticipationRatings.get(1).longValue());
        assertEquals(76, homeParticipationRatings.get(2).longValue());
        assertEquals(78, homeParticipationRatings.get(3).longValue());
        assertEquals(74, homeParticipationRatings.get(4).longValue());
        assertEquals(80, homeParticipationRatings.get(5).longValue());
        assertEquals(77, homeParticipationRatings.get(6).longValue());
        assertEquals(53, homeParticipationRatings.get(7).longValue());
        assertEquals(25, homeParticipationRatings.get(8).longValue());
        assertEquals(59, homeParticipationRatings.get(9).longValue());
        assertEquals(18, homeParticipationRatings.get(10).longValue());
        assertEquals(86, homeParticipationRatings.get(11).longValue());
        assertEquals(44, homeParticipationRatings.get(12).longValue());
        assertEquals(25, homeParticipationRatings.get(13).longValue());

        assertEquals(80, awayParticipationRatings.get(0).longValue());
        assertEquals(71, awayParticipationRatings.get(1).longValue());
        assertEquals(77, awayParticipationRatings.get(2).longValue());
        assertEquals(80, awayParticipationRatings.get(3).longValue());
        assertEquals(75, awayParticipationRatings.get(4).longValue());
        assertEquals(73, awayParticipationRatings.get(5).longValue());
        assertEquals(68, awayParticipationRatings.get(6).longValue());
        assertEquals(14, awayParticipationRatings.get(7).longValue());
        assertEquals(70, awayParticipationRatings.get(8).longValue());
        assertEquals(5, awayParticipationRatings.get(9).longValue());
        assertEquals(76, awayParticipationRatings.get(10).longValue());
        assertEquals(9, awayParticipationRatings.get(11).longValue());
        assertEquals(77, awayParticipationRatings.get(12).longValue());
        assertEquals(95, awayParticipationRatings.get(13).longValue());
    }
}
