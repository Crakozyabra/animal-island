package livingobjects;

import livingobjects.Animal;
import livingobjects.animals.herbivores.*;
import livingobjects.animals.predators.*;

public class AnimalFactory {
    public Animal createAnimal(int id){

        Animal animal = null;

        switch (id) {
            case 0:
                animal = new Wolf();
                break;
            case 1:
                animal = new Boa();
                break;
            case 2:
                animal = new Fox();
                break;
            case 3:
                animal =  new Bear();
                break;
            case 4:
                animal = new Eagle();
                break;
            case 5:
                animal = new Horse();
                break;
            case 6:
                animal = new Deer();
                break;
            case 7:
                animal =  new Rabbit();
                break;
            case 8:
                animal = new Mouse();
                break;
            case 9:
                animal = new Goat();
                break;
            case 10:
                animal = new Sheep();
                break;
            case 11:
                animal = new Boar();
                break;
            case 12:
                animal = new Buffalo();
                break;
            case 13:
                animal = new Duck();
                break;
            case 14:
                animal = new Caterpillar();
                break;
        }

        return animal;
    };

}
