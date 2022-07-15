package livingobjects;

public class AnimalFactory {
    public Animal createAnimal(int id){

        Animal animal = null;

        switch (id) {
            case 0:
                animal = new Predator.Wolf();
                break;
            case 1:
                animal = new Predator.Boa();
                break;
            case 2:
                animal = new Predator.Fox();
                break;
            case 3:
                animal =  new Predator.Bear();
                break;
            case 4:
                animal = new Predator.Eagle();
                break;
            case 5:
                animal = new Herbivore.Horse();
                break;
            case 6:
                animal = new Herbivore.Deer();
                break;
            case 7:
                animal =  new Herbivore.Rabbit();
                break;
            case 8:
                animal = new Herbivore.Mouse();
                break;
            case 9:
                animal = new Herbivore.Goat();
                break;
            case 10:
                animal = new Herbivore.Sheep();
                break;
            case 11:
                animal = new Herbivore.Boar();
                break;
            case 12:
                animal = new Herbivore.Buffalo();
                break;
            case 13:
                animal = new Herbivore.Duck();
                break;
            case 14:
                animal = new Herbivore.Caterpillar();
                break;
        }

        return animal;
    };

}
