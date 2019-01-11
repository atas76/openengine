package org.openfootie.openengine.gameplay;

import org.openfootie.openengine.domain.Environment;
import org.openfootie.openengine.domain.Result;

public class FriendlyMatch {

    public static void main(String [] args) {

       boolean international = true;
       Environment environment = new Environment();

       if (!environment.load()) {
           System.out.println("Error loading environment");
           return;
       }

       if ("c".equals(args[0]) || "club".equals(args[0])) {
           international = false;
       }
    }
}
