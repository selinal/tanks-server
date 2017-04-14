package com.sbt.codeit.common.model;

import com.sbt.codeit.common.GeneratorHelper;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class Tank extends DynamicGameObject {

    private int ammo = 100;
    private TankStatus status;
    private Character character;
    private Coordinates coordinates;
    private Coordinates prevCoordinates;

    public Tank(String name, Coordinates coordinates, Direction direction) {
        super(name, direction);
        getSize().height = 3;
        getSize().width = 3;
        status = TankStatus.STOP;
        this.character = GeneratorHelper.INSTANCE.getCharacter();
        this.coordinates = coordinates;
        this.prevCoordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        prevCoordinates = this.coordinates;
        this.coordinates = coordinates;
    }

    public Coordinates getPrevCoordinates() {
        return prevCoordinates;
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
                "\n id='" + getId() +
                "\n name='" + getName() +
                "\n size=" + getSize() +
                "\n direction=" + getDirection() +
                "\n status=" + status +
                "\n ammo=" + ammo +
                "\n character=" + character +
                "\n coordinates=" + coordinates +
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
