package com.jp.graphs.stereotypes;

import java.util.HashSet;
import java.util.Set;

/**
 * Core implementation of a vertex.
 * I haven't decided yet how 'sentient' the vertices should be...
 * <p>
 * <p>
 * Created by JP on 3/3/2017.
 */
public abstract class Vertex<T> {

    private T dataElement;

    private Set<Vertex> neighbors;

    public Vertex() {
        dataElement = null;
        neighbors = new HashSet<>();
    }

    public Vertex(T dataElement) {
        this();
        this.dataElement = dataElement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex<?> vertex = (Vertex<?>) o;

        return dataElement != null ? dataElement.equals(vertex.dataElement) : vertex.dataElement == null;
    }

    public T getDataElement() {
        return dataElement;
    }

    public Set<Vertex> getNeighbors() {
        if (neighbors == null) {
            neighbors = new HashSet<>(1);
        }

        return neighbors;
    }

    @Override
    public int hashCode() {
        return dataElement != null ? dataElement.hashCode() : 0;
    }

    public boolean removeNeighbor(Vertex vertex) {
        return getNeighbors().remove(vertex);
    }

    public void setDataElement(T dataElement) {
        this.dataElement = dataElement;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "["
                + "dataElement:" + dataElement + "]";
    }

}
