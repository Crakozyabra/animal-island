package livingobjects.animals;

import livingobjects.Animal;

public class Herbivore extends Animal {
    public Herbivore(int id, double weight, int maxCountInOneCell, int cellPerMoveSpeedNoMore, double foodForFullSaturation) {
        super(id, weight, maxCountInOneCell, cellPerMoveSpeedNoMore, foodForFullSaturation);
    }
}
