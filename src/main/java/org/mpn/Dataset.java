package org.mpn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Dataset {

    private final List<ProcessUnit> data;

    public Dataset(List<ProcessUnit> data) {
        this.data = data;
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

    public List<Statement> getStateTransitions() {
        return getByProcessUnitType(Statement.class).stream()
                .map(Statement.class::cast)
                .toList();
    }

    public List<Statement> getStateTransitionsByTeam(String teamKey) {
        return getStateTransitions().stream()
                .filter(s -> s.getTeamKey().equals(teamKey))
                .collect(Collectors.toList());
    }

    public List<Statement> getByDurationGreaterOrEqual(int seconds) {
        return getStateTransitions().stream().filter(s -> s.getDuration() >= seconds).collect(Collectors.toList());
    }

    public List<Statement> getDurationLessOrEqual(int seconds) {
        return getStateTransitions().stream()
                .filter(s -> s.getDuration() <= seconds && s.getDuration() >= 0)
                .collect(Collectors.toList());
    }

    public int size() {
        return data.size();
    }

    private List<? extends ProcessUnit> getByProcessUnitType(Class<? extends ProcessUnit> clazz) {
        return data.stream().filter(clazz::isInstance).toList();
    }
}
