package org.mpn;

import java.nio.file.Paths;
import java.util.List;

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
        List<Statement> stateTransitionsL = dataset.getStateTransitionsByTeamList("L");
        List<Statement> stateTransitionsT = dataset.getStateTransitionsByTeamList("T");
        System.out.println("Liverpool state transitions number: " + stateTransitionsL.size());
        System.out.println("Liverpool sample value: " + stateTransitionsL.get(0).getTeamKey());
        System.out.println("Tottenham state transitions number: " + stateTransitionsT.size());
        System.out.println("Tottenham sample value: " + stateTransitionsT.get(0).getTeamKey());
        System.out.println();

        /*
        dataset.getStateTransitions()
                .forEach(stateTransition ->
                        System.out.println(stateTransition.getStartTime() + ": " + stateTransition.getDuration()));
         */

        List<Statement> longPhases = dataset.getByDurationGreaterOrEqualList(5);
        System.out.println("Number of long phases: " + longPhases.size());
        System.out.println("Long phase example: " + longPhases.get(38).getDuration());
        List<Statement> shorterPhases = dataset.getByDurationLessOrEqualList(4);
        System.out.println("Number of shorter phases: " + shorterPhases.size());
        List<Statement> shortPhases = dataset.getByDurationLessOrEqualList(3);
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
    }
}
