package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public abstract class DynamicGameObject extends BaseGameObject {
    protected Direction direction;

    public DynamicGameObject(String name, Direction direction) {
        this.name = name;
        this.direction = direction;
    }


}
