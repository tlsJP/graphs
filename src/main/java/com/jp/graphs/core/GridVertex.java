package com.jp.graphs.core;

import com.jp.graphs.stereotypes.SimpleVertex;

/**
 * Vertex that has x and y coordinates
 * <p>
 * Created by JP on 3/7/2017.
 */
public class GridVertex extends SimpleVertex {

    private int x;
    private int y;

    public GridVertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GridVertex that = (GridVertex) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + x + "," + y + "]";// + "(" + getNeighbors().size() + ")";
    }
}
