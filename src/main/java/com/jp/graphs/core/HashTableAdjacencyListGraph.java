package com.jp.graphs.core;

import com.jp.graphs.stereotypes.Graph;
import com.jp.graphs.stereotypes.Vertex;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * I think this is the generally more elegant way of implementing an adjacency list graph
 * <p>
 * Created by JP on 3/10/2017.
 */
public class HashTableAdjacencyListGraph extends AdjacencyListGraph implements Graph {

    private Map<Vertex, Set> vertices = new Hashtable<>();

    @Override
    public boolean add(Vertex vertex) {
        vertices.put(vertex, new HashSet());
        return true;
    }

    @Override
    public boolean connect(Vertex i, Vertex j) {
        super.connect(i, j);

        Set iV = vertices.get(i);
        Set jV = vertices.get(j);
        iV.add(j);
        jV.add(i);

        return true;
    }

    @Override
    public boolean disconnect(Vertex i, Vertex j) {
        super.disconnect(i, j);

        Set iV = vertices.get(i);
        Set jV = vertices.get(j);

        iV.remove(j);
        jV.remove(i);

        return true;
    }

    @Override
    public Vertex getVertex(Vertex v) {
        return vertices.keySet().stream()
                .filter(s -> s.equals(v))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Vertex> getVertices() {
        return vertices.keySet();
    }

    @Override
    public boolean isAdjacent(Vertex i, Vertex j) {
        Set iV = vertices.get(i);
        Set jV = vertices.get(j);

        return iV.contains(j) && jV.contains(i);

    }

    @Override
    public boolean remove(Vertex vertex) {
        super.remove(vertex);

        vertices.remove(vertex);
        vertices.entrySet().forEach(es -> es.getValue().remove(vertex));
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("\n");
        vertices.entrySet().forEach(es -> sb.append(es).append("\n"));
        return sb.toString();
    }
}
