package com.jp.graphs.search;

import com.jp.graphs.stereotypes.Search;
import com.jp.graphs.stereotypes.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by JP on 3/8/2017.
 */
public class BreadthFirstSearch implements Search {

    private static final Logger LOGGER = LoggerFactory.getLogger(BreadthFirstSearch.class);

    private Collection<Vertex> visitedNodes = new ArrayList<>();
    private Queue<Vertex> uncheckedNodes = new ArrayBlockingQueue<>(500);

    private Vertex doSearch(Vertex vertex, Vertex target) {
        visitedNodes.add(vertex);

        if (vertex.equals(target)) {
            return vertex;
        }

        vertex.getNeighbors().stream().filter(n -> !uncheckedNodes.contains(n) && !visitedNodes.contains(n)).forEach(v -> uncheckedNodes.add((Vertex) v));

        Vertex next = uncheckedNodes.poll();
        return next == null ? null : doSearch(next, target);
    }

    public Queue<Vertex> getUncheckedNodes() {
        return uncheckedNodes;
    }

    @Override
    public Collection<Vertex> getVisitedNodes() {
        return visitedNodes;
    }

    /**
     * @param start
     * @param target
     * @return
     */
    @Override
    public Vertex search(Vertex start, Vertex target) {
        uncheckedNodes.clear();
        visitedNodes.clear();

        return doSearch(start, target);
    }

}
