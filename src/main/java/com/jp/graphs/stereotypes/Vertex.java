package com.jp.graphs.stereotypes;

import java.util.HashSet;
import java.util.Set;

/**
 * Core implementation of a vertex
 * <p>
 * Created by JP on 3/3/2017.
 */
public abstract class Vertex<T> {

    private T dataElement;

    private Set<Vertex> neighbors;

    public T getDataElement() {
        return dataElement;
    }

    public Set<Vertex> getNeighbors() {
        if (neighbors == null) {
            neighbors = new HashSet<>(1);
        }

        return neighbors;
    }

    public boolean removeNeighbor(Vertex vertex) {
        return getNeighbors().remove(vertex);
    }

    public void setDataElement(T dataElement) {
        this.dataElement = dataElement;
    }


}
