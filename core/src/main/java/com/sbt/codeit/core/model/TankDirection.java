package com.sbt.codeit.core.model;

/**
 * Created by SBT-Galimov-RR on 09.02.2017.
 */
public enum TankDirection {

    UP(0),
    DOWN(180),
    LEFT(270),
    RIGHT(90);

    private int rotation;

    TankDirection(int rotation) {
        this.rotation = rotation;
    }

    public int toRotation(){
        return rotation;
    }

}
