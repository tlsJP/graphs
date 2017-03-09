package com.jp.graphs.client;

import com.jp.graphs.core.AdjacencyListGraph;
import com.jp.graphs.stereotypes.GraphFactory;
import com.jp.graphs.core.GridGraphFactory;
import com.jp.graphs.stereotypes.Graph;
import com.jp.graphs.stereotypes.SimpleVertex;
import com.jp.graphs.stereotypes.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Runs all the thingies
 * <p>
 * Created by JP on 3/3/2017.
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Graph g = new AdjacencyListGraph();


        Vertex a = new SimpleVertex(1){};
        Vertex b = new SimpleVertex(2){};
        Vertex c = new SimpleVertex(3){};

        g.add(a);
        g.add(b);
        g.add(c);

        g.connect(a, b);
        g.connect(c, a);
        g.connect(c, b);
        LOGGER.info("a~b ? {}" , g.isAdjacent(a, b));
        LOGGER.info("A neighbors : " + a.printNeighbors());
        LOGGER.info("B neighbors : " + b.printNeighbors());
        LOGGER.info("C neighbors : " + c.printNeighbors());


        g.disconnect(a, b);
        LOGGER.info("a~b ? {}" , g.isAdjacent(a, b));
        LOGGER.info("A neighbors : " + a.printNeighbors());
        LOGGER.info("B neighbors : " + b.printNeighbors());
        LOGGER.info("C neighbors : " + c.printNeighbors());

        g.remove(a);
        LOGGER.info("B neighbors : " + b.printNeighbors());
        LOGGER.info("C neighbors : " + c.printNeighbors());


        LOGGER.info("graph = {}", g);


        GraphFactory factory = new GridGraphFactory();
        Graph g2 = factory.build(3,4);
        LOGGER.info("graph = {}", g2);

    }

}
