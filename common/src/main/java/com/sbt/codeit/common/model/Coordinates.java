package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 14.04.2017.
 */
public class Coordinates {

    public int rowFrom;
    public int colFrom;
    public int rowTo;
    public int colTo;

    public Coordinates(int rowFrom, int colFrom, int rowTo, int colTo) {
        this.rowFrom = rowFrom;
        this.colFrom = colFrom;
        this.rowTo = rowTo;
        this.colTo = colTo;
    }

    public Coordinates(int rowFrom, int colFrom, Size size) {
        this.rowFrom = rowFrom;
        this.colFrom = colFrom;
        this.rowTo = rowFrom + size.height - 1;
        this.colTo = colFrom + size.width - 1;
    }

    public Coordinates move(Direction direction) {
        switch (direction) {
            case UP:
                return new Coordinates(rowFrom - 1, colFrom, rowTo - 1, colTo);
            case DOWN:
                return new Coordinates(rowFrom + 1, colFrom, rowTo + 1, colTo);
            case LEFT:
                return new Coordinates(rowFrom, colFrom - 1, rowTo, colTo - 1);
            case RIGHT:
                return new Coordinates(rowFrom, colFrom + 1, rowTo, colTo + 1);
        }
        return null;
    }

    @Override
    public String toString() {
        return "[rowFrom=" + rowFrom + ", colFrom=" + colFrom +
                ", rowTo=" + rowTo + ", colTo=" + colTo + ']';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coordinates &&
                (((Coordinates) obj).rowFrom == this.rowFrom &&
                        ((Coordinates) obj).colFrom == this.colFrom &&
                        ((Coordinates) obj).rowTo == this.rowTo &&
                        ((Coordinates) obj).colTo == this.colTo);
    }
}
