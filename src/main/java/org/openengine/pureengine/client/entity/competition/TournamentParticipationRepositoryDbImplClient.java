package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.config.SQLiteConfig;
import org.openengine.pureengine.domain.repository.db.TournamentParticipationRepositoryDbImpl;
import org.openengine.pureengine.domain.repository.db.TournamentRepositoryDbImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TournamentParticipationRepositoryDbImplClient {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SQLiteConfig.class)) {
            TournamentRepositoryDbImpl tournamentDb = context.getBean(TournamentRepositoryDbImpl.class);
            TournamentParticipationRepositoryDbImpl tournamentParticipationDb =
                    context.getBean(TournamentParticipationRepositoryDbImpl.class);

            System.out.println(tournamentDb.findById(1));
            tournamentParticipationDb.findByReferenceId(1).forEach(System.out::println);

            System.out.println();

            System.out.println(tournamentDb.findById(2));
            tournamentParticipationDb.findByReferenceId(2).forEach(System.out::println);
        }
    }
}
