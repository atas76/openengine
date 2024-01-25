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

        displayLayoutsByPosition("Right back", TacticalPosition.D_R);
        displayLayoutsByPosition("Left back", TacticalPosition.D_L);
        displayLayoutsByPosition("Centre right back", TacticalPosition.D_CR);
        displayLayoutsByPosition("Centre left back", TacticalPosition.D_CL);

        displayLayoutsByPosition("Right midfielder", TacticalPosition.M_R);
        displayLayoutsByPosition("Left midfielder", TacticalPosition.M_L);
        displayLayoutsByPosition("Centre right midfielder" , TacticalPosition.M_CR);
        displayLayoutsByPosition("Centre left midfielder", TacticalPosition.M_CL);

        displayLayoutsByPosition("Centre right forward", TacticalPosition.F_CR);
        displayLayoutsByPosition("Centre left forward", TacticalPosition.F_CL);
    }

    private static void displayLayoutsByPosition(String positionLabel, TacticalPosition tacticalPosition) {
        System.out.println();
        System.out.println(positionLabel);
        System.out.println(Tactic.getTacticalLayoutByPosition(tacticalPosition));
        System.out.println("Weight layout");
        System.out.println(Tactic.getWeightLayoutRepresentationByPosition(tacticalPosition));
    }
}
