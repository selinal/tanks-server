package com.sbt.codeit.common.model;

import com.sbt.codeit.common.WorldMap;
import org.junit.Test;

/**
 * Created by sbt-selin-an on 14.04.2017.
 */
public class TankManagerTest {

    @Test
    public void testDrawing(){
        WorldMap map = new WorldMap(10,10);
        map.printToStream(System.out);
        System.out.println("===========");

        map.put(8,8, WallBlock.CHARACTER);

        Tank t = new Tank("testTank", new Coordinates(2,2, new Size(3,3)),Direction.UP);

        TankManager tankManager = new TankManager(t, map);
        tankManager.init(11);

        map.printToStream(System.out);
        System.out.println("===========");

        map.cleanTheWorld();

        t.setCoordinates(new Coordinates(2,2, new Size(3,3)));
        t.setDirection(Direction.LEFT);
        tankManager.drawTank();
        map.printToStream(System.out);
    }

}