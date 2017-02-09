package com.sbt.codeit.core.control;

import com.sbt.codeit.core.model.Tank;
import com.sbt.codeit.core.model.World;

import java.rmi.RemoteException;

import static com.sbt.codeit.core.model.TankDirection.*;
import static com.sbt.codeit.core.model.TankState.MOVING;
import static com.sbt.codeit.core.model.TankState.STAYING;

/**
 * Created by Roman on 08.02.2017.
 */
public class GameControllerImpl implements GameController {

    private World world;

    public GameControllerImpl(World world) {
        this.world = world;
    }

    @Override
    public void register(ServerListener serverListener) throws RemoteException {
        world.addTank(serverListener);
    }

    @Override
    public void start(ServerListener serverListener) throws RemoteException {
        Tank tank = world.getTank(serverListener);
        if(tank != null) {
            tank.setState(MOVING);
        }
    }

    @Override
    public void stop(ServerListener serverListener) throws RemoteException {
        Tank tank = world.getTank(serverListener);
        if(tank != null) {
            tank.setState(STAYING);
        }
    }

    @Override
    public void up(ServerListener serverListener) {
        Tank tank = world.getTank(serverListener);
        if(tank != null) {
            tank.setDirection(UP);
        }
    }

    @Override
    public void down(ServerListener serverListener) throws RemoteException {
        Tank tank = world.getTank(serverListener);
        if(tank != null) {
            tank.setDirection(DOWN);
        }
    }

    @Override
    public void left(ServerListener serverListener) throws RemoteException {
        Tank tank = world.getTank(serverListener);
        if(tank != null) {
            tank.setDirection(LEFT);
        }
    }

    @Override
    public void right(ServerListener serverListener) throws RemoteException {
        Tank tank = world.getTank(serverListener);
        if(tank != null) {
            tank.setDirection(RIGHT);
        }
    }

}
