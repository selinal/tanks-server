package com.sbt.codeit.common.model;

import com.sbt.codeit.common.WorldMap;
import com.sbt.codeit.common.exception.ObjectDrawException;

/**
 * Created by sbt-selin-an on 14.04.2017.
 */
public class TankManager {

    private Tank tank;
    private WorldMap map;

    public TankManager(Tank tank, WorldMap map) {
        this.tank = tank;
        this.map = map;
    }

    private boolean checkPosition(Coordinates coordinates) {
        for (int r = coordinates.rowFrom; r <= coordinates.rowTo; r++) {
            for (int c = coordinates.colFrom; c <= coordinates.colTo; c++) {
                if (map.get(r, c) != null && !map.get(r, c).equals('0')) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean coordinatesCorrect(Coordinates coordinates) {
        return coordinates.rowFrom > 0 && coordinates.rowFrom <= map.getMapHeight()
                && coordinates.rowTo > 0 && coordinates.rowTo < map.getMapHeight()
                && coordinates.colFrom > 0 && coordinates.colFrom <= map.getMapWidth()
                && coordinates.colTo > 0 && coordinates.colTo <= map.getMapWidth();
    }

    public void drawTank() {
        Coordinates coordinates = tank.getCoordinates();
        Coordinates prevCoordinates = tank.getPrevCoordinates();
        Character c = tank.getCharacter();
        //TODO fix draw
        map.put(coordinates.rowFrom, coordinates.colFrom, c);
        map.put(coordinates.rowFrom, coordinates.colTo, c);
        map.put(coordinates.rowTo, coordinates.colFrom, c);
        map.put(coordinates.rowTo, coordinates.colTo, c);
        switch (tank.getDirection()) {
            case UP:
                map.put(prevCoordinates.rowTo, prevCoordinates.colFrom, Space.CHARACTER);
                map.put(prevCoordinates.rowTo, prevCoordinates.colTo, Space.CHARACTER);
                map.put(coordinates.rowFrom + 1, coordinates.colFrom, c);
                map.put(coordinates.rowFrom + 1, coordinates.colTo, c);
                map.put(coordinates.rowFrom + 1, coordinates.colFrom + 1, 'O');
                map.put(coordinates.rowFrom, coordinates.colFrom + 1, '|');
                break;
            case DOWN:
                map.put(prevCoordinates.rowFrom, prevCoordinates.colFrom, Space.CHARACTER);
                map.put(prevCoordinates.rowFrom, prevCoordinates.colTo, Space.CHARACTER);
                map.put(coordinates.rowFrom + 1, coordinates.colFrom, c);
                map.put(coordinates.rowFrom + 1, coordinates.colTo, c);
                map.put(coordinates.rowFrom + 1, coordinates.colFrom + 1, 'O');
                map.put(coordinates.rowTo, coordinates.colFrom + 1, '|');
                break;
            case LEFT:
                map.put(prevCoordinates.rowFrom, prevCoordinates.colTo, Space.CHARACTER);
                map.put(prevCoordinates.rowTo, prevCoordinates.colTo, Space.CHARACTER);
                map.put(coordinates.rowFrom, coordinates.colFrom + 1, c);
                map.put(coordinates.rowTo, coordinates.colFrom + 1, c);
                map.put(coordinates.rowFrom + 1, coordinates.colFrom + 1, 'O');
                map.put(coordinates.rowFrom + 1, coordinates.colFrom, '-');
                break;
            case RIGHT:
                map.put(prevCoordinates.rowFrom, prevCoordinates.colFrom, Space.CHARACTER);
                map.put(prevCoordinates.rowTo, prevCoordinates.colFrom, Space.CHARACTER);
                map.put(coordinates.rowFrom, coordinates.colFrom + 1, c);
                map.put(coordinates.rowTo, coordinates.colFrom + 1, c);
                map.put(coordinates.rowFrom + 1, coordinates.colFrom + 1, 'O');
                map.put(coordinates.rowTo, coordinates.colFrom + 1, '-');
                break;
        }
    }

    public void init(int corner) {
        if (corner == 0) {
            tank.setCoordinates(
                    new Coordinates(0, 0, tank.getSize()));
        } else if (corner == 1) {
            tank.setCoordinates(
                    new Coordinates(0, map.getMapWidth() - tank.getSize().width, tank.getSize()));
        } else if (corner == 2) {
            tank.setCoordinates(
                    new Coordinates(map.getMapHeight() - tank.getSize().height, 0, tank.getSize()));
        } else {
            tank.setCoordinates(
                    new Coordinates(map.getMapHeight() - tank.getSize().height, map.getMapWidth() - tank.getSize().width, tank.getSize()));
        }
        if (checkPosition(tank.getCoordinates())) {
            drawTank();
        } else {
            System.err.println("Could not draw tank. New position has barriers.");
        }

    }

    public void move(Direction direction) {
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


}
