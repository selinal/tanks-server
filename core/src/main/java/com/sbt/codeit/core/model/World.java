package com.sbt.codeit.core.model;

import com.sbt.codeit.core.control.ServerListener;
import com.sbt.codeit.core.util.FieldHelper;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.sbt.codeit.core.util.FieldHelper.FIELD_HEIGHT;
import static com.sbt.codeit.core.util.FieldHelper.FIELD_WIDTH;

/**
 * Created by sbt-galimov-rr on 09.02.2017.
 */
public class World {

    private final ConcurrentHashMap<ServerListener, Tank> tanks = new ConcurrentHashMap<>();
    private final ArrayList<ArrayList<Character>> field = FieldHelper.loadField();
    private final Random random = new Random();

    public ArrayList<ArrayList<Character>> getField() {
        return field;
    }

    public void startUpdates() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                updateTanks();
            }
        }, 0, (long)(1 / Tank.SPEED * 1000));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                updateBullets();
            }
        }, 0, (long)(1 / Bullet.SPEED * 1000));
    }

    public void addTank(ServerListener listener, String name) {
        Tank tank = createRandomTank(name);
        tanks.put(listener, tank);
    }

    private Tank createRandomTank(String name) {
        int x;
        int y;
        switch (random.nextInt(4)) {
            case 0:
                x = 0;
                y = 0;
                break;
            case 1:
                x = FIELD_WIDTH - Tank.SIZE;
                y = 0;
                break;
            case 2:
                x = 0;
                y = FIELD_HEIGHT - Tank.SIZE;
                break;
            default:
                x = FIELD_WIDTH - Tank.SIZE;
                y = FIELD_HEIGHT - Tank.SIZE;
        }
        return new Tank(x, y, name, random.nextInt(3), random.nextInt(3));
    }

    public Tank getTank(ServerListener listener) {
        return tanks.get(listener);
    }

    public Collection<Tank> getTanks() {
        return tanks.values();
    }

    private void updateTanks() {
        for (Tank tank : tanks.values()) {
            tank.update(field);
            for (int i = 0; i < Tank.SIZE; i++) {
                for (int j = 0; j < Tank.SIZE; j++) {
                    FieldHelper.clearCell(field, tank.getPreviousX() + j, tank.getPreviousY() + i);
                    FieldHelper.addTankToCell(field, tank.getX() + j, tank.getY() + i);
                }
            }
        }
        notifyListeners();
    }

    private void updateBullets() {
        for (Tank tank : tanks.values()) {
            tank.getBullets().stream().filter(Bullet::isAvailable).forEach(bullet -> {
                bullet.update(field);
                if (bullet.isOnTheField() && FieldHelper.isEmpty(field, bullet.getX(), bullet.getY())) {
                    FieldHelper.clearCell(field, bullet.getPreviousX(), bullet.getPreviousY());
                    FieldHelper.addBulletToCell(field, bullet.getX(), bullet.getY());
                }
            });
        }
    }

    private void notifyListeners() {
        for (ServerListener listener : tanks.keySet()) {
            try {
                listener.update(field);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
