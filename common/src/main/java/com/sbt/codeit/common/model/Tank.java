package com.sbt.codeit.common.model;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class Tank extends DynamicGameObject {

    private int ammo = 100;
    private TankStatus status;

    public Tank(String name, Direction direction) {
        super(name,direction);
        size.height = 3;
        size.width = 3;
        status = TankStatus.STAING;
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.TANK;
    }

    public enum TankStatus {
        MOVING,
        STAING,
        HITED
    }

    public TankStatus getTankStaus(){
        return status;
    }

    @Override
    public String toString() {
        return "";
    }
}
