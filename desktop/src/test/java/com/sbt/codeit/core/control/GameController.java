package com.sbt.codeit.core.control;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Roman on 08.02.2017.
 */
public interface GameController extends Remote {

    void moveUp() throws RemoteException;

}
