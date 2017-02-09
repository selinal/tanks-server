package com.sbt.codeit.core.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by sbt-galimov-rr on 09.02.2017.
 */
public class Tank {

    private Vector2 previousPosition = new Vector2();
    private Vector2 position = new Vector2();
    private TankState state = TankState.MOVING;
    private TankDirection direction = TankDirection.DOWN;

    public Tank(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public int getX() {
        return (int)position.x;
    }

    public int getY() {
        return (int)position.y;
    }

    public int getPreviousX() {
        return (int)previousPosition.x;
    }

    public int getPreviousY() {
        return (int)previousPosition.y;
    }

    public void setState(TankState state) {
        this.state = state;
    }

    public TankState getState() {
        return state;
    }

    public TankDirection getDirection() {
        return direction;
    }

    public void setDirection(TankDirection direction) {
        this.direction = direction;
    }

    public void move(ArrayList<ArrayList<Character>> map) {
        previousPosition.set(getX(), getY());
        if(state == TankState.MOVING) {
            switch (direction) {
                case UP:
                    if (getY() > 0 && !map.get(getY() - 1).get(getX()).equals('#')) {
                        position.y--;
                    }
                    break;
                case DOWN:
                    if (getY() < map.size() - 1 && !map.get(getY() + 1).get(getX()).equals('#')) {
                        position.y++;
                    }
                    break;
                case LEFT:
                    if (getX() > 0 && !map.get(getY()).get(getX() - 1).equals('#')) {
                        position.x--;
                    }
                    break;
                case RIGHT:
                    if (getX() < map.get(0).size() - 1 && !map.get(getY()).get(getX() + 1).equals('#')) {
                        position.x++;
                    }
            }
        }
    }
}
