package com.sbt.codeit.core.control;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Roman on 08.02.2017.
 */
public interface GameController extends Remote {

    void init(ServerListener serverListener) throws RemoteException;
    void moveUp(ServerListener serverListener) throws RemoteException;
    void moveDown(ServerListener serverListener) throws RemoteException;
    void moveLeft(ServerListener serverListener) throws RemoteException;
    void moveRight(ServerListener serverListener) throws RemoteException;

}
