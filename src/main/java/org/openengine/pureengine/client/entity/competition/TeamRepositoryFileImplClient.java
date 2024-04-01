package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.Team;
import org.openengine.pureengine.domain.repository.Repository;
import org.openengine.pureengine.domain.repository.TeamRepositoryFileImpl;

public class TeamRepositoryFileImplClient {

    public static void main(String[] args) {
        Repository<Team> teamRepository = new TeamRepositoryFileImpl();

        teamRepository.findAll().forEach(System.out::println);
    }
}
