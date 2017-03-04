package com.jp.graphs.core;

import com.jp.graphs.stereotypes.Graph;
import com.jp.graphs.stereotypes.Vertex;

/**
 * TODO
 *
 * Created by JP on 3/3/2017.
 */
public class AdjacencyListGraph implements Graph {

    @Override
    public boolean add(Vertex vertex) {
        return false;
    }

    @Override
    public boolean connect(Vertex i, Vertex j) {
        return false;
    }

    @Override
    public boolean disconnect(Vertex i, Vertex j) {
        return false;
    }

    @Override
    public boolean isAdjacent(Vertex i, Vertex j) {
        return false;
    }

    @Override
    public boolean remove(Vertex vertex) {
        return false;
    }
}
