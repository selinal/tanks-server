package com.sbt.codeit.core.control;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by sbt-galimov-rr on 09.02.2017.
 */
public interface ServerListener extends Remote {

    public static final String HOST = "localhost";
    public static final int PORT = 2017;
    public static final String STUB_NAME = "GameController";

    void update(ArrayList<ArrayList<Character>> field) throws RemoteException;

}
