package com.jp.graphs.client;

import com.jp.graphs.core.GraphFactory;
import com.jp.graphs.core.GridGraphFactory;
import com.jp.graphs.core.GridVertex;
import com.jp.graphs.stereotypes.Graph;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by JP on 3/7/2017.
 */
public class VisualMain extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisualMain.class);

    private static final int GRID_HEIGHT = 36;
    private static final int GRID_WIDTH = 84;

    private static final int NODE_DIMENSION = 20;
    private static final int SCENE_HEIGHT = GRID_HEIGHT * NODE_DIMENSION;
    private static final int SCENE_WIDTH = GRID_WIDTH * NODE_DIMENSION;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create the window
        Group root = new Group();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.setTitle(this.getClass().getSimpleName());

        // Use builder to create a graph
        GraphFactory builder = new GridGraphFactory();
        final Graph graph = builder.build(GRID_HEIGHT, GRID_WIDTH);

        // Draw a rectangle for each vertex.  Also setting the data element of the vertex to the rectangle.  Not sure why
        // right now but it might be useful later?
        Random rand = new Random();
        graph.getVertices().forEach(v -> {
            GridVertex gv = (GridVertex) v;
            Rectangle r = new Rectangle(gv.getX() * NODE_DIMENSION, gv.getY() * NODE_DIMENSION, NODE_DIMENSION, NODE_DIMENSION);

            r.setFill(Color.rgb(100, rand.nextInt(25) + 100, rand.nextInt(25) + 100));
            r.setStroke(Color.LIGHTGREY);
            gv.setDataElement(r);
            root.getChildren().add(r);
        });

        primaryStage.show();


    }

}
