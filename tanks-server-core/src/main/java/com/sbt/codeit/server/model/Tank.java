package com.sbt.codeit.server.model;

import com.sbt.codeit.common.model.Coordinates;
import com.sbt.codeit.common.model.Direction;
import com.sbt.codeit.common.model.GameObjectType;
import com.sbt.codeit.server.GeneratorHelper;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class Tank extends DynamicGameObject {

    private int ammo = 100;
    private TankStatus status;
    private Character character;

    public Tank(String name, Coordinates coordinates, Direction direction) {
        super(name, coordinates, direction);
        init();
    }

    public Tank(String name) {
        super(name, Coordinates.ZERO_ZERO, Direction.UP);
        init();
    }

    private void init() {
        getSize().x_size = 3;
        getSize().y_size = 3;
        status = TankStatus.STOP;
        this.character = GeneratorHelper.INSTANCE.getCharacter();
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    @Override
    public Character getCharacter() {
        return character;
    }

    public TankStatus getStatus() {
        return status;
    }

    public void setStatus(TankStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tank[" +
                "\n id=" + getId() +
                "\n name='" + getName() +
                "\n size=" + getSize() +
                "\n direction=" + getDirection() +
                "\n status=" + status +
                "\n ammo=" + ammo +
                "\n character=" + character +
                "\n coordinates=" + getCoordinates() +
                ']';
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.TANK;
    }

    public enum TankStatus {
        MOVE,
        STOP,
        DAMGED
    }
}
