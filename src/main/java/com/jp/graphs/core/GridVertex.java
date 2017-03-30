package com.jp.graphs.core;

import com.jp.graphs.stereotypes.Heuristic;
import com.jp.graphs.stereotypes.SimpleVertex;
import com.jp.graphs.stereotypes.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Vertex that has x and y coordinates
 * <p>
 * Created by JP on 3/7/2017.
 */
public class GridVertex extends SimpleVertex implements Comparable, Heuristic {

    private static final Logger LOGGER = LoggerFactory.getLogger(GridVertex.class);
    private static final double ARBITRARILY_HIGH_VALUE = Long.MAX_VALUE;

    private int x;
    private int y;

    // Movement cost
    private double gScore = ARBITRARILY_HIGH_VALUE;
    // Sum of the heuristic and the movement cost
    private double fScore = ARBITRARILY_HIGH_VALUE;
    // Heuristic
    private double hScore;

    private boolean restricted;

    public GridVertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double calculateHeuristic(Vertex v) {
        GridVertex gv = (GridVertex) v;

        hScore = Math.abs(gv.getX() - x) + Math.abs(gv.getY() - y) ;

        // Pythagorean theorem
//        int yDistance = Math.abs(y - gv.y);
//        int xDistance = Math.abs(x - gv.x);
//        hScore = Math.sqrt(yDistance * yDistance + xDistance * xDistance);



        return hScore;
    }

    @Override
    public int compareTo(Object o) {
        GridVertex that = (GridVertex) o;

        if (fScore == that.fScore) {
            return 0;
        } else if (fScore > that.fScore) {
            return 1;
        } else if (fScore < that.fScore) {
            return -1;
        }

        LOGGER.error("Not supposed to be here...");
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

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

    public double getfScore() {
        return fScore;
    }

    public double getgScore() {
        return gScore;
    }

    public double gethScore() {
        return hScore;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setfScore(double fScore) {
        this.fScore = fScore;
    }

    public void setgScore(double gScore) {
        this.gScore = gScore;
    }

    public void sethScore(double hScore) {
        this.hScore = hScore;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + x + "," + y + "]";// + "(" + getNeighbors().size() + ")";
    }
}
