package com.sbt.codeit.server.controller;

import com.sbt.codeit.server.WorldMap;
import com.sbt.codeit.server.model.Tank;
import com.sbt.codeit.server.provider.CornerProvider;
import com.sbt.codeit.server.provider.MapProvider;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbt-selin-an on 15.04.2017.
 */
public class GameController {

    private volatile WorldMap map;
    private List<TankController> tanks;
    private List<BulletController> bullets;
    private CornerProvider cornerProvider;
    private List<String> tankNames;
    private volatile boolean gameStarted;

    public GameController() {
        tanks = new ArrayList<>(4);
        bullets = new ArrayList<>(32);
        map = new MapProvider().getMap();
        cornerProvider = new CornerProvider();
        this.tankNames = new ArrayList<>();
    }

    public synchronized boolean register(String name) {
        for (String tankName : tankNames) {
            if (tankName.equals(name))
                return false;
        }
        tankNames.add(name);
        if (tankNames.size() >= 2)
            gameStarted = true;
        return true;
    }

/*    public void startGame() {
        for (String tankName : tankNames) {
            TankController tankController = new TankController(new Tank(tankName), map);
            tanks.add(tankController);
            tankController.init(cornerProvider.getCorner());
        }
    }*/

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void printState(PrintWriter writer) {

        writer.println("...");//TODO add output
    }

    public TankController getTankController(String tankName) {
        for (TankController tc : tanks) {
            if (tc.getTankName().equals(tankName)) {
                return tc;
            }
        }
        return null;
    }
}
