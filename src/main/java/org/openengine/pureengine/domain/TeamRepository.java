package org.openengine.pureengine.domain;

import org.openengine.pureengine.Team;

import java.util.Map;

public class TeamRepository {
    private static final Map<String, Team> teams = Map.ofEntries(
            Map.entry("Coventry", new Team("Coventry City", 5)),
            Map.entry("Maidstone", new Team("Maidstone United", 1)),
            Map.entry("Bournemouth", new Team("Bournemouth", 7)),
            Map.entry("Leicester", new Team("Leicester City", 5)),
            Map.entry("Blackburn", new Team("Blackburn Rovers", 5)),
            Map.entry("Newcastle", new Team("Newcastle United", 8)),
            Map.entry("Luton", new Team("Luton Town", 6)),
            Map.entry("Manchester City", new Team("Manchester City", 10)),
            Map.entry("Chelsea", new Team("Chelsea", 7)),
            Map.entry("Leeds", new Team("Leeds United", 5)),
            Map.entry("Nottingham Forest", new Team("Nottingham Forest", 6)),
            Map.entry("Manchester United", new Team("Manchester United", 8)),
            Map.entry("Wolves", new Team("Wolverhampton Wanderers", 7)),
            Map.entry("Brighton", new Team("Brighton & Hove Albion", 8)),
            Map.entry("Liverpool", new Team("Liverpool", 10)),
            Map.entry("Southampton", new Team("Southampton", 5))
    );

    public static Team getTeam(String name) {
        return teams.get(name);
    }
}
