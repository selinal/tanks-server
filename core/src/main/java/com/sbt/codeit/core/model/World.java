package com.sbt.codeit.core.model;

import com.badlogic.gdx.utils.Timer;
import com.sbt.codeit.core.control.ServerListener;
import com.sbt.codeit.core.util.MapHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import static com.sbt.codeit.core.model.Tank.SIZE;

/**
 * Created by sbt-galimov-rr on 09.02.2017.
 */
public class World {

    private final ConcurrentHashMap<ServerListener, Tank> tanks = new ConcurrentHashMap<>();
    private final ArrayList<ArrayList<Character>> map = MapHelper.getMap();
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
                tank = new Tank(map.get(0).size() - SIZE, 0);
                break;
            case 2:
                tank = new Tank(0, map.size() - SIZE);
                break;
            default:
                tank = new Tank(map.get(0).size() - SIZE, map.size() - SIZE);
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

    private void update() {
        for (Tank tank : tanks.values()) {
            tank.update(map);
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    map.get(tank.getPreviousY() + i).set(tank.getPreviousX() + j, ' ');
                    map.get(tank.getY() + i).set(tank.getX() + j, 'T');
                }
            }
        }
        notifyListeners();
    }

    private void notifyListeners() {
        for (ServerListener listener : tanks.keySet()) {
            try {
                listener.update(map);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateBullets() {
        for (Tank tank : tanks.values()) {
            tank.getBullets().stream().filter(Bullet::isAvailable).forEach(bullet -> {
                bullet.update();
                if (bullet.getX() > 0 && bullet.getY() > 0 && bullet.getY() < map.size() && bullet.getX() < map.get(0).size()) {
                    if (MapHelper.isEmpty(map, bullet.getX(), bullet.getY())) {
                        map.get(bullet.getPreviousY()).set(bullet.getPreviousX(), ' ');
                        map.get(bullet.getY()).set(bullet.getX(), 'x');
                    } else {
                        bullet.explode(map);
                    }
                } else {
                    bullet.setAvailable(false);
                }
            });
        }
    }

    public void live () {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    update();
                }
            }, 0, 0.12F);
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    updateBullets();
                }
            }, 0, 0.10F);
        }

    }
