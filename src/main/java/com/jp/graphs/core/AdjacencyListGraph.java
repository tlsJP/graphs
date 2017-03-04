package com.jp.graphs.core;

import com.jp.graphs.stereotypes.Graph;
import com.jp.graphs.stereotypes.Vertex;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 * <p>
 * Created by JP on 3/3/2017.
 */
public class AdjacencyListGraph implements Graph {

    private Set<Vertex> vertices = new HashSet<>();

    @Override
    public boolean add(Vertex vertex) {
        return vertices.add(vertex);
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
    public Set<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public boolean isAdjacent(Vertex i, Vertex j) {
        return false;
    }

    @Override
    public boolean remove(Vertex vertex) {
        return vertices.remove(vertex);
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "[" + vertices + "]";
    }
}
