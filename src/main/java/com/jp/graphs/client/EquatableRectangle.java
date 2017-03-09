package com.jp.graphs.client;

import javafx.scene.shape.Rectangle;

/**
 * Since I'm using rectangles as data elements, and the javafx shape doesn't implement it,
 * I'm just extending the class so it's possible to evaluate them at a basic level.
 * <p>
 * WARNING
 * setters will invoke super() but will not update 'this' ; consider these values immutable as far
 * as equals and hashcode are concerned
 * <p>
 * <p>
 * Created by JP on 3/8/2017.
 */
public class EquatableRectangle extends Rectangle {

    private double x;
    private double y;
    private double width;
    private double height;

    public EquatableRectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquatableRectangle that = (EquatableRectangle) o;

        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        if (Double.compare(that.width, width) != 0) return false;
        return Double.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
