package com.sbt.codeit.core.model;

import com.badlogic.gdx.math.Vector2;
import com.sbt.codeit.core.ServerListener;
import com.sbt.codeit.core.util.FieldHelper;
import com.sbt.codeit.core.util.IdHelper;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.sbt.codeit.core.util.FieldHelper.FIELD_HEIGHT;
import static com.sbt.codeit.core.util.FieldHelper.FIELD_WIDTH;

/**
 * Created by sbt-galimov-rr on 09.02.2017.
 */
public class World implements TankExplodeListener {

    private final static int HEARTBEAT_DELAY = 100;
    private final ConcurrentHashMap<ServerListener, Tank> tanks = new ConcurrentHashMap<>();
    private final ArrayList<ArrayList<Character>> field = FieldHelper.loadField();
    private final Random random = new Random();

    private int currentColor;
    private int heartBeats = 0;

    public World() {
        currentColor = random.nextInt(3);
    }

    public ArrayList<ArrayList<Character>> getField() {
        return field;
    }

    public void startUpdates() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(heartBeats % 2 == 0) {
                    updateTanks();
                }
                updateBullets();
                heartBeats++;
            }
        }, 0, HEARTBEAT_DELAY);
    }

    public void addTank(ServerListener listener, String name) {
        Tank tank = createRandomTank(name);
        tanks.put(listener, tank);
    }

    private Tank createRandomTank(String name) {
        Tank tank = new Tank(this, IdHelper.getId(name), name, currentColor, random.nextInt(3));
        tank.moveTo(createRandomPosition());
        currentColor = currentColor < 2 ? currentColor + 1 : 0;
        return tank;
    }

    public Tank getTank(ServerListener listener) {
        return tanks.get(listener);
    }

    public Collection<Tank> getTanks() {
        return tanks.values();
    }

    private synchronized void updateTanks() {
        for (Tank tank : tanks.values()) {
            if (tank.getState() == TankState.EXPLODED) {
                continue;
            }
            tank.update(field);
            if(heartBeats % 20 == 0) {
                tank.enableFire();
                heartBeats = 0;
            }
//            synchronized (this) {
                for (int i = 0; i < Tank.SIZE; i++) {
                    for (int j = 0; j < Tank.SIZE; j++) {
                        FieldHelper.clearCell(field, tank.getPreviousX() + j, tank.getPreviousY() + i);
                    }
                }
                for (int i = 0; i < Tank.SIZE; i++) {
                    for (int j = 0; j < Tank.SIZE; j++) {
                        FieldHelper.addTankToCell(field, tank.getId(), tank.getX() + j, tank.getY() + i);
                    }
                }
//            }
        }
        notifyListeners();
    }

    private synchronized void updateBullets() {
        for (Tank tank : tanks.values()) {
            tank.getBullets().stream().filter(Bullet::isAvailable).forEach(bullet -> {
                bullet.update(field);
                if (bullet.isAvailable()) {
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

    @Override
    public synchronized void hit(Tank owner, int x, int y) {
        Tank tank = getTankById(field.get(y).get(x));
        for (int i = 0; i < Tank.SIZE; i++) {
            for (int j = 0; j < Tank.SIZE; j++) {
                FieldHelper.clearCell(field, tank.getPreviousX() + j, tank.getPreviousY() + i);
                FieldHelper.clearCell(field, tank.getX() + j, tank.getY() + i);
            }
        }
        tank.moveTo(createRandomPosition());
        owner.incrementHits();
    }

    private Tank getTankById(Character character) {
        for (Tank tank : getTanks()) {
            if (tank.getId().equals(character)) {
                return tank;
            }
        }
        throw new IllegalArgumentException();
    }

    private Vector2 createRandomPosition() {
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
        return new Vector2(x, y);
    }

}
