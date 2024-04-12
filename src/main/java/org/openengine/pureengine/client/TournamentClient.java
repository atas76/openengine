package org.openengine.pureengine.client;

import org.openengine.pureengine.config.SQLiteConfig;
import org.openengine.pureengine.domain.model.Tournament;
import org.openengine.pureengine.domain.repository.Repository;
import org.openengine.pureengine.domain.repository.db.TournamentRepositoryDbImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class TournamentClient {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SQLiteConfig.class)) {
            Repository<Tournament> tournamentRepository = context.getBean(TournamentRepositoryDbImpl.class);
            Collection<Tournament> tournaments = tournamentRepository.findAll();
            List<Tournament> tournamentHistory = new ArrayList<>(tournaments);
            tournamentHistory.sort(Comparator.comparingInt(Tournament::getId).reversed());

            tournamentHistory.forEach(tournament -> {
                tournament.displayHeader();
                System.out.println();
                tournament.displayParticipants();

                System.out.println();
                tournament.play();
                System.out.println();
            });
        }
    }
}
