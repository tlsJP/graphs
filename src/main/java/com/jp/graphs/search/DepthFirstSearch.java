package com.jp.graphs.search;

import com.jp.graphs.stereotypes.Search;
import com.jp.graphs.stereotypes.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

/**
 * Created by JP on 3/9/2017.
 */
public class DepthFirstSearch implements Search {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepthFirstSearch.class);

    private Stack<Vertex> uncheckedNodes = new Stack<>();
    private Collection<Vertex> visitedNodes = new ArrayList<>();

    private Vertex doSearch(Vertex root, Object target) {
        visitedNodes.add(root);

        Object cv = root.getDataElement();
        if (cv.equals(target)) {
            return root;
        }

        root.getNeighbors().stream().filter(n -> !uncheckedNodes.contains(n) && !visitedNodes.contains(n)).forEach(v -> uncheckedNodes.push((Vertex) v));

        Vertex next = null;
        if (!uncheckedNodes.isEmpty()) {
            next = uncheckedNodes.pop();
        }

        return next == null ? null : doSearch(next, target);
    }

    @Override
    public Collection getVisitedNodes() {
        return visitedNodes;
    }

    @Override
    public Vertex search(Vertex start, Object target) {
        uncheckedNodes.clear();
        visitedNodes.clear();

        return doSearch(start, target);
    }
}
