package org.mpn;

import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Dataset {

    private final List<? extends ProcessUnit> data;
    private String homeTeam;
    private String awayTeam;
    private Random rnd = new Random();

    public Dataset(String datasource) {
        this(new Processor().process(Paths.get(datasource)));
    }

    public Dataset(String datasource, String homeTeam, String awayTeam) {
        this(new Processor().process(Paths.get(datasource)), homeTeam, awayTeam);
    }

    public Dataset(List<? extends ProcessUnit> data) {
        this.data = data;
        setEndTimesFromContext();
    }

    public Dataset(List<? extends ProcessUnit> data, String homeTeam, String awayTeam) {
        this(data);
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public List<? extends ProcessUnit> getData() {
        return this.data;
    }

    public Statement getAny() {
        return (Statement) data.get(rnd.nextInt(data.size()));
    }

    private void setEndTimesFromContext() {
        for (int i = 0; i < this.data.size(); i++) {
            if (data.get(i) instanceof Statement currentStatement) {
                if (currentStatement.getDuration() < 0) {
                    if (i + 1 < this.data.size() && data.get(i + 1) instanceof Statement nextStatement) {
                        currentStatement.setEndTime(nextStatement.getStartTime());
                    } else {
                        currentStatement.setEndTime(currentStatement.getStartTime());
                    }
                }
            }
        }
    }

    public List<Directive> getDirectives() {
        return getByProcessUnitType(Directive.class).stream()
                .map(Directive.class::cast)
                .toList();

    }

    public long countDirectives(Directive directive) {
        return getDirectives().stream()
                .filter(d -> d.equals(directive))
                .count();
    }

    public List<Integer> getDirectiveLocations(Directive directive) {
        return IntStream.range(0, data.size())
                .filter(index -> data.get(index) instanceof Directive && data.get(index).equals(directive))
                .boxed()
                .collect(Collectors.toList());

    }

    public List<Statement> getStateTransitionsList() {
        return getByProcessUnitType(Statement.class).stream()
                .map(Statement.class::cast)
                .toList();
    }

    public Dataset getStateTransitions() {
        return new Dataset(getStateTransitionsList());
    }

    public List<Statement> listStateTransitionsByTeam(String teamKey) {
        return getStateTransitionsList().stream()
                .filter(s -> s.getTeamKey().equals(teamKey))
                .collect(Collectors.toList());
    }

    public Dataset getStateTransitionsByTeam(String teamKey) {
        return new Dataset(listStateTransitionsByTeam(teamKey));
    }

    public List<Statement> listByDurationGreaterOrEqual(int seconds) {
        return getStateTransitionsList().stream().filter(s -> s.getDuration() >= seconds).collect(Collectors.toList());
    }

    public Dataset getByDurationGreaterOrEqual(int seconds) {
        return new Dataset(listByDurationGreaterOrEqual(seconds));
    }

    public List<Statement> listByDurationLessOrEqual(int seconds) {
        return getStateTransitionsList().stream()
                .filter(s -> s.getDuration() <= seconds && s.getDuration() >= 0)
                .collect(Collectors.toList());
    }

    public Dataset getDurationLessOrEqual(int seconds) {
        return new Dataset(listByDurationLessOrEqual(seconds));
    }

    public List<Statement> listStateTransitionsByInitialState(State initialState) {
        return getStateTransitionsList().stream()
                .filter(s -> initialState.equals(s.getInitialState())).toList();
    }

    public Dataset getStateTransitionsByInitialState(State initialState) {
        return new Dataset(listStateTransitionsByInitialState(initialState));
    }

    public List<Statement> listStateTransitionsByEndState(State endState) {
        return getStateTransitionsList().stream()
                .filter(s -> endState.equals(s.getEndState())).toList();
    }

    public Dataset getStateTransitionsByEndState(State endState) {
        return new Dataset(listStateTransitionsByEndState(endState));
    }

    public List<Statement> listStateTransitionsByGoalAttemptOutcome(State goalAttemptOutcome) {
        return getStateTransitionsList().stream()
                .filter(s -> goalAttemptOutcome.equals(s.getGoalAttemptOutcome())).toList();
    }

    public Dataset getStateTransitionsByGoalAttemptOutcome(State goalAttemptOutcome) {
        return new Dataset(listStateTransitionsByGoalAttemptOutcome(goalAttemptOutcome));
    }

    public List<Statement> listStateTransitionsByInitialPitchPosition(PitchPosition pitchPosition) {
        return getStateTransitionsList().stream()
                .filter(s -> pitchPosition.equals(s.getInitialPitchPosition())).toList();
    }

    public Dataset getStateTransitionsByInitialPitchPosition(PitchPosition pitchPosition) {
        return new Dataset(listStateTransitionsByInitialPitchPosition(pitchPosition));
    }

    public List<Statement> listStateTransitionsByOutcomePitchPosition(PitchPosition pitchPosition) {
        return getStateTransitionsList().stream()
                .filter(s -> pitchPosition.equals(s.getOutcomePitchPosition())).toList();
    }

    public Dataset getStateTransitionsByOutcomePitchPosition(PitchPosition pitchPosition) {
        return new Dataset(listStateTransitionsByOutcomePitchPosition(pitchPosition));
    }

    public List<Statement> listPossessionChanges() {
        return getStateTransitionsList().stream().filter(s -> !s.isPossessionRetained()).toList();
    }

    public Dataset getPossessionChanges() {
        return new Dataset(listPossessionChanges());
    }

    public int [] getBallPossession() {
        int homeTeamSeconds = listStateTransitionsByTeam(this.homeTeam).stream().mapToInt(Statement::getDuration).sum();
        int awayTeamSeconds = listStateTransitionsByTeam(this.awayTeam).stream().mapToInt(Statement::getDuration).sum();

        int homeTeamPercentage = (int) Math.round((double) homeTeamSeconds * 100 / (homeTeamSeconds + awayTeamSeconds));
        int awayTeamPercentage = (int) Math.round((double) awayTeamSeconds * 100 / (homeTeamSeconds + awayTeamSeconds));

        return new int [] {homeTeamPercentage, awayTeamPercentage};
    }

    public int size() {
        return data.size();
    }

    protected List<? extends ProcessUnit> getByProcessUnitType(Class<? extends ProcessUnit> clazz) {
        return data.stream().filter(clazz::isInstance).toList();
    }
}
