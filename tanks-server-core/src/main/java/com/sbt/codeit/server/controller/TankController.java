package com.sbt.codeit.server.controller;

import com.sbt.codeit.common.model.Coordinates;
import com.sbt.codeit.common.model.Direction;
import com.sbt.codeit.common.model.Size;
import com.sbt.codeit.server.WorldMap;
import com.sbt.codeit.core.Command;
import com.sbt.codeit.server.model.Bullet;
import com.sbt.codeit.server.model.Space;
import com.sbt.codeit.server.model.Tank;

/**
 * Created by sbt-selin-an on 14.04.2017.
 */
public class TankController {

    private Tank tank;
    private WorldMap map;

    public TankController(Tank tank, WorldMap map) {
        this.tank = tank;
        this.map = map;
    }

    private boolean checkPosition(Coordinates coordinates) {
        for (int r = coordinates.yFrom; r <= coordinates.yTo; r++) {
            for (int c = coordinates.xFrom; c <= coordinates.xTo; c++) {
                if (map.get(r, c) != null && !map.get(r, c).equals('0')) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean coordinatesCorrect(Coordinates coordinates) {
        return coordinates.yFrom > 0 && coordinates.yFrom <= map.getMapHeight()
                && coordinates.yTo > 0 && coordinates.yTo < map.getMapHeight()
                && coordinates.xFrom > 0 && coordinates.xFrom <= map.getMapWidth()
                && coordinates.xTo > 0 && coordinates.xTo <= map.getMapWidth();
    }

    private void drawTank() {
        Coordinates coordinates = tank.getCoordinates();
        Coordinates prevCoordinates = tank.getPrevCoordinates();
        Character c = tank.getCharacter();
        //TODO fix draw
        map.put(coordinates.yFrom, coordinates.xFrom, c);
        map.put(coordinates.yFrom, coordinates.xTo, c);
        map.put(coordinates.yTo, coordinates.xFrom, c);
        map.put(coordinates.yTo, coordinates.xTo, c);
        switch (tank.getDirection()) {
            case UP:
                map.put(prevCoordinates.yTo, prevCoordinates.xFrom, Space.CHARACTER);
                map.put(prevCoordinates.yTo, prevCoordinates.xTo, Space.CHARACTER);
                map.put(coordinates.yFrom + 1, coordinates.xFrom, c);
                map.put(coordinates.yFrom + 1, coordinates.xTo, c);
                map.put(coordinates.yFrom + 1, coordinates.xFrom + 1, 'O');
                map.put(coordinates.yFrom, coordinates.xFrom + 1, '|');
                break;
            case DOWN:
                map.put(prevCoordinates.yFrom, prevCoordinates.xFrom, Space.CHARACTER);
                map.put(prevCoordinates.yFrom, prevCoordinates.xTo, Space.CHARACTER);
                map.put(coordinates.yFrom + 1, coordinates.xFrom, c);
                map.put(coordinates.yFrom + 1, coordinates.xTo, c);
                map.put(coordinates.yFrom + 1, coordinates.xFrom + 1, 'O');
                map.put(coordinates.yTo, coordinates.xFrom + 1, '|');
                break;
            case LEFT:
                map.put(prevCoordinates.yFrom, prevCoordinates.xTo, Space.CHARACTER);
                map.put(prevCoordinates.yTo, prevCoordinates.xTo, Space.CHARACTER);
                map.put(coordinates.yFrom, coordinates.xFrom + 1, c);
                map.put(coordinates.yTo, coordinates.xFrom + 1, c);
                map.put(coordinates.yFrom + 1, coordinates.xFrom + 1, 'O');
                map.put(coordinates.yFrom + 1, coordinates.xFrom, '-');
                break;
            case RIGHT:
                map.put(prevCoordinates.yFrom, prevCoordinates.xFrom, Space.CHARACTER);
                map.put(prevCoordinates.yTo, prevCoordinates.xFrom, Space.CHARACTER);
                map.put(coordinates.yFrom, coordinates.xFrom + 1, c);
                map.put(coordinates.yTo, coordinates.xFrom + 1, c);
                map.put(coordinates.yFrom + 1, coordinates.xFrom + 1, 'O');
                map.put(coordinates.yTo, coordinates.xFrom + 1, '-');
                break;
        }
    }

    public void init(int corner) {
        if (corner % 4 == 0) {
            tank.setCoordinates(
                    new Coordinates(0, 0, tank.getSize()));
        } else if (corner % 4 == 1) {
            tank.setCoordinates(
                    new Coordinates(0, map.getMapWidth() - tank.getSize().x_size, tank.getSize()));
        } else if (corner % 4 == 2) {
            tank.setCoordinates(
                    new Coordinates(map.getMapHeight() - tank.getSize().y_size, 0, tank.getSize()));
        } else {
            tank.setCoordinates(
                    new Coordinates(map.getMapHeight() - tank.getSize().y_size, map.getMapWidth() - tank.getSize().x_size, tank.getSize()));
        }
        if (checkPosition(tank.getCoordinates())) {
            drawTank();
        } else {
            System.err.println("Could not draw tank. New position has barriers.");
        }

    }

    private void move(Direction direction) {
        if (tank.getDirection() != direction) {
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
        }
    }

    public Bullet fire() {
        tank.setStatus(Tank.TankStatus.STOP);
        Coordinates bulletCoordinates = null;
        switch (tank.getDirection()) {
            case UP:
                bulletCoordinates = new Coordinates(tank.getCoordinates().yFrom - 1, tank.getCoordinates().xFrom + 1, Size.ONE);
                break;
            case DOWN:
                bulletCoordinates = new Coordinates(tank.getCoordinates().yTo + 1, tank.getCoordinates().xFrom + 1, Size.ONE);
                break;
            case LEFT:
                bulletCoordinates = new Coordinates(tank.getCoordinates().yFrom + 1, tank.getCoordinates().xFrom - 1, Size.ONE);
                break;
            case RIGHT:
                bulletCoordinates = new Coordinates(tank.getCoordinates().yFrom + 1, tank.getCoordinates().xTo + 1, Size.ONE);
                break;
        }
        return new Bullet(tank.getName() + "-bullet", bulletCoordinates, tank.getDirection(), tank.getId());
    }


    public String getTankName() {
        return tank.getName();
    }

    public int getId() {
        return tank.getId();
    }

    public synchronized void executeCommand(Command command) {
        switch (command) {
            case UP:
                move(Direction.UP);
                break;
            case DOWN:
                move(Direction.DOWN);
                break;
            case LEFT:
                move(Direction.LEFT);
                break;
            case RIGHT:
                move(Direction.RIGHT);
                break;
            case FIRE:
                fire();
                break;
            case STOP:
                tank.setStatus(Tank.TankStatus.STOP);
            default:

        }
    }
}
