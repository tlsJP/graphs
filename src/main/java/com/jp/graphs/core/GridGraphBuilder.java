package com.jp.graphs.core;

import com.jp.graphs.stereotypes.Graph;
import com.jp.graphs.stereotypes.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This will maybe turn into a proper factory or something later.  For now it's just a place to put graph construction things
 * <p>
 * Created by JP on 3/7/2017.
 */
public class GridGraphBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(GridGraphBuilder.class);

    /**
     * Builds a graph of the specified height and width.
     *
     * @param height
     * @param width
     * @return
     */
    public static Graph build(int height, int width) {
        LOGGER.info("build({},{})", new Object[]{height, width});

        final int columns = width;
        final int rows = height;

        AdjacencyListGraph graph = new AdjacencyListGraph();

        // This loop is just to create a vertex for every spot in the grid.
        // The cell size is effectively an offset
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                // Create the vertex
                LOGGER.debug("creating : ({},{})", i, j);
                Vertex newVertex = new GridVertex(i, j);
                graph.add(newVertex);
                LOGGER.debug("created : {}", newVertex.printNeighbors());
            }
        }

        LOGGER.info("Done creating graph.");
        LOGGER.info("{}", graph);

        // Connect the vertices
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {

                int currentX = i;
                int currentY = j;

                // Find the vertex we are wanting to connect
                LOGGER.debug("current : ({},{})", currentX, currentY);
                GridVertex currentVertex = (GridVertex) graph.getVertices().stream().filter(v -> {
                    GridVertex gv = (GridVertex) v;
                    return gv.getX() == currentX && gv.getY() == currentY;
                }).findFirst().get();

                // Find vertices that are directly left/right/up/down, and for each of those, establish the connections
                graph.getVertices().stream().filter(v -> {
                    GridVertex adjacent = (GridVertex) v;

                    boolean isHorizontallyAdjacent = adjacent.getY() == currentVertex.getY() && (adjacent.getX() == currentX + 1 || adjacent.getX() == currentX - 1);
                    boolean isVerticallyAdjacent = adjacent.getX() == currentVertex.getX() && (adjacent.getY() == currentY + 1 || adjacent.getY() == currentY - 1);

                    return isHorizontallyAdjacent || isVerticallyAdjacent;

                }).forEach(gv -> {
                    graph.connect(currentVertex, gv);
                    LOGGER.debug("({},{}) new neighbor : {}", new Object[]{currentVertex.getX(), currentVertex.getY(), gv});
                });

                LOGGER.info("({},{}) now neighbors : {}", new Object[]{currentVertex.getX(), currentVertex.getY(), currentVertex.printNeighbors()});

            }
        }

        LOGGER.info("Done establishing connections.");
        LOGGER.info("{}", graph);

        return graph;

    }
}
