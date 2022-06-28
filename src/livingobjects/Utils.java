package livingobjects;

import java.util.concurrent.ConcurrentHashMap;


public class Utils {

    // размер острова
    public static final int xIslandSize = 100;
    public static final int yIslandSize = 20;


    // вероятность съедения строкой столбца
    public static final int [][]probability = {

            {-1,10,10,0,10,30,40,70,90,60,70,20,30,80,0,0},    // id=0  wolf
            {0,-1,0,0,0,0,0,50,90,0,0,0,0,50,90,0},            // id=1  boa
            {0,20,-1,0,10,0,5,70,90,20,20,5,0,80,0,0},         // id=2  fox
            {20,30,20,-1,30,70,80,80,90,70,70,60,75,80,0,0},   // id=3  bear
            {0,50,0,0,-1,0,0,90,90,0,0,0,0,85,20,0},           // id=4  eagle
            {0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,100},              // id=5  horse
            {0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,100},              // id=6  deer
            {0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,100},              // id=7  rabbit
            {0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,100},              // id=8  mouse
            {0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,100},              // id=9  goat
            {0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,100},              // id=10 sheep
            {0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,100},              // id=11 boar
            {0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,100},              // id=12 buffalo
            {0,0,0,0,0,0,0,0,0,0,0,0,0,-1,50,100},             // id=13 duck
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,100},              // id=14 caterpillar
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}                  // id=15 plant
                                                               // sum == 16
    };


    // количество детей у животных разных видов
    public static final ConcurrentHashMap<Integer,Integer> childQuanity = new ConcurrentHashMap<>() {{
        put(0,3);   // id=0  wolf
        put(1,3);   // id=1  boa
        put(2,3);   // id=2  fox
        put(3,3);   // id=3  bear
        put(4,3);   // id=4  eagle
        put(5,3);   // id=5  horse
        put(6,3);   // id=6  deer
        put(7,5);   // id=7  rabbit
        put(8,10);  // id=8  mouse
        put(9,3);   // id=9  goat
        put(10,3);  // id=10 sheep
        put(11,3);  // id=11 boar
        put(12,3);  // id=12 buffalo
        put(13,3);  // id=13 duck
        put(14,10); // id=14 caterpillar
    }};

    // максимальное количество животных определенного типа в ячейке
    public static final ConcurrentHashMap<Integer, Integer> maxCountInOneCell = new ConcurrentHashMap<>() {{
        put(0,30);   // id=0  wolf
        put(1,30);   // id=1  boa
        put(2,30);   // id=2  fox
        put(3,5);    // id=3  bear
        put(4,20);   // id=4  eagle
        put(5,20);   // id=5  horse
        put(6,20);   // id=6  deer
        put(7,150);  // id=7  rabbit
        put(8,500);  // id=8  mouse
        put(9,140);  // id=9  goat
        put(10,140); // id=10 sheep
        put(11,50);  // id=11 boar
        put(12,10);  // id=12 buffalo
        put(13,200); // id=13 duck
        put(14,1000);// id=14 caterpillar
    }};


    // максимальное количество животных определенного типа в ячейке
    public static final ConcurrentHashMap<Integer, Integer> cellPerMoveSpeedNoMore = new ConcurrentHashMap<>() {{
        put(0,30);   // id=0  wolf
        put(1,30);   // id=1  boa
        put(2,30);   // id=2  fox
        put(3,5);    // id=3  bear
        put(4,20);   // id=4  eagle
        put(5,20);   // id=5  horse
        put(6,20);   // id=6  deer
        put(7,150);  // id=7  rabbit
        put(8,500);  // id=8  mouse
        put(9,140);  // id=9  goat
        put(10,140); // id=10 sheep
        put(11,50);  // id=11 boar
        put(12,10);  // id=12 buffalo
        put(13,200); // id=13 duck
        put(14,1000);// id=14 caterpillar
    }};

}
