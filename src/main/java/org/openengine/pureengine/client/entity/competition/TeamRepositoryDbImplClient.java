package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.config.SQLiteConfig;
import org.openengine.pureengine.domain.repository.db.TeamRepositoryDbImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TeamRepositoryDbImplClient {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SQLiteConfig.class)) {
            TeamRepositoryDbImpl teamRepository = context.getBean(TeamRepositoryDbImpl.class);
            teamRepository.findAll().forEach(System.out::println);

            System.out.println();
            System.out.println("Chelsea: " + teamRepository.findByName("Chelsea"));
            System.out.println("Manchester United: " + teamRepository.findByName("Manchester United"));
            System.out.println("Manchester United 2023: " + teamRepository.findByName("Manchester United 2023"));
        }
    }
}
