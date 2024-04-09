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

            System.out.println(tournamentRepositoryDb.findById(1));
            System.out.println(tournamentRepositoryDb.findById(2));
            System.out.println();

            Collection<Tournament> tournaments = tournamentRepositoryDb.findAll();
            tournaments.forEach(System.out::println);
        }
    }
}
