package com.sbt.codeit.core.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by sbt-galimov-rr on 09.02.2017.
 */
public class Tank {

    private Vector2 previousPosition = new Vector2();
    private Vector2 position = new Vector2();
    private int color;
    private int model;
    private TankState state = TankState.STAYING;
    private TankDirection direction = TankDirection.DOWN;

    public Tank(float x, float y) {
        position.set(x, y);
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

    public void setType(int color, int model) {
        this.color = color;
        this.model = model;
    }

    public int getColor() {
        return color;
    }

    public int getModel() {
        return model;
    }

    public void move(ArrayList<ArrayList<Character>> map) {
        previousPosition.set(getX(), getY());
        if(state == TankState.MOVING) {
            switch (direction) {
                case UP:
                    if (getY() > 0 && isEmpty(map, getX(), getY() - 1)) {
                        position.y--;
                    }
                    break;
                case DOWN:
                    if (getY() < map.size() - 1 && isEmpty(map, getX(), getY() + 1)) {
                        position.y++;
                    }
                    break;
                case LEFT:
                    if (getX() > 0 && isEmpty(map, getX() - 1, getY())) {
                        position.x--;
                    }
                    break;
                case RIGHT:
                    if (getX() < map.get(0).size() - 1 && isEmpty(map, getX() + 1, getY())) {
                        position.x++;
                    }
            }
        }
    }

    private boolean isEmpty(ArrayList<ArrayList<Character>> map, int x, int y) {
        return map.get(y).get(x).equals(' ');
    }
}
