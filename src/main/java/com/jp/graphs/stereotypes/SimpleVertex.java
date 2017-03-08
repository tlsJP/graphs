package com.jp.graphs.stereotypes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Core implementation of a vertex.
 * I haven't decided yet how 'sentient' the vertices should be...
 * <p>
 * TODO - fix generics
 * <p>
 * Created by JP on 3/3/2017.
 */
public abstract class SimpleVertex<T> implements Vertex {

    private T dataElement;

    private Set<Vertex> neighbors;

    public SimpleVertex() {
        dataElement = null;
        neighbors = new HashSet<>();
    }

    public SimpleVertex(T dataElement) {
        this();
        this.dataElement = dataElement;
    }

    @Override
    public void connect(Vertex vertex) {
        neighbors.add(vertex);
    }

    @Override
    public void disconnect(Vertex vertex) {
        neighbors.remove(vertex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleVertex<?> vertex = (SimpleVertex<?>) o;

        return dataElement != null ? dataElement.equals(vertex.dataElement) : vertex.dataElement == null;
    }

    @Override
    public T getDataElement() {
        return dataElement;
    }

    @Override
    public Set<Vertex> getNeighbors() {
        return Collections.unmodifiableSet(neighbors);
    }

    @Override
    public Vertex getParent() {
        return null;
    }

    @Override
    public int hashCode() {
        return dataElement != null ? dataElement.hashCode() : 0;
    }

    public String printNeighbors() {
        return neighbors.stream().map(n -> toString()).collect(Collectors.joining(","));
    }

    @Override
    public void setDataElement(Object o) {
        this.dataElement = (T) o;

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "["
                + "dataElement:" + dataElement + "]";
    }
}
