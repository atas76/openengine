package org.mpn;

public class MatchModel {

    private final ActionChainsMap homeTeamActionChainMap = new ActionChainsMap();
    private final ActionChainsMap awayTeamActionChainsMap = new ActionChainsMap();

    public MatchModel(Dataset dataset, String homeTeam, String awayTeam) {
        createActionChainMaps(dataset, homeTeam, awayTeam);
    }

    private void createActionChainMaps(Dataset dataset, String homeTeam, String awayTeam) {
        Dataset homeTeamDS = dataset.getStateTransitionsByTeam(homeTeam);
        Dataset awayTeamDS = dataset.getStateTransitionsByTeam(awayTeam);

        homeTeamDS.getStateTransitionsList().forEach(stateTransition ->
            homeTeamActionChainMap.addStateTransitionMapping(stateTransition.getInitialState(), stateTransition)
        );
        awayTeamDS.getStateTransitionsList().forEach(stateTransition ->
            awayTeamActionChainsMap.addStateTransitionMapping(stateTransition.getInitialState(), stateTransition)
        );
    }

    public ActionChainsMap getHomeTeamActionChainMappings() {
        return homeTeamActionChainMap;
    }

    public ActionChainsMap getAwayTeamActionChainMappings() {
        return awayTeamActionChainsMap;
    }
}
