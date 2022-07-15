
import java.util.concurrent.*;

/*
    Author - Golubev Ivan Vladimirovich
    email - mrcrakozyabra@gmail.com
*/

public class Solution {

    public static void main(String[] args) {
        ScheduledExecutorService plantsGrow = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService animalsLife = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService statistics = Executors.newScheduledThreadPool(1);

        Island island = new Island();
        island.populateTheIsland();
        plantsGrow.scheduleAtFixedRate(new PlantsGrowTask(island), 1, 100, TimeUnit.MILLISECONDS);
        statistics.scheduleAtFixedRate(new StatisticsTask(island), 1, 3000, TimeUnit.MILLISECONDS);
        animalsLife.scheduleAtFixedRate(new AnimalsLifeCycleTask(island), 1, 2000, TimeUnit.MILLISECONDS);
    }
}
