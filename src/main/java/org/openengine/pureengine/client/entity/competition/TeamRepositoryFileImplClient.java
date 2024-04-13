package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.Team;
import org.openengine.pureengine.domain.repository.RetrievableRepository;
import org.openengine.pureengine.domain.repository.file.TeamRepositoryFileImpl;

public class TeamRepositoryFileImplClient {

    public static void main(String[] args) {
        RetrievableRepository<Team> teamRepository = new TeamRepositoryFileImpl();

        teamRepository.findAll().forEach(System.out::println);
    }
}
