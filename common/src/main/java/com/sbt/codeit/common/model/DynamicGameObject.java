package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public abstract class DynamicGameObject extends BaseGameObject {
    private Direction direction;

    public DynamicGameObject(String name, Direction direction) {
        this.setName(name);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
