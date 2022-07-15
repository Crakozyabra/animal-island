package livingobjects;

public abstract class Herbivore extends Animal {
    public Herbivore(int id, double weight, int maxCountInOneCell, int cellPerMoveSpeedNoMore, double foodForFullSaturation) {
        super(id, weight, maxCountInOneCell, cellPerMoveSpeedNoMore, foodForFullSaturation);
    }

    public static class Boar extends Herbivore {
        public Boar() {
            super(11, 400, 50, 2, 50);
        }
    }

    public static class Buffalo extends Herbivore {
        public Buffalo() {
            super(12, 700.0, 10, 3, 100);
        }
    }

    public static class Caterpillar extends Herbivore {
        public Caterpillar() {
            super(14, 0.01, 1000, 0, 0);
        }
    }

    public static class Deer extends Herbivore {
        public Deer() {
            super(6, 300, 20, 4, 50);
        }
    }

    public static class Duck extends Herbivore {
        public Duck() {
            super(13, 1, 200, 4, 0.15);
        }
    }

    public static class Goat extends Herbivore {
        public Goat() {
            super(9, 60, 140, 3, 10);
        }
    }

    public static class Horse extends Herbivore {
        public Horse() {
            super(5, 400, 20, 4, 60);
        }
    }

    public static class Mouse extends Herbivore {
        public Mouse() {
            super(8, 0.05, 500, 1, 0.01);
        }
    }

    public static class Rabbit extends Herbivore {
        public Rabbit() {
            super(7, 2, 150, 2, 0.45);
        }
    }

    public static class Sheep extends Herbivore {
        public Sheep() {
            super(10, 70, 140, 3, 15);
        }
    }
}
