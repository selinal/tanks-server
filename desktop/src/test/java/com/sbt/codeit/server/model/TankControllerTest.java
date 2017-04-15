package com.sbt.codeit.server.model;

import com.sbt.codeit.server.WorldMap;
import com.sbt.codeit.server.controller.TankController;
import org.junit.Test;

/**
 * Created by sbt-selin-an on 14.04.2017.
 */
public class TankControllerTest {

    @Test
    public void testDrawing(){
        WorldMap map = new WorldMap(10,10);
        map.printToStream(System.out);
        System.out.println("===========");

        map.put(8,8, WallBlock.CHARACTER);

        Tank t = new Tank("testTank", new Coordinates(2,2, new Size(3,3)),Direction.UP);

        TankController tankController = new TankController(t, map);
        tankController.init(11);

        map.printToStream(System.out);
        System.out.println("===========");

        map.cleanTheWorld();

        t.setCoordinates(new Coordinates(2,2, new Size(3,3)));
        t.setDirection(Direction.LEFT);
        tankController.drawTank();
        map.printToStream(System.out);

        System.out.println(t);
    }

}