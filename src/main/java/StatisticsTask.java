import javafx.scene.control.TextField;
import livingobjects.Animal;
import livingobjects.Plant;
import livingobjects.Utils;


import java.util.LinkedHashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class StatisticsTask implements Runnable{
    private Island island;
    private ConcurrentHashMap<String, TextField> animalsOnIslandForGUI;

    public StatisticsTask(Island island, ConcurrentHashMap<String, TextField> animalsOnIslandForGUI) {
        this.island = island;
        this.animalsOnIslandForGUI = animalsOnIslandForGUI;
    }

    @Override
    public void run() {
        printCountStatisticToConsole();
        setTextFieldsIconsOnGUI();
    }


    // печатает статистику в консоль
    private void printCountStatisticToConsole(){
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

        System.out.println("Island statistic:\n" + result.toString());
    }


    private void setTextFieldsIconsOnGUI() {
        for (int i = 0; i < Utils.xIslandSize; i++) {
            for (int j = 0; j < Utils.yIslandSize; j++) {
                String cell = i+"|"+j;
                String iconsInCell = getIconsStringForTextFieldByCell(cell);
                this.animalsOnIslandForGUI.get(cell).setText(iconsInCell);
            }
        }
    }


    // возвращает иконку строку иконок для ячейки
    private String getIconsStringForTextFieldByCell(String cell) {
        ConcurrentHashMap<String,CopyOnWriteArrayList<Animal>> animalsOnIsland = island.getAnimalsOnIsland();
        CopyOnWriteArrayList<Animal> animalsOnCell = animalsOnIsland.get(cell);
        TreeSet<String> animalsIcons = new TreeSet<>();

        for (Animal animal:animalsOnCell) {
            int id = animal.getId();
            String iconOfAnimal = Utils.iconsOfAnimlsAndPlants.get(id);
            animalsIcons.add(iconOfAnimal);
        }

        String resultString = "";
        for (String curentIcon:animalsIcons) {
            resultString = resultString + curentIcon;
        }

        return resultString;
    }
}
