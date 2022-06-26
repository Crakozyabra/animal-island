import livingobjects.animals.predators.*;

import java.util.ArrayList;
import java.util.List;
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
        plantsGrow.scheduleAtFixedRate(new PlantsGrowTask(island), 1, 100, TimeUnit.MILLISECONDS);//1000
        statistics.scheduleAtFixedRate(new StatisticsTask(island), 1, 300, TimeUnit.MILLISECONDS);//3000
        animalsLife.scheduleAtFixedRate(new AnimalsLifeCycleTask(island), 1, 200, TimeUnit.MILLISECONDS);//2000
    }
}
