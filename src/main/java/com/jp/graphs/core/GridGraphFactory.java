package com.jp.graphs.core;

import com.jp.graphs.stereotypes.Graph;
import com.jp.graphs.stereotypes.GraphFactory;
import com.jp.graphs.stereotypes.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * This will maybe turn into a proper factory or something later.  For now it's just a place to put graph construction things
 * <p>
 * Created by JP on 3/7/2017.
 */
public class GridGraphFactory implements GraphFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(GridGraphFactory.class);

    private static Graph generateGraph(int columns, int rows) {
        Graph graph = new HashTableAdjacencyListGraph();

        // This loop is just to create a vertex for every spot in the grid.
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
        LOGGER.debug("{}", graph);

        return graph;
    }

    private static void establishNodeConnections(int columns, int rows, Graph graph) {
        Random r = new Random();

        // Connect the vertices
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {

                int currentX = i;
                int currentY = j;

                // Find the vertex we are wanting to connect
                LOGGER.debug("current : ({},{})", currentX, currentY);
                final GridVertex currentVertex = (GridVertex) graph.getVertex(new GridVertex(currentX, currentY));

                // Randomly make nodes impassable instead of connecting it to other vertices
                if (r.nextInt(100) < 20) {
                    currentVertex.setRestricted(true);
                }

                // Find vertices that are directly left/right/up/down, and for each of those, establish the connections
                graph.getVertices().stream()
                        .filter(v -> {
                            GridVertex adjacent = (GridVertex) v;

                            boolean isHorizontallyAdjacent = adjacent.getY() == currentVertex.getY() && (adjacent.getX() == currentX + 1 || adjacent.getX() == currentX - 1);
                            boolean isVerticallyAdjacent = adjacent.getX() == currentVertex.getX() && (adjacent.getY() == currentY + 1 || adjacent.getY() == currentY - 1);

                            return (isHorizontallyAdjacent || isVerticallyAdjacent) && !adjacent.isRestricted();
                        })
                        .forEach(gv -> {
                            graph.connect(currentVertex, gv);
                            LOGGER.debug("({},{}) new neighbor : {}", new Object[]{currentVertex.getX(), currentVertex.getY(), gv});
                        });

            }
        }
        LOGGER.info("Done establishing connections.");
    }

    /**
     * Builds a graph of the specified height and width.
     *
     * @param height
     * @param width
     * @return
     */
    public Graph build(int height, int width) {
        LOGGER.info("build({},{})", new Object[]{height, width});

        Graph graph = generateGraph(width, height);

        establishNodeConnections(width, height, graph);

        return graph;

    }
}
