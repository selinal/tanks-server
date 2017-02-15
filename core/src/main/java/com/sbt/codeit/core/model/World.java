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
public class World implements TankExplodeListener {

    private final ConcurrentHashMap<ServerListener, Tank> tanks = new ConcurrentHashMap<>();
    private final ArrayList<ArrayList<Character>> field = FieldHelper.loadField();
    private final Random random = new Random();

    private int currentColor;

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
                updateTanks();
            }
        }, 0, (long) (1 / Tank.SPEED * 1000));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                updateBullets();
            }
        }, 0, (long) (1 / Bullet.SPEED * 1000));
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
        Tank tank = new Tank(this, x, y, name, currentColor, random.nextInt(3));
        currentColor = currentColor < 2 ? currentColor + 1 : 0;
        return tank;
    }

    public Tank getTank(ServerListener listener) {
        return tanks.get(listener);
    }

    public Collection<Tank> getTanks() {
        return tanks.values();
    }

    private void updateTanks() {
        for (Tank tank : tanks.values()) {
            if(tank.getState() == TankState.EXPLODED) {
                continue;
            }
            tank.update(field);
            for (int i = 0; i < Tank.SIZE; i++) {
                for (int j = 0; j < Tank.SIZE; j++) {
                    FieldHelper.clearCell(field, tank.getPreviousX() + j, tank.getPreviousY() + i);
                    FieldHelper.addTankToCell(field, tank.getName(), tank.getX() + j, tank.getY() + i);
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

    @Override
    public void hit(Tank owner, int x, int y) {
        Tank tank = getTankByChar(field.get(y).get(x));
        tank.setState(TankState.EXPLODED);
        for (int i = 0; i < Tank.SIZE; i++) {
            for (int j = 0; j < Tank.SIZE; j++) {
                FieldHelper.clearCell(field, tank.getPreviousX() + j, tank.getPreviousY() + i);
                FieldHelper.clearCell(field, tank.getX() + j, tank.getY() + i);
            }
        }
    }

    private Tank getTankByChar(Character character) {
        for (Tank tank : getTanks()) {
            if(tank.getName().charAt(0) == character.charValue()) {
                return tank;
            }
        }
        throw new IllegalArgumentException();
    }

}
