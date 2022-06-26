import livingobjects.Animal;
import livingobjects.Plant;
import livingobjects.Utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class StatisticsTask implements Runnable{
    private Island island;

    public StatisticsTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {

        ConcurrentMap<String,Long> animalsMap = island.getAnimalsOnIsland().
                values().
                parallelStream().
                flatMap(CopyOnWriteArrayList::stream).
                collect(Collectors.groupingByConcurrent(Animal::getSimpleClassName,Collectors.counting()));

        ConcurrentMap<String,Long> plantsMap = island.getPlantsOnIsland().
                values().
                parallelStream().
                flatMap(CopyOnWriteArrayList::stream).
                collect(Collectors.groupingByConcurrent(Plant::getSimpleClassName,Collectors.counting()));

        StringBuilder result = new StringBuilder();

        for (ConcurrentMap.Entry<String,Long> entry:animalsMap.entrySet()) {
            result.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }





        for (ConcurrentMap.Entry<String,Long> entry:plantsMap.entrySet()) {
            result.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }

        if (Utils.oldAnimalsPopulation.equals(""))
            Utils.oldAnimalsPopulation = result.toString();
        else {
            if(Utils.oldAnimalsPopulation.equals(result.toString()))
                System.out.println("Зациклилось!");
        }

        System.out.println("Island statistic:\n" + result.toString());
    }
}
