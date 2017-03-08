package com.jp.graphs.client;

import com.jp.graphs.core.GridGraphBuilder;
import com.jp.graphs.core.GridVertex;
import com.jp.graphs.stereotypes.Graph;
import com.jp.graphs.stereotypes.Vertex;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JP on 3/7/2017.
 */
public class VisualMain extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisualMain.class);

    private static final int GRID_HEIGHT = 9;
    private static final int GRID_WIDTH = 21;

    private static final int NODE_DIMENSION = 30;
    private static final int SCENE_HEIGHT = GRID_HEIGHT * NODE_DIMENSION;
    private static final int SCENE_WIDTH = GRID_WIDTH * NODE_DIMENSION;

    private Graph graph;

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
        graph = GridGraphBuilder.build(GRID_HEIGHT, GRID_WIDTH);

        // Draw a rectangle for each vertex.  Also setting the data element of the vertex to the rectangle.  Not sure why
        // right now but it might be useful later??
        graph.getVertices().forEach(v -> {
            GridVertex gv = (GridVertex) v;
            Rectangle r = new Rectangle(gv.getX() * NODE_DIMENSION, gv.getY() * NODE_DIMENSION, NODE_DIMENSION, NODE_DIMENSION);
            r.setFill(Color.BEIGE);
            r.setStroke(Color.BLACK);
            gv.setDataElement(r);
            root.getChildren().add(r);
        });

        primaryStage.show();


    }

}
