package com.sbt.codeit.server.model;

import com.sbt.codeit.common.model.Coordinates;
import com.sbt.codeit.common.model.Direction;
import com.sbt.codeit.common.model.GameObjectType;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class Bullet extends DynamicGameObject {
    private int parent;
    private int speed;

    public Bullet(String name, Coordinates coordinates, Direction direction, int parent) {
        super(name, coordinates, direction);
        this.parent = parent;
        speed = 2;
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.BULLET;
    }

    @Override
    public Character getCharacter() {
        switch (getDirection()) {
            case UP:
                return '^';
            case DOWN:
                return 'v';
            case LEFT:
                return '<';
            case RIGHT:
                return '>';
        }
        return null;
    }

    public int getParent() {
        return parent;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
