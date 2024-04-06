package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.config.SQLiteConfig;
import org.openengine.pureengine.domain.model.Competition;
import org.openengine.pureengine.domain.repository.db.CompetitionRepositoryDbImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class CompetitionRepositoryDbImplClient {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SQLiteConfig.class)) {
            CompetitionRepositoryDbImpl competitionRepository = context.getBean(CompetitionRepositoryDbImpl.class);

            Competition competition = competitionRepository.findById(1);
            System.out.println(competition);

            Competition faCup = competitionRepository.findByName("FA Cup");
            System.out.println(faCup);

            Collection<Competition> competitions = competitionRepository.findAll();
            competitions.forEach(System.out::println);
        }
    }
}
