package com.jp.graphs.client;

import com.jp.graphs.core.GridGraphBuilder;
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

/**
 * Created by JP on 3/7/2017.
 */
public class VisualMain extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisualMain.class);

    private final static int SCENE_HEIGHT = 200;
    private final static int SCENE_WIDTH = 200;
    private final static int NODE_DIMENSION = 20;

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
        graph = GridGraphBuilder.build(SCENE_HEIGHT, SCENE_WIDTH, NODE_DIMENSION);

        // Draw a rectangle for each vertex.  Also setting the data element of the vertex to the rectangle.  Not sure why
        // right now but it might be useful later??
        graph.getVertices().forEach(v -> {
            GridVertex gv = (GridVertex) v;
            Rectangle r = new Rectangle(gv.getX(), gv.getY(), NODE_DIMENSION, NODE_DIMENSION);
            r.setFill(Color.BEIGE);
            r.setStroke(Color.BLACK);
            gv.setDataElement(r);
            root.getChildren().add(r);
        });

        LOGGER.info("{}", graph);
        LOGGER.info("{}", graph.getVertices().stream().findAny().map(v -> v.toString() + v.printNeighbors()));

        primaryStage.show();


    }

}
