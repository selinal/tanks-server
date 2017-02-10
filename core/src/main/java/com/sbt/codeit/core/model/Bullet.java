package com.sbt.codeit.core.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by SBT-Galimov-RR on 10.02.2017.
 */
public class Bullet {

    private Vector2 position = new Vector2();
    private Vector2 previous = new Vector2();
    private Vector2 velocity = new Vector2();
    private Direction direction;
    private boolean exploded;

    public Bullet(int x, int y, Direction direction) {
        this.direction = direction;
        switch (direction){
            case UP:
                position.set(x + Tank.SIZE / 2, y - 1);
                velocity.set(0, -1);
                break;
            case DOWN:
                position.set(x + Tank.SIZE / 2, y + Tank.SIZE);
                velocity.set(0, 1);
                break;
            case LEFT:
                position.set(x, y + Tank.SIZE / 2);
                velocity.set(-1, 0);
                break;
            case RIGHT:
                position.set(x + Tank.SIZE, y + Tank.SIZE / 2);
                velocity.set(1, 0);
        }
    }

    public int getX() {
        return (int)position.x;
    }

    public int getY() {
        return (int)position.y;
    }

    public int getPreviousX() {
        return (int)previous.x;
    }

    public int getPreviousY() {
        return (int)previous.y;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void explode(ArrayList<ArrayList<Character>> map) {
        exploded = true;
//        if(MapHelper.isWall(map, getPreviousX(), getPreviousY())) {
//            position.set(getPreviousX(), getPreviousY());
//        }
//        if(direction == Direction.UP || direction == Direction.DOWN) {
//
//        }
        map.get(getY()).set(getX(), ' ');
    }

    public void move() {
        previous.set(position.x, position.y);
        position.add(velocity);
    }

}
