package org.openengine.pureengine.domain;

import org.openengine.pureengine.Team;

import java.util.List;

public class TeamRepository {
    public static final List<Team> team = List.of(
            new Team("Coventry", 5),
            new Team("Maidstone", 1),
            new Team("Bournemouth", 7),
            new Team("Leicester", 5),
            new Team("Blackburn", 5),
            new Team("Newcastle", 8),
            new Team("Luton", 6),
            new Team("Manchester City", 10),
            new Team("Chelsea", 7),
            new Team("Leeds", 5),
            new Team("Nottingham Forest", 6),
            new Team("Manchester United", 8),
            new Team("Wolves", 7),
            new Team("Brighton", 8),
            new Team("Liverpool", 10),
            new Team("Southampton", 5)
    );
}
