package com.jp.graphs.client;

import com.jp.graphs.core.AdjacencyListGraph;
import com.jp.graphs.core.SimpleVertex;
import com.jp.graphs.stereotypes.Graph;
import com.jp.graphs.stereotypes.Vertex;

import java.util.stream.Collectors;

/**
 * Runs all the thingies
 * <p>
 * Created by JP on 3/3/2017.
 */
public class Main {

    public static void main(String[] args) {

        Graph g = new AdjacencyListGraph();


        Vertex a = new SimpleVertex(1);
        Vertex b = new SimpleVertex(2);

        g.add(a);
        g.add(b);

        g.connect(a, b);
        System.out.println("a~b ? : " + g.isAdjacent(a, b));

        g.disconnect(a, b);
        System.out.println("a~b ? : " + g.isAdjacent(a, b));


        System.out.println(g.toString());
    }
}
