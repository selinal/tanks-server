package com.sbt.codeit.core.control;

import com.sbt.codeit.core.model.World;
import java.rmi.RemoteException;

import static com.sbt.codeit.core.model.TankDirection.*;

/**
 * Created by Roman on 08.02.2017.
 */
public class GameControllerImpl implements GameController {

    private World world;

    public GameControllerImpl(World world) {
        this.world = world;
    }

    @Override
    public void init(ServerListener serverListener) throws RemoteException {
        world.addTank(serverListener);
    }

    @Override
    public void moveUp(ServerListener serverListener) {
        world.getTank(serverListener).setDirection(UP);
    }

    @Override
    public void moveDown(ServerListener serverListener) throws RemoteException {
        world.getTank(serverListener).setDirection(DOWN);
    }

    @Override
    public void moveLeft(ServerListener serverListener) throws RemoteException {
        world.getTank(serverListener).setDirection(LEFT);
    }

    @Override
    public void moveRight(ServerListener serverListener) throws RemoteException {
        world.getTank(serverListener).setDirection(RIGHT);
    }


}
