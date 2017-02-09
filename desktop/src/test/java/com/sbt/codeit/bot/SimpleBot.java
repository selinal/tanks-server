package com.sbt.codeit.bot;

import com.sbt.codeit.core.control.GameController;
import com.sbt.codeit.core.control.ServerListener;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roman on 08.02.2017.
 */
public class SimpleBot implements ServerListener {

    private static final String HOST = "localhost";
    private static final int PORT = 2017;
    private static final String STUB_NAME = "GameController";
    private static GameController server;
    private static ServerListener client;

    public static void main(String... args) {
        try {
            SimpleBot simpleBot = new SimpleBot();
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            server = (GameController) registry.lookup(STUB_NAME);
            client = (ServerListener) UnicastRemoteObject.exportObject(simpleBot, 0);
            server.init(client);
            randomMove();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void randomMove() throws Exception {
        Random random = new Random();
        while (true) {
            switch (random.nextInt(4)) {
                case 0:
                    server.moveUp(client);
                    break;
                case 1:
                    server.moveDown(client);
                    break;
                case 2:
                    server.moveLeft(client);
                    break;
                case 3:
                    server.moveRight(client);
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }


    @Override
    public void update(ArrayList<ArrayList<Character>> field) {
        //nothing
    }
}
