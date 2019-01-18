package org.openfootie.openengine.gameplay;

import org.openfootie.openengine.domain.Environment;

public class FriendlyMatch {

    public static void main(String [] args) {

       Environment environment = new Environment();

       if (!environment.load()) {
           System.out.println("Error loading environment");
           return;
       }

    }
}
