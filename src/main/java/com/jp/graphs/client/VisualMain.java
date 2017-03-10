package com.jp.graphs.client;

import com.jp.graphs.core.GridGraphFactory;
import com.jp.graphs.core.GridVertex;
import com.jp.graphs.search.AStar;
import com.jp.graphs.search.BreadthFirstSearch;
import com.jp.graphs.search.DepthFirstSearch;
import com.jp.graphs.stereotypes.Graph;
import com.jp.graphs.stereotypes.GraphFactory;
import com.jp.graphs.stereotypes.Search;
import com.jp.graphs.stereotypes.Vertex;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Graphs are more fun when you can see them in action.  This is my attempt at making that happen
 * <p>
 * Created by JP on 3/7/2017.
 */
public class VisualMain extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisualMain.class);

    private static final int GRID_HEIGHT = 36;
    private static final int GRID_WIDTH = 84;

    private static final int NODE_DIMENSION = 20;
    private static final int SCENE_HEIGHT = GRID_HEIGHT * NODE_DIMENSION;
    private static final int SCENE_WIDTH = GRID_WIDTH * NODE_DIMENSION;

    private static Random rand = new Random();


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Accepts a Search implementation and a graph to apply it to
     *
     * @param searchAlgorithm
     * @param graph
     */
    private Vertex findPath(Search searchAlgorithm, Graph graph) {

        // Arbitrarily choose a start and end
        int startX;
        int startY;
        int endX;
        int endY;
        do {
            startX = rand.nextInt(GRID_WIDTH);
            startY = rand.nextInt(GRID_HEIGHT);
            endX = rand.nextInt(GRID_WIDTH);
            endY = rand.nextInt(GRID_HEIGHT);
        } while (startX == endX && startY == endY);
        final int fStartX = startX;
        final int fStartY = startY;
        final int fEndX = endX;
        final int fEndY = endY;

        Vertex startVertex = new GridVertex(fStartX, fStartY);
        LOGGER.info("Start should be : {}", startVertex);
        Vertex endVertex = new GridVertex(fEndX, fEndY);
        LOGGER.info("End should be : {}", endVertex);

        // Coordinates selected, so let's pull the two vertices from the graph so we can pass them to the search algorithm
        Vertex start = graph.getVertices().stream().filter(v -> {
            GridVertex gv = (GridVertex) v;
            return gv.getX() == fStartX && gv.getY() == fStartY;
        }).findFirst().orElse(null);
        ((Rectangle) start.getDataElement()).setFill(Color.GREEN);
        ((GridVertex) start).setRestricted(false);

        Vertex end = graph.getVertices().stream().filter(v -> {
            GridVertex gv = (GridVertex) v;
            return gv.getX() == fEndX && gv.getY() == fEndY;
        }).findFirst().orElse(null);
        ((Rectangle) end.getDataElement()).setFill(Color.RED);
        ((GridVertex) end).setRestricted(false);

        graph.getVertices().forEach(v->((GridVertex)v).calculateHeuristic(end));

        // Do the actual search
        Vertex result = searchAlgorithm.search(start, end);

        LOGGER.info("Actual end was : {}", result);
        return result;
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
        graph.getVertices().forEach(v -> {
            GridVertex gv = (GridVertex) v;

            Rectangle r = new EquatableRectangle(gv.getX() * NODE_DIMENSION, gv.getY() * NODE_DIMENSION, NODE_DIMENSION, NODE_DIMENSION);

            r.setFill(Color.rgb(100,  100, rand.nextInt(25) + 100));
            r.setStroke(Color.LIGHTGREY);

            if (((GridVertex) v).isRestricted()) {
                r.setFill(Color.BLACK);
            }

            gv.setDataElement(r);
            root.getChildren().add(r);
        });

        primaryStage.show();

        /*
        The graph is drawn so let's try to do some path-finding
         */
        Search searchAlgorithm = new AStar();
        Vertex result = findPath(searchAlgorithm, graph);

        // Let's trace what happened
        Queue<Vertex> trace = new ArrayBlockingQueue(searchAlgorithm.getVisitedNodes().size());
        searchAlgorithm.getVisitedNodes().forEach(v -> {
            Vertex vt = (Vertex) v;
            trace.offer(vt);
        });


//        Timeline timeline = new Timeline(10);
//        timeline.setCycleCount(1);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Vertex currentSpot = trace.poll();
                if (currentSpot == null) {
                    stop();
                } else {
                    ((Rectangle) currentSpot.getDataElement()).setFill(Color.DARKGREY);
                }


            }
        };


//        timeline.play();
        timer.start();

        if(result!=null){

            GridVertex r = (GridVertex) result;
            Vertex prev = result.getParent();
            List<LineTo> lines = new ArrayList<>();
            lines.add(new LineTo(r.getX(), r.getY()));

            Path path = new Path();
            path.getElements().add(new MoveTo(r.getX()*NODE_DIMENSION,r.getY()*NODE_DIMENSION));

            while(prev!=null && (prev = prev.getParent())!=null){
                path.getElements().add(new LineTo(((GridVertex) prev).getX()*NODE_DIMENSION,((GridVertex) prev).getY()*NODE_DIMENSION));
            }
            root.getChildren().add(path);
        }

    }

}
