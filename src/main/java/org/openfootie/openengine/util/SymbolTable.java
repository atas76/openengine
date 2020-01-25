package org.openfootie.openengine.util;

import org.fgn.parser.Action;
import org.fgn.parser.State;
import org.openfootie.openengine.util.fgn.StatementWrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SymbolTable {

    private static List<StatementWrapper> statements = new ArrayList<>();

    private List<State> statesIn = new ArrayList<>();
    private List<State> statesOut = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();

    private Set<State> stateInTypes = new HashSet<>();
    private Set<State> stateOutTypes = new HashSet<>();
    private Set<Action> actionTypes = new HashSet<>();

    void addStatementTokens(StatementWrapper wrapper) {
        statements.add(wrapper);
    }

    void addStateIn(State state) {
        this.statesIn.add(state);
        this.stateInTypes.add(state);
    }

    void addStateOut(State state) {
        this.statesOut.add(state);
        this.stateOutTypes.add(state);
    }

    void addAction(Action action) {
        this.actions.add(action);
        this.actionTypes.add(action);
    }

    void printReport() {

        System.out.println("Number of statements: " + statements.size());

        AtomicInteger nTokens = new AtomicInteger();

        statements.forEach(statement -> {
            nTokens.addAndGet(statement.getTokens().size());
        });

        System.out.println("Number of tokens: " + nTokens.intValue());
        System.out.println("Number of in-states: " + this.stateInTypes.size());
        System.out.println("Number of out-states: " + this.stateOutTypes.size());
        System.out.println("Number of actions: " + this.actionTypes.size());

        System.out.println();

        System.out.println("Supported in-states");
        System.out.println("-------------------");
        this.stateInTypes.forEach(System.out::println);

        System.out.println();

        System.out.println("Supported out-states");
        System.out.println("--------------------");
        this.stateOutTypes.forEach(System.out::println);

        System.out.println();

        System.out.println("Supported actions");
        System.out.println("-----------------");
        this.actionTypes.forEach(System.out::println);
    }
}
