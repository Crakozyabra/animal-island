package livingobjects;

public abstract class Predator extends Animal {
    public Predator(int id, double weight, int maxCountInOneCell, int cellPerMoveSpeedNoMore, double foodForFullSaturation) {
        super(id, weight, maxCountInOneCell, cellPerMoveSpeedNoMore, foodForFullSaturation);
    }

    public static class Bear extends Predator {
        public Bear() {
            super(3, 500, 5, 2, 80);
        }
    }

    public static class Boa extends Predator {
        public Boa() {
            super(1, 15, 30, 1, 3);
        }
    }

    public static class Eagle extends Predator {
        public Eagle() {
            super(4, 6, 20, 3, 1);
        }
    }

    public static class Fox extends Predator {
        public Fox() {
            super(2, 8, 30, 2, 2);
        }
    }

    public static class Wolf extends Predator {
        public Wolf() {
            super(0, 50, 30, 3, 8);
        }
    }
}
