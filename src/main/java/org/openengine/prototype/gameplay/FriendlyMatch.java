package org.openengine.prototype.gameplay;

import org.openengine.prototype.domain.Environment;
import org.openengine.prototype.domain.TeamImpl;
import org.openengine.prototype.engine.Match;
import org.openengine.prototype.engine.log.MatchLogInfo;

import static org.openengine.prototype.engine.log.MatchLogInfo.MatchInfoType.*;

public class FriendlyMatch {

    public static void main(String [] args) {

        Environment environment = new Environment();

        if (!environment.load()) {
            System.out.println("Error loading environment");
            return;
        }

        Match match = new Match(new TeamImpl("Team A"), new TeamImpl("Team B"));

        match.playHalfTime();
        match.getMatchLogger().info(new MatchLogInfo(HT));

        System.out.println("Half time score: " + match.getScore());

        match.playHalfTime();
        match.getMatchLogger().info(new MatchLogInfo(FT));

        System.out.println("Full time score: " + match.getScore());
        System.out.println();
        System.out.println("Match report");
        match.getMatchLogger().displayReport();
    }
}
