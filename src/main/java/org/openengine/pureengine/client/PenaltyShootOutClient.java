package org.openengine.pureengine.client;

import org.openengine.pureengine.PenaltyShootOut;
import org.openengine.pureengine.Team;

public class PenaltyShootOutClient {
    public static void main(String[] args) {
        new PenaltyShootOut(new Team("Reds"), new Team("Blues")).execute(false);
    }
}
