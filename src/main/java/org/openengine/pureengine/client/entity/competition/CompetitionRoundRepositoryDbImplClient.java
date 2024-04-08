package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.config.SQLiteConfig;
import org.openengine.pureengine.domain.model.CompetitionRound;
import org.openengine.pureengine.domain.repository.db.CompetitionRoundRepositoryDbImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class CompetitionRoundRepositoryDbImplClient {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SQLiteConfig.class)) {
            CompetitionRoundRepositoryDbImpl competitionRoundRepository =
                    context.getBean(CompetitionRoundRepositoryDbImpl.class);

            Collection<CompetitionRound> competitionRounds = competitionRoundRepository.findByReferenceId(1);
            competitionRounds.forEach(System.out::println);
        }
    }
}
