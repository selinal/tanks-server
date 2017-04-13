package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class Bullet extends DynamicGameObject {

    public Bullet(String name, Direction direction) {
        super(name, direction);
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.BULLET;
    }

    public String toString() {
        switch (direction) {
            case UP:
                return "^";
            case DOWN:
                return "v";
            case LEFT:
                return "<";
            case RIGHT:
                return ">";
        }
        return null;
    }
}
