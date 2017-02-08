package com.sbt.codeit.bot;

import com.sbt.codeit.core.control.GameController;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Roman on 08.02.2017.
 */
public class SimpleBot {

    private static final String HOST = "localhost";
    private static final int PORT = 2017;
    private static final String STUB_NAME = "GameController";

    public static void main(String... args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST, PORT);
        GameController stub = (GameController) registry.lookup(STUB_NAME);
        stub.moveUp();
    }

}
