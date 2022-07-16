import livingobjects.Plant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class PlantsGrowTask implements Runnable{
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Plant>> plantsOnIsland;

    public PlantsGrowTask(Island island) {
        this.plantsOnIsland = island.getPlantsOnIsland();
    }

    @Override
    public void run() {
        for (Map.Entry<String, CopyOnWriteArrayList<Plant>> entry: plantsOnIsland.entrySet()) {
            List<Plant> plants = entry.getValue();
                if (plants.size() < 200) {
                    plants.add(new Plant());
                }
        }
    }


}
