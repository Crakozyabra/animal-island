package livingobjects;

import livingobjects.animals.Herbivore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    public boolean mark;
    private final int id; // id
    private final double weight; // вес
    private final int maxCountInOneCell; // максимальное кол-во животных этого типа в ячейке
    private final int cellPerMoveSpeedNoMore; // скорость - не больше кол-ва клеток за ход
    private final double foodForFullSaturation; // кол-во еды для насыщения
    private double currentSaturation; // текущий уровень насыщения

    private boolean animalIsDead;

    private boolean animalAlreadyMoved;

    public boolean isAnimalAlreadyMoved() {
        return animalAlreadyMoved;
    }

    public void setAnimalAlreadyMoved(boolean animalAlreadyMoved) {
        this.animalAlreadyMoved = animalAlreadyMoved;
    }

    public boolean isAnimalIsDead() {
        return animalIsDead;
    }

    public void setAnimalIsDead(boolean animalIsDead) {
        this.animalIsDead = animalIsDead;
    }

    public Animal(int id, double weight, int maxCountInOneCell, int cellPerMoveSpeedNoMore, double foodForFullSaturation) {
        this.id = id;
        this.weight = weight;
        this.maxCountInOneCell = maxCountInOneCell;
        this.cellPerMoveSpeedNoMore = cellPerMoveSpeedNoMore;
        this.foodForFullSaturation = foodForFullSaturation;
        this.currentSaturation = currentSaturation;
    }
    public boolean isFullSaturation() {
        return this.foodForFullSaturation - this.currentSaturation < 1.0;
    }
    public void toSaturate(Animal anotherAnimal) {
        this.currentSaturation += anotherAnimal.getWeight();
        anotherAnimal.setAnimalIsDead(true);
    }
    public void toSaturate(Plant plant) {
        this.currentSaturation += plant.getWeight();
    }
    public String getSimpleClassName() {
        return this.getClass().getSimpleName();
    }

    public Direction chooseDirectionOfTravel(){
        int direction = ThreadLocalRandom.current().nextInt(1,5);
        Direction directionEnum = null;
        switch (direction) {
            case 1:
                directionEnum = Direction.RIGHT;
                break;
            case 2:
                directionEnum = Direction.UP;
                break;
            case 3:
                directionEnum = Direction.LEFT;
                break;
            case 4:
                directionEnum = Direction.DOWN;
                break;
            default:
                directionEnum = Direction.RIGHT;
                break;
        }
        return directionEnum;
    }

    public int stepCount() {
        int cellPerMoveSpeedNoMore = this.getCellPerMoveSpeedNoMore();
        int maxRandomCellPerMoveSpeedNoMoreForAllAnimal = ThreadLocalRandom.current().nextInt(0, 5);
        if (maxRandomCellPerMoveSpeedNoMoreForAllAnimal <= cellPerMoveSpeedNoMore)
            return maxRandomCellPerMoveSpeedNoMoreForAllAnimal;
        return 0;
    }

    public void eat(Animal anotherAnimal){
        // пробует съесть животное
        if (anotherAnimal == null) return;
        if (this.equals(anotherAnimal)) return;
        if (this.canBeEaten(anotherAnimal) && !this.isFullSaturation()) {
            this.toSaturate(anotherAnimal);
        }
    }

    public void eat(List<Plant> plants){
        if (plants != null && this instanceof Herbivore && plants.size() > 0) {
            this.toSaturate(plants.get(0));
            plants.remove(0);
        }
    }

    public CopyOnWriteArrayList<Animal> multiply(Animal anotherAnimal){
        if (this.getId() == anotherAnimal.getId() && !this.equals(anotherAnimal)) {
            CopyOnWriteArrayList<Animal> children = new CopyOnWriteArrayList<>();
            AnimalFactory animalFactory = new AnimalFactory();
            for (int i = 0; i < Utils.childQuanity.get(id); i++) {
                children.add(animalFactory.createAnimal(this.getId()));
            }
            return children;
        }
        return null;
    }

    private boolean canBeEaten(Animal anotherAnimal){
        int probability = Utils.probability[this.getId()][anotherAnimal.getId()];
        int factProbability = ThreadLocalRandom.current().nextInt(0,100);
        return probability > factProbability;
    }
    public double getWeight() {
        return weight;
    }

    public int getMaxCountInOneCell() {
        return maxCountInOneCell;
    }

    public int getCellPerMoveSpeedNoMore() {
        return cellPerMoveSpeedNoMore;
    }

    public double getFoodForFullSaturation() {
        return foodForFullSaturation;
    }
    public int getId() {
        return id;
    }



}
