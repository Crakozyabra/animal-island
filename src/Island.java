import livingobjects.Animal;
import livingobjects.AnimalFactory;
import livingobjects.Plant;
import livingobjects.Utils;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Island {
    private ConcurrentHashMap<String, CopyOnWriteArrayList<Animal>> animalsOnIsland = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, CopyOnWriteArrayList<Plant>> plantsOnIsland = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String,CopyOnWriteArrayList<Animal>> getAnimalsOnIsland() {
        return animalsOnIsland;
    }
    public ConcurrentHashMap<String,CopyOnWriteArrayList<Plant>> getPlantsOnIsland() {
        return plantsOnIsland;
    }
    public CopyOnWriteArrayList<Animal> getAnimalsOnIslandInCell(String cell) {
        return animalsOnIsland.get(cell);
    }
    public CopyOnWriteArrayList<Plant> getPlantsOnIslandInCell(String cell) {
        return plantsOnIsland.get(cell);
    }
    public void setAnimalsOnIslandInCell(String cell, CopyOnWriteArrayList<Animal> animals) {
        animalsOnIsland.put(cell, animals);
    }

    public void setPlantsOnIslandInCell(String cell, CopyOnWriteArrayList<Plant> plants) {
        plantsOnIsland.put(cell, plants);
    }

    public void populateTheIsland() {
        AnimalFactory animalFactory = new AnimalFactory();
        int animalId = 0;
        for (int i = 0; i < Utils.xIslandSize; i++) {
            for (int j = 0; j < Utils.yIslandSize; j++) {
                CopyOnWriteArrayList<Animal> animals = animalsOnIsland.get(i + "|" + j);
                animalId = ThreadLocalRandom.current().nextInt(0,Utils.probability.length-1);
                animals.add(animalFactory.createAnimal(animalId));
            }
        }
    }

    public Island() {
        for (int i = 0; i < Utils.xIslandSize; i++) {
            for (int j = 0; j < Utils.yIslandSize; j++) {
                animalsOnIsland.put(i + "|" + j, new CopyOnWriteArrayList<>());
                plantsOnIsland.put(i + "|" + j, new CopyOnWriteArrayList<>());
            }
        }

    }
}
