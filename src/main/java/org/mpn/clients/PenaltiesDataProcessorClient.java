package org.mpn.clients;

import org.mpn.PenaltiesDataset;
import org.mpn.ProcessUnit;
import org.mpn.Processor;

import java.nio.file.Paths;
import java.util.List;

public class PenaltiesDataProcessorClient {

    private static String datasource = "src/main/resources/data/mpn/penalties.mpn";

    private static Processor processor = new Processor();

    public static void main(String[] args) {
        List<ProcessUnit> data = processor.process(Paths.get(datasource));
        PenaltiesDataset dataset = new PenaltiesDataset(data);

        System.out.println("Raw dataset size: " + dataset.size());

        System.out.println();
        System.out.println("Number of penalty saves: " + dataset.getGoalkeeperSavesList().size());
        System.out.println("Number of hits at post: " + dataset.getPostHitsList().size());
        System.out.println("Number of off-target shots: " + dataset.getOffTargetShotsList().size());

        System.out.println();
        System.out.println("Number of shots ending in goalkeeper possession: "
                + dataset.getGoalkeeperPossessionSaveList().size());
        System.out.println("Number of goal attempts after penalty shots: "
                + dataset.getGoalAttemptsAfterShotsList().size());
        System.out.println("Number of attacking rebounds: " + dataset.getAttackingReboundsList().size());
        System.out.println("Number of defensive rebounds: " + dataset.getDefensiveReboundsList().size());
        System.out.println("Number of corners: " + dataset.getCornersList().size());
        System.out.println("Number of attacking encroachments: " + dataset.getAttackingEncroachmentsList().size());
        System.out.println("Number of defensive encroachments: " + dataset.getDefensiveEncroachmentsList().size());
        System.out.println("Number of off-target shots: " + dataset.getOffTargetShotsList().size());
        System.out.println("Number of throw-ins: " + dataset.getThrowInsList().size());
    }
}
