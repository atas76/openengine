package org.openengine.abstractmodel;

public class TacticsClient {
    public static void main(String[] args) {
        Tactic tactic_4_4_2 = new Tactic(Formations.formation_4_4_2);
        Tactic tactic_4_3_3 = new Tactic(Formations.formation_4_3_3);
        Tactic tactic_4_2_3_1 = new Tactic(Formations.formation_4_2_3_1);

        System.out.println("4-4-2");
        System.out.println(tactic_4_4_2);

        System.out.println();

        System.out.println("4-3-3");
        System.out.println(tactic_4_3_3);

        System.out.println();

        System.out.println("4-2-3-1");
        System.out.println(tactic_4_2_3_1);

        System.out.println();
        System.out.println("Right back position");
        System.out.println(Tactic.getTacticalLayoutByPosition(TacticalPosition.D_R));

        System.out.println();
        System.out.println("Tactical layout");
        System.out.println(Tactic.getWeightLayoutRepresentationByPosition(TacticalPosition.D_R));
    }
}
