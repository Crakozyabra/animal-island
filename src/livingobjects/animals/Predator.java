package livingobjects.animals;

import livingobjects.Animal;

public class Predator extends Animal {
    public Predator(int id, double weight, int maxCountInOneCell, int cellPerMoveSpeedNoMore, double foodForFullSaturation) {
        super(id, weight, maxCountInOneCell, cellPerMoveSpeedNoMore, foodForFullSaturation);
    }
}
