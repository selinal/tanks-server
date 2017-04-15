package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 14.04.2017.
 */
public class Coordinates {

    public int yFrom;
    public int xFrom;
    public int yTo;
    public int xTo;
    public static Coordinates ZERO_ZERO = new Coordinates(0,0,Size.ONE);

    public Coordinates(int xFrom, int yFrom, int xTo, int yTo) {
        this.yFrom = yFrom;
        this.xFrom = xFrom;
        this.yTo = yTo;
        this.xTo = xTo;
    }

    public Coordinates(int xFrom, int yFrom, Size size) {
        this.yFrom = yFrom;
        this.xFrom = xFrom;
        this.yTo = yFrom + size.y_size - 1;
        this.xTo = xFrom + size.x_size - 1;
    }

    public Coordinates move(Direction direction) {
        switch (direction) {
            case UP:
                return new Coordinates(xFrom, yFrom - 1, xTo, yTo - 1);
            case DOWN:
                return new Coordinates(xFrom, yFrom + 1, xTo, yTo + 1);
            case LEFT:
                return new Coordinates(xFrom - 1, yFrom, xTo - 1, yTo);
            case RIGHT:
                return new Coordinates(xFrom + 1, yFrom, xTo + 1, yTo);
        }
        return null;
    }

    @Override
    public String toString() {
        return "[xFrom=" + xFrom + ", yFrom=" + yFrom +
                ", xTo=" + xTo + ", yTo=" + yTo + ']';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coordinates &&
                (((Coordinates) obj).xFrom == this.xFrom &&
                        ((Coordinates) obj).yFrom == this.yFrom &&
                        ((Coordinates) obj).xTo == this.xTo &&
                        ((Coordinates) obj).yTo == this.yTo);
    }
}
