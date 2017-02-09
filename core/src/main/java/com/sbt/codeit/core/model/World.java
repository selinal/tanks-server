package com.sbt.codeit.core.model;

import com.badlogic.gdx.utils.Timer;
import com.sbt.codeit.core.control.ServerListener;
import com.sbt.codeit.core.util.MapLoader;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sbt-galimov-rr on 09.02.2017.
 */
public class World {

    private final ConcurrentHashMap<ServerListener, Tank> tanks = new ConcurrentHashMap<>();
    private final ArrayList<ArrayList<Character>> map = MapLoader.getMap();
    private final Random random = new Random();

    public ArrayList<ArrayList<Character>> getMap() {
        return map;
    }

    public void addTank(ServerListener listener) {
        Tank tank;
        switch (random.nextInt(4)) {
            case 0:
                tank = new Tank(0, 0);
                break;
            case 1:
                tank = new Tank(map.get(0).size() - 1, 0);
                break;
            case 2:
                tank = new Tank(0, map.size() - 1);
                break;
            default:
                tank = new Tank(map.get(0).size() - 1, map.size() - 1);
        }
        tank.setType(random.nextInt(3), random.nextInt(3));
        tanks.put(listener, tank);
    }

    public Tank getTank(ServerListener listener) {
        return tanks.get(listener);
    }

    public Collection<Tank> getTanks() {
        return tanks.values();
    }

    public void update() {
        for (Tank tank : tanks.values()) {
            tank.move(map);
            map.get(tank.getPreviousY()).set(tank.getPreviousX(), ' ');
            map.get(tank.getY()).set(tank.getX(), 'T');
        }
        for (ServerListener serverListener : tanks.keySet()) {
            try {
                serverListener.update(map);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void live() {
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                update();
            }
        }, 0, 0.5F);
    }
}
