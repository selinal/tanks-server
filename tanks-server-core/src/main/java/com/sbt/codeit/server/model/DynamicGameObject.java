package com.sbt.codeit.server.model;

import com.sbt.codeit.common.model.Coordinates;
import com.sbt.codeit.common.model.Direction;
import com.sbt.codeit.server.GeneratorHelper;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public abstract class DynamicGameObject extends BaseGameObject {
    private Direction direction;
    private Coordinates coordinates;
    private Coordinates prevCoordinates;
    private int id;

    public DynamicGameObject(String name, Coordinates coordinates,Direction direction) {
        this.setName(name);
        this.direction = direction;
        this.coordinates = coordinates;
        this.prevCoordinates = coordinates;
        id = GeneratorHelper.INSTANCE.getId();
    }

    @Override
    public int getId() {
        return id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        prevCoordinates = this.coordinates;
        this.coordinates = coordinates;
    }

    public Coordinates getPrevCoordinates() {
        return prevCoordinates;
    }
}
