package com.sbt.codeit.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Roman on 08.02.2017.
 */
public interface GameController extends Remote {

    void register(String name) throws RemoteException;
    void executeCommand() throws RemoteException;

}
