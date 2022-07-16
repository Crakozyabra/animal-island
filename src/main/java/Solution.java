import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import livingobjects.Utils;

import java.util.concurrent.*;

/*
    Author - Golubev Ivan Vladimirovich
    email - mrcrakozyabra@gmail.com
*/

public class Solution extends Application{



    public static void main(String[] args) {
        Application.launch(args);

    }

    public void start(Stage stage) throws Exception {

        GridPane gridPaneAll = new GridPane();
        ScrollPane scrollPane = new ScrollPane();
        ConcurrentHashMap<String,TextField> animalsOnIslandForGUI = new ConcurrentHashMap<>();


        for (int i = -1; i < Utils.xIslandSize; i++) {
            for (int j = -1; j < Utils.yIslandSize; j++) {
                TextField tf = null;

                if (i==-1 && j==-1) {
                    tf = new TextField(String.valueOf("x/y"));
                }

                else if (i == -1 && j!=-1) {
                    tf = new TextField(String.valueOf(j));
                }

                else if (j == -1 && i!=-1) {
                    tf = new TextField(String.valueOf(i));
                }

                else if (i!=-1 && j!=-1){
                    tf = new TextField(Utils.getIconString());
                    animalsOnIslandForGUI.put(i+"|"+j,tf);
                }

                tf.setEditable(false);
                gridPaneAll.add(tf,i+1,j+1);

                scrollPane.setContent(gridPaneAll);
            }
        }

        ScheduledExecutorService plantsGrow = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService animalsLife = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService statistics = Executors.newScheduledThreadPool(1);

        Island island = new Island();
        island.populateTheIsland();
        plantsGrow.scheduleAtFixedRate(new PlantsGrowTask(island), 1, 1000, TimeUnit.MILLISECONDS);
        statistics.scheduleAtFixedRate(new StatisticsTask(island, animalsOnIslandForGUI), 1, 2000, TimeUnit.MILLISECONDS);
        animalsLife.scheduleAtFixedRate(new AnimalsLifeCycleTask(island), 1, 3000, TimeUnit.MILLISECONDS);

        Scene scene = new Scene(scrollPane,1366,768);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Animal island");
        stage.show();
    }
}
