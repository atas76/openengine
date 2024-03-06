package org.openengine.pureengine.client;

import org.openengine.pureengine.MatchEngine;
import org.openengine.pureengine.Team;

public class MatchEngineClient {
    public static void main(String[] args) throws InterruptedException {
        MatchEngine matchEngine = new MatchEngine(new Team("Red", 9), new Team("Blue", 4));
        matchEngine.play();
    }
}
