package org.openengine.pureengine.client.entity.competition;

import org.openengine.pureengine.domain.repository.TeamRepository;

public class TeamRepositoryClient {

    public static void main(String[] args) {
        TeamRepository.loadData();
        TeamRepository.getEntries().forEach(System.out::println);
    }
}
