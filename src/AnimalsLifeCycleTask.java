import livingobjects.*;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class AnimalsLifeCycleTask implements Runnable{

    private Island island;

    public AnimalsLifeCycleTask(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        for (Integer i = 0; i < Utils.xIslandSize; i++) {
            for (Integer j = 0; j < Utils.yIslandSize; j++) {
                animalsEatInIslandCell(i, j);
                animalsMultiplyInIslandCell(i, j);
                animalsMoveFromCell(i, j);
            }
        }
    }

    // животные едят в ячейке острова
    private void animalsEatInIslandCell(Integer xCoordinate, Integer yCoordinate){
        //System.out.println("start eat");
        CopyOnWriteArrayList<Animal> animals = island.getAnimalsOnIslandInCell(xCoordinate+"|"+yCoordinate);
        CopyOnWriteArrayList<Plant> plants = island.getPlantsOnIslandInCell(xCoordinate+"|"+yCoordinate);
        for (Animal currentAnimal:animals) {

            if (currentAnimal == null) continue;

            for (Animal anotherAnimal:animals) {
                if (!currentAnimal.isAnimalIsDead() && !anotherAnimal.isAnimalIsDead()) {
                    currentAnimal.eat(anotherAnimal);
                    currentAnimal.eat(plants);
                }
            }
        }
        animals.removeIf(Animal::isAnimalIsDead);
        //System.out.println("end eat");
    }

    private boolean canBeEaten(Animal whoEat, Animal eat){
        int probability = Utils.probability[whoEat.getId()][eat.getId()];
        int factProbability = ThreadLocalRandom.current().nextInt(0,100);
        return probability > factProbability;
    }

    // животные размножаются в ячейке острова
    private void animalsMultiplyInIslandCell(Integer xCoordinate, Integer yCoordinate){

        //System.out.println("start multiply");
        CopyOnWriteArrayList<Animal> animals = island.getAnimalsOnIslandInCell(xCoordinate+"|"+yCoordinate);
        CopyOnWriteArrayList<Animal> animalsChildren = new CopyOnWriteArrayList<>();
        for (Animal firstParent:animals) {
            for (Animal secondParent: animals) {
                CopyOnWriteArrayList<Animal> children = firstParent.multiply(secondParent);
                if (children != null && !children.isEmpty())
                    animalsChildren.addAll(children);
            }
        }
        animals.addAll(animalsChildren);
        trimToAnimalsCellLimit(animals);
        //System.out.println("end multiply");
    }

    private CopyOnWriteArrayList<Animal> getAnimalsChildren(int id) {
        CopyOnWriteArrayList<Animal> animalsChildren = new CopyOnWriteArrayList<>();
        AnimalFactory animalFactory = new AnimalFactory();
        return animalsChildren;
    }

    private void trimToAnimalsCellLimit(CopyOnWriteArrayList<Animal> animals){
        for (int id = 0; id < Utils.probability.length - 1; id++) {
            trimToConcreteAnimalsCellLimit(id, animals);
        }
    }

    private void trimToConcreteAnimalsCellLimit(int id, CopyOnWriteArrayList<Animal> animals){
        long animalsIdCount = animals.stream().filter(e->e.getId()==id).count();
        long animalsIdMaxCount = Utils.maxCountInOneCell.get(id);
        long animalsDifferenceCount = animalsIdCount - animalsIdMaxCount;
        for (Animal animal:animals) {
            if (animalsDifferenceCount==0) break;
            if (animal.getId() == id && animalsDifferenceCount > 0) animals.remove(animal);
            animalsDifferenceCount--;
        }
    }

    // животные передвигаются на другие ячейки острова
    private void animalsMoveFromCell(Integer xCoordinate, Integer yCoordinate) {
        //System.out.println("start move");
        CopyOnWriteArrayList<Animal> animalsInOldCell = island.getAnimalsOnIslandInCell(xCoordinate+"|"+yCoordinate);

        for (Animal animal:animalsInOldCell) {
            Direction direction = animal.chooseDirectionOfTravel();
            int stepCount = animal.stepCount();
            String newCell = newCellCoordinate(direction, stepCount, xCoordinate, yCoordinate);


            if (!newCell.equals(xCoordinate+"|"+yCoordinate)) {
                CopyOnWriteArrayList<Animal> animalsInNewCell = island.getAnimalsOnIslandInCell(newCell);
                animalsInNewCell.add(animal);
                animalsInOldCell.remove(animal);
            }
        }
        //System.out.println("end move");
    }

    private String newCellCoordinate(Direction direction, int stepCount, int xCoordinate, int yCoordinate) {
        String oldCell = xCoordinate+"|"+yCoordinate;

        switch (direction) {
            case RIGHT:
                xCoordinate = xCoordinate + stepCount >= 0?xCoordinate + stepCount:xCoordinate;
                break;
            case UP:
                yCoordinate = yCoordinate + stepCount >= 0?yCoordinate + stepCount:yCoordinate;
                break;
            case LEFT:
                xCoordinate = xCoordinate - stepCount >=0?xCoordinate - stepCount:xCoordinate;
                break;
            case DOWN:
                yCoordinate = yCoordinate - stepCount>=0?yCoordinate - stepCount:yCoordinate;
                break;
            default:
                yCoordinate = yCoordinate - stepCount>=0?yCoordinate - stepCount:yCoordinate;
                break;
        }

        if (xCoordinate < Utils.xIslandSize && xCoordinate >=0 && yCoordinate < Utils.yIslandSize && yCoordinate >= 0)
            return xCoordinate + "|" + yCoordinate;
        return oldCell;
    }


}
