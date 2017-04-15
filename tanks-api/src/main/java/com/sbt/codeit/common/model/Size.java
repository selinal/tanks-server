package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class Size {

    public int x_size;
    public int y_size;

    public static Size ONE = new Size(1,1);

    public Size(int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
    }

    @Override
    public String toString() {
        return "[x_size=" + x_size + ", y_size=" + y_size + ']';
    }
}
