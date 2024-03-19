package org.openengine.pureengine.tournament;

import org.openengine.pureengine.TieBreaker;

import java.util.List;

public class CompetitionRepository {
    public static final Competition ENGLISH_FA_CUP = // Modelling only later stages
            new Competition("English FA Cup", List.of(
                    new CompetitionRound("5th round", true, TieBreaker.EXTRA_TIME_PENALTIES),
                    new CompetitionRound("Quarter final", true, TieBreaker.EXTRA_TIME_PENALTIES),
                    new CompetitionRound("Semi final", false, TieBreaker.EXTRA_TIME_PENALTIES),
                    new CompetitionRound("Final", false, TieBreaker.EXTRA_TIME_PENALTIES)));
}
