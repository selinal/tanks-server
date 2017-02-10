package com.sbt.codeit.core.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Optional;

import static com.sbt.codeit.core.util.MapHelper.isEmpty;

/**
 * Created by sbt-galimov-rr on 09.02.2017.
 */
public class Tank {

    public static final int SIZE = 3;
    private static final int MAX_SHOTS = 10;

    private Vector2 previousPosition = new Vector2();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<ArrayList<Vector2>> points = new ArrayList<>();
    private int color;
    private int model;
    private volatile boolean canFire = true;
    private TankState state = TankState.STAYING;
    private Direction direction = Direction.DOWN;

    public Tank(float x, float y) {
        for (int i = 0; i < SIZE; i++) {
            points.add(new ArrayList<>());
            for (int j = 0; j < SIZE; j++) {
                points.get(i).add(new Vector2(x + j, y + i));
            }
        }
        for (int i = 0; i < MAX_SHOTS; i++) {
            bullets.add(new Bullet());
        }
    }

    public int getX() {
        return (int) points.get(0).get(0).x;
    }

    public int getY() {
        return (int) points.get(0).get(0).y;
    }

    public int getPreviousX() {
        return (int) previousPosition.x;
    }

    public int getPreviousY() {
        return (int) previousPosition.y;
    }

    public void setState(TankState state) {
        this.state = state;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
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

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void update(ArrayList<ArrayList<Character>> map) {
        previousPosition.set(getX(), getY());
        if (state == TankState.MOVING) {
            switch (direction) {
                case UP:
                    if (isNotOnTheEdgeUp() && points.get(0).stream().allMatch(vectors -> isEmpty(map, vectors.x, getY() - 1))) {
                        points.forEach(vectors -> vectors.forEach(vector -> vector.y--));
                    }
                    break;
                case DOWN:
                    if (isNotOnTheEdgeDown(map) && points.get(SIZE - 1).stream().allMatch(vectors -> isEmpty(map, vectors.x, getY() + SIZE))) {
                        points.forEach(vectors -> vectors.forEach(vector -> vector.y++));
                    }
                    break;
                case LEFT:
                    if (isNotOnTheEdgeLeft() && points.stream().allMatch(vectors -> isEmpty(map, getX() - 1, vectors.get(0).y))) {
                        points.forEach(vectors -> vectors.forEach(vector -> vector.x--));
                    }
                    break;
                case RIGHT:
                    if (isNotOnTheEdgeRight(map) && points.stream().allMatch(vectors -> isEmpty(map, getX() + SIZE, vectors.get(SIZE - 1).y))) {
                        points.forEach(vectors -> vectors.forEach(vector -> vector.x++));
                    }
            }
        }
    }

    public void fire() {
        if (!canFire) {
            return;
        }
        canFire = false;
        Optional<Bullet> availableBullet = bullets.stream().filter(bullet -> !bullet.isAvailable()).findFirst();
        availableBullet.ifPresent(bullet -> bullet.setUp(getX(), getY(), direction));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                canFire = true;
            }
        }, 2F);
    }

    private boolean isNotOnTheEdgeRight(ArrayList<ArrayList<Character>> map) {
        return getX() + SIZE - 1 < map.get(0).size() - 1;
    }

    private boolean isNotOnTheEdgeLeft() {
        return getX() > 0;
    }

    private boolean isNotOnTheEdgeDown(ArrayList<ArrayList<Character>> map) {
        return getY() + SIZE - 1 < map.size() - 1;
    }

    private boolean isNotOnTheEdgeUp() {
        return getY() > 0;
    }

}
