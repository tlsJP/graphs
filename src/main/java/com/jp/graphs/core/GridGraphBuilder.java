package com.jp.graphs.core;

import com.jp.graphs.stereotypes.Graph;
import com.jp.graphs.stereotypes.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

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
     * @param cellSize
     * @return
     */
    public static Graph build(int height, int width, int cellSize) {
        LOGGER.info("build({},{},{})", new Object[]{height, width, cellSize});

        final int columns = width / cellSize;
        final int rows = height / cellSize;

        AdjacencyListGraph graph = new AdjacencyListGraph();

        // This loop is just to create a vertex for every spot in the grid.
        // The cell size is effectively an offset
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                // Create the vertex
                LOGGER.debug("creating : ({},{})", i, j);
                Vertex newVertex = new GridVertex(i * cellSize, j * cellSize);
                graph.add(newVertex);
            }
        }

        LOGGER.info("Done creating graph.");
        LOGGER.info("{}", graph);

        // Connect the vertices
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {

                final int currentX = i;
                final int currentY = j;

                // Find the vertex we are wanting to connect
                LOGGER.debug("current : ({},{})", currentX, currentY);
                final Optional<Vertex> currentVertex = graph.getVertices().stream().filter(v -> {
                    GridVertex gv = (GridVertex) v;
                    return gv.getX() == currentX && gv.getY() == currentY;
                }).findFirst();

                // Find vertices that are directly left/right/up/down, and for each of those, establish the connections
                graph.getVertices().stream().filter(v -> {
                    GridVertex gv = (GridVertex) v;

                    boolean isHorizontallyAdjacent = gv.getY() == currentY && (gv.getX() == currentX + cellSize || gv.getX() == currentX - cellSize);
                    boolean isVerticallyAdjacent = gv.getX() == currentX && (gv.getY() == currentY + cellSize || gv.getY() == currentY - cellSize);

                    return isHorizontallyAdjacent || isVerticallyAdjacent;

                }).forEach(gv -> graph.connect(currentVertex.get(), gv));

            }
        }


        return graph;

    }
}
