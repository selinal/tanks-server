package com.sbt.codeit.server.controller;

import com.sbt.codeit.server.WorldMap;
import com.sbt.codeit.server.model.Bullet;
import com.sbt.codeit.server.model.Tank;

/**
 * Created by sbt-selin-an on 15.04.2017.
 */
public class BulletController {

    private WorldMap map;
    private Bullet bullet;

    public BulletController(WorldMap map, Bullet bullet) {
        this.map = map;
        this.bullet = bullet;
    }

    public boolean isBulletHitTank(Tank tank){
        for (int r = tank.getCoordinates().yFrom; r <= tank.getCoordinates().yTo; r++) {
            for (int c = tank.getCoordinates().xFrom; c <= tank.getCoordinates().xTo; c++) {
                if (bullet.getCoordinates().yFrom >= r && bullet.getCoordinates().yTo <= r
                        && bullet.getCoordinates().xFrom >= c && bullet.getCoordinates().xTo <= c) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move() {
        /*if (tank.getDirection() != direction) {
            tank.setDirection(direction);
            tank.setStatus(Tank.TankStatus.STOP);
            drawTank();
        } else {
            Coordinates newCoordinates = tank.getCoordinates().move(tank.getDirection());
            if (coordinatesCorrect(newCoordinates) && checkPosition(newCoordinates)) {
                tank.setStatus(Tank.TankStatus.MOVE);
                tank.setCoordinates(newCoordinates);
                drawTank();
            } else {
                tank.setStatus(Tank.TankStatus.STOP);
            }
        }*/
    }
}
