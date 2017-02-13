package com.sbt.codeit.core.model;

import com.badlogic.gdx.math.Vector2;
import com.sbt.codeit.core.util.FieldHelper;

import java.util.ArrayList;
import java.util.Collections;

import static com.sbt.codeit.core.model.Tank.SIZE;

/**
 * Created by SBT-Galimov-RR on 10.02.2017.
 */
public class Bullet extends GameObject {

    public static final float SPEED = 10;
    private boolean available;

    public Bullet() {
        position.add(new ArrayList<>(Collections.singletonList(new Vector2())));
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setUp(int x, int y, Direction direction) {
        setDirection(direction);
        setAvailable(true);
        int posX = x + SIZE / 2;
        int posY = y + SIZE / 2;
        switch (direction){
            case UP:
                posY = y - 1;
                break;
            case DOWN:
                posY = y + SIZE;
                break;
            case LEFT:
                posX = x - 1;
                break;
            default:
                posX = x + SIZE;
        }
        position.get(0).get(0).set(posX, posY);
    }

    @Override
    public void update(ArrayList<ArrayList<Character>> field) {
        super.update(field);
        if (isOnTheField()) {
            fixStuck();
            if (!FieldHelper.isEmpty(field, getX(), getY())) {
                explode();
            }
        } else {
            setAvailable(false);
        }
    }

    private void fixStuck() {
        if(FieldHelper.isWall(field, getPreviousX(), getPreviousY())) {
            position.get(0).get(0).set(getPreviousX(), getPreviousY());
        }
    }

    public boolean isOnTheField() {
        return getX() >= 0 && getY() >= 0 && getY() < FieldHelper.FIELD_HEIGHT && getX() < FieldHelper.FIELD_WIDTH;
    }

    public void explode() {
        if(!isAvailable()) {
            return;
        }
        if(direction == Direction.UP || direction == Direction.DOWN) {
            for (int x = getX() - SIZE / 2; x <= getX() + SIZE / 2; x++) {
                if(FieldHelper.isWall(field, x, getY())) {
                    field.get(getY()).set(x, ' ');
                }
            }
        } else {
            for (int y = getY() - SIZE / 2; y <= getY() + SIZE / 2; y++) {
                if(FieldHelper.isWall(field, getX(), y)) {
                    field.get(y).set(getX(), ' ');
                }
            }
        }
        setAvailable(false);
    }

}
