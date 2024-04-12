package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.config.SQLiteConfig;
import org.openengine.pureengine.domain.model.Tournament;
import org.openengine.pureengine.domain.repository.db.TournamentRepositoryDbImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class TournamentRepositoryDbImplClient {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SQLiteConfig.class)) {
            TournamentRepositoryDbImpl tournamentRepositoryDb = context.getBean(TournamentRepositoryDbImpl.class);

            Tournament tournament1 = tournamentRepositoryDb.findById(1);
            System.out.println(tournament1);
            tournament1.displayParticipants();


            Tournament tournament2 = tournamentRepositoryDb.findById(2);
            System.out.println();
            System.out.println(tournament2);
            tournament2.displayParticipants();
            System.out.println();

            Collection<Tournament> tournaments = tournamentRepositoryDb.findAll();
            tournaments.forEach(System.out::println);
        }
    }
}
