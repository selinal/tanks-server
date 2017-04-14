package com.sbt.codeit.core.control;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Roman on 08.02.2017.
 */
public interface GameController extends Remote {

    void register(String name) throws RemoteException;
    void start() throws RemoteException;
    void stop() throws  RemoteException;
    void up() throws RemoteException;
    void down() throws RemoteException;
    void left() throws RemoteException;
    void right() throws RemoteException;
    void fire() throws RemoteException;

}
