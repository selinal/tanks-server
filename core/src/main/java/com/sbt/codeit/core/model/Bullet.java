package com.sbt.codeit.core.model;

import com.badlogic.gdx.math.Vector2;
import com.sbt.codeit.core.util.MapHelper;

import java.util.ArrayList;

import static com.sbt.codeit.core.model.Tank.SIZE;

/**
 * Created by SBT-Galimov-RR on 10.02.2017.
 */
public class Bullet {

    private Vector2 position = new Vector2();
    private Vector2 previous = new Vector2();
    private Vector2 velocity = new Vector2();
    private Direction direction;
    private boolean available;

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setUp(int x, int y, Direction direction) {
        this.direction = direction;
        setAvailable(true);
        switch (direction){
            case UP:
                position.set(x + SIZE / 2, y - 1);
                velocity.set(0, -1);
                break;
            case DOWN:
                position.set(x + SIZE / 2, y + SIZE);
                velocity.set(0, 1);
                break;
            case LEFT:
                position.set(x - 1, y + SIZE / 2);
                velocity.set(-1, 0);
                break;
            case RIGHT:
                position.set(x + SIZE, y + SIZE / 2);
                velocity.set(1, 0);
        }
    }

    public void explode(ArrayList<ArrayList<Character>> map) {
        if(!isAvailable()) {
            return;
        }
        fixStuck(map);
        if(direction == Direction.UP || direction == Direction.DOWN) {
            for (int x = getX() - SIZE / 2; x <= getX() + SIZE / 2; x++) {
                if(MapHelper.isWall(map, x, getY())) {
                    map.get(getY()).set(x, ' ');
                }
            }
        } else {
            for (int y = getY() - SIZE / 2; y <= getY() + SIZE / 2; y++) {
                if(MapHelper.isWall(map, getX(), y)) {
                    map.get(y).set(getX(), ' ');
                }
            }
        }
        setAvailable(false);
    }

    private void fixStuck(ArrayList<ArrayList<Character>> map) {
        if(MapHelper.isWall(map, getPreviousX(), getPreviousY())) {
            position.set(getPreviousX(), getPreviousY());
        }
    }

    public void update() {
        previous.set(position.x, position.y);
        position.add(velocity);
    }

}
