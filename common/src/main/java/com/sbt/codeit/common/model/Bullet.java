package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class Bullet extends DynamicGameObject {
    private int parent;
    public int speed;

    public Bullet(String name, Direction direction, int parent) {
        super(name, direction);
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
}
