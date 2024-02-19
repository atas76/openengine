package org.mpn.clients;

import org.mpn.*;

import java.nio.file.Paths;
import java.util.List;

import static org.mpn.State.*;

public class ProcessorClient {

    private static String datasource = "src/main/resources/data/mpn/cl_v2.mpn";
    private static Processor processor = new Processor();

    public static void main(String[] args) {
        List<ProcessUnit> data = processor.process(Paths.get(datasource));
        Dataset dataset = new Dataset(data);

        System.out.println("Raw dataset size: " + dataset.size());
        System.out.println("Number of directives: " + dataset.getDirectives().size());
        System.out.println("Number of BREAK directives: " + dataset.countDirectives(Directive.BREAK));
        System.out.println("Number of half time directives: " + dataset.countDirectives(Directive.HT));
        System.out.println("BREAK directive locations: " + dataset.getDirectiveLocations(Directive.BREAK));
        System.out.println("Half time directive location: " + dataset.getDirectiveLocations(Directive.HT));
        System.out.println("Number of statements: " + dataset.getStateTransitionsList().size());
        System.out.println();
        List<Statement> stateTransitionsL = dataset.listStateTransitionsByTeam("L");
        List<Statement> stateTransitionsT = dataset.listStateTransitionsByTeam("T");
        System.out.println("Liverpool state transitions number: " + stateTransitionsL.size());
        System.out.println("Liverpool sample value: " + stateTransitionsL.get(0).getTeamKey());
        System.out.println("Tottenham state transitions number: " + stateTransitionsT.size());
        System.out.println("Tottenham sample value: " + stateTransitionsT.get(0).getTeamKey());
        System.out.println();

        List<Statement> attacks = dataset.listStateTransitionsByInitialState(ATTACK);
        List<Statement> counterAttacks = dataset.listStateTransitionsByInitialState(COUNTER_ATTACK);

        System.out.println("Number of attacking phases: " + (attacks.size() + counterAttacks.size()));
        System.out.println();

        List<Statement> longPhases = dataset.listByDurationGreaterOrEqual(5);
        System.out.println("Number of long phases: " + longPhases.size());
        System.out.println("Long phase example: " + longPhases.get(38).getDuration());
        List<Statement> shorterPhases = dataset.listByDurationLessOrEqual(4);
        System.out.println("Number of shorter phases: " + shorterPhases.size());
        List<Statement> shortPhases = dataset.listByDurationLessOrEqual(3);
        System.out.println("Number of short phases: " + shortPhases.size());
        System.out.println("Short phase example: " + shortPhases.get(13).getDuration());

        Dataset liverpoolLongPhases =
                dataset.getStateTransitionsByTeam("L").getByDurationGreaterOrEqual(5);
        Dataset liverpoolShortPhases =
                dataset.getStateTransitionsByTeam("L").getDurationLessOrEqual(4);
        Dataset liverpoolVeryLongPhases =
                dataset.getStateTransitionsByTeam("L").getByDurationGreaterOrEqual(10);
        Dataset tottenhamLongPhases =
                dataset.getStateTransitionsByTeam("T").getByDurationGreaterOrEqual(5);
        Dataset tottenhamShortPhases =
                dataset.getStateTransitionsByTeam("T").getDurationLessOrEqual(4);
        Dataset tottenhamVeryLongPhases =
                dataset.getStateTransitionsByTeam("T").getByDurationGreaterOrEqual(10);

        System.out.println();
        System.out.println("Liverpool long phases #: " + liverpoolLongPhases.size());
        System.out.println("Liverpool short phases #: " + liverpoolShortPhases.size());
        System.out.println("Liverpool very long phases #: " + liverpoolVeryLongPhases.size());
        System.out.println("Tottenham long phases #: " + tottenhamLongPhases.size());
        System.out.println("Tottenham short phases #: " + tottenhamShortPhases.size());
        System.out.println("Tottenham very long phases #: " + tottenhamVeryLongPhases.size());
        System.out.println("Both teams' very long phases #: " +
                dataset.getStateTransitions().getByDurationGreaterOrEqual(10).size());

        System.out.println();
        Dataset statsDataset = new Dataset(data, "L", "T");
        var possession = statsDataset.getBallPossession();

        System.out.println("Team possession: Liverpool - Tottenham " + possession[0] + " - " + possession[1]);

        System.out.println();
        Dataset goalAttemptsDataset = dataset.getStateTransitionsByInitialState(GOAL_ATTEMPT);
        System.out.println("Number of goal attempts: " + goalAttemptsDataset.size());
        Dataset liverpoolOpenPlayGoalAttempts = goalAttemptsDataset.getStateTransitionsByTeam("L");
        Dataset tottenhamOpenPlayGoalAttempts = goalAttemptsDataset.getStateTransitionsByTeam("T");
        System.out.println("Number of Liverpool open play goal attempts: " + liverpoolOpenPlayGoalAttempts.size());
        System.out.println("Number of Tottenham open play goal attempts: " + tottenhamOpenPlayGoalAttempts.size());
        System.out.println();
        System.out.println("Match score: Liverpool - Tottenham: " +
                dataset.getStateTransitionsByTeam("L").getStateTransitionsByEndState(GOAL).size() +
                " - " +
                dataset.getStateTransitionsByTeam("T").getStateTransitionsByEndState(GOAL).size()
        );

        System.out.println();
        Dataset initialPitchPositionA = dataset.getStateTransitionsByInitialPitchPosition(PitchPosition.ABw);
        System.out.println("Number of state transitions with 'ABw' initial pitch position: " +
                initialPitchPositionA.size());
        System.out.println("Liverpool initial states in ABw: " +
                initialPitchPositionA.getStateTransitionsByTeam("L").size());
        System.out.println("Tottenham initial states in ABw: " +
                initialPitchPositionA.getStateTransitionsByTeam("T").size());

        System.out.println();
        Dataset outcomePitchPositionBw = dataset.getStateTransitionsByOutcomePitchPosition(PitchPosition.ABw);
        System.out.println("Number of state transitions with 'ABw' outcome pitch position: " +
                outcomePitchPositionBw.size());
        System.out.println("Liverpool outcome states with 'ABw' outcome pitch position: " +
                outcomePitchPositionBw.getStateTransitionsByTeam("L").size());
        System.out.println("Tottenham outcome states with 'ABw' outcome pitch position: " +
                outcomePitchPositionBw.getStateTransitionsByTeam("T").size());

        System.out.println();
        Dataset liverpoolPossessionLossBw = outcomePitchPositionBw.getStateTransitionsByTeam("L")
                .getPossessionChanges();
        Dataset tottenhamPossessionLossBw = outcomePitchPositionBw.getStateTransitionsByTeam("T")
                .getPossessionChanges();
        System.out.println("Liverpool outcome states with possession loss in 'Bw': " +
                liverpoolPossessionLossBw.getStateTransitionsByTeam("L").size());
        System.out.println("Tottenham outcome states with possession loss in 'Bw': " +
                tottenhamPossessionLossBw.getStateTransitionsByTeam("T").size());

        System.out.println();
        Dataset tottenhamOpenPlaySaves = liverpoolOpenPlayGoalAttempts.getStateTransitionsByEndState(SAVE);
        Dataset liverpoolOpenPlaySaves = tottenhamOpenPlayGoalAttempts.getStateTransitionsByEndState(SAVE);
        System.out.println("Open play saves: Liverpool - Tottenham " +
                liverpoolOpenPlaySaves.size() + " - " + tottenhamOpenPlaySaves.size());
        Dataset liverpoolCornersFromGoalAttempts =
                liverpoolOpenPlayGoalAttempts.getStateTransitionsByGoalAttemptOutcome(CORNER);
        Dataset tottenhamCornersFromGoalAttempts =
                tottenhamOpenPlayGoalAttempts.getStateTransitionsByGoalAttemptOutcome(CORNER);
        System.out.println("Corners from goal attempts: Liverpool - Tottenham " +
                liverpoolCornersFromGoalAttempts.size() + " - " + tottenhamCornersFromGoalAttempts.size());
        Dataset liverpoolCornersSaves = tottenhamOpenPlaySaves.getStateTransitionsByGoalAttemptOutcome(CORNER);
        Dataset tottenhamCornersSaves = liverpoolOpenPlaySaves.getStateTransitionsByGoalAttemptOutcome(CORNER);
        System.out.println("Corners from saves: Liverpool - Tottenham " +
                liverpoolCornersSaves.size() + " - " + tottenhamCornersSaves.size());

        System.out.println();
        Dataset liverpoolPossessionLosses = dataset.getStateTransitionsByTeam("L").getPossessionChanges();
        Dataset tottenhamPossessionLosses = dataset.getStateTransitionsByTeam("T").getPossessionChanges();
        System.out.println("Possession losses: Liverpool - Tottenham "
                + liverpoolPossessionLosses.size() + " - " + tottenhamPossessionLosses.size());
    }

    private static void debug(List<Statement> stateTransitions) {
        stateTransitions.forEach(stateTransition ->
                System.out.println(stateTransition.getStartTime() + ": " + stateTransition.getDuration()));
    }
}
