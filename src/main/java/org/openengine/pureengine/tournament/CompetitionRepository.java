package org.openengine.pureengine.tournament;

import java.util.List;

public class CompetitionRepository {
    // Modelling only later stages
    public static final Competition ENGLISH_FA_CUP =
            new Competition("English FA Cup", List.of(
                    new CompetitionRound("5th round", true),
                    new CompetitionRound("Quarter final", true),
                    new CompetitionRound("Semi final", false),
                    new CompetitionRound("Final", false)));
}
