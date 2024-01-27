package org.mpn;

public class MatchModelClient {
    public static void main(String[] args) {
        Dataset data = Processable.loadData();

        MatchModel matchModel = new MatchModel(data, "L", "T");
        System.out.println("Liverpool mappings");
        System.out.println("-------------------");
        System.out.println();
        matchModel.getHomeTeamActionChainMappings().displayStateTransitionMappings();
        System.out.println();
        System.out.println("Tottenham mappings");
        System.out.println("-------------------");
        System.out.println();
        matchModel.getAwayTeamActionChainMappings().displayStateTransitionMappings();
    }
}
