package com.sbt.codeit.core.server;

import com.sbt.codeit.server.controller.GameController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class TankServer {

    public static int SERVER_PORT = Integer.parseInt(System.getProperties().getProperty("server.port"));
    public static int TANKS_COUNT = Integer.parseInt(System.getProperties().getProperty("tanks.count"));
    private List<Runnable> tanktThreads;
    private volatile GameController gameController;


    public void init() {
        try {
            //init server
            ServerSocket servers = new ServerSocket(SERVER_PORT);
            ExecutorService pool = Executors.newFixedThreadPool(10);

            //init map
            gameController = new GameController();

            //init tanks
            tanktThreads = new ArrayList<>(TANKS_COUNT);
            int acc = 0;
            while (true) {
                //waiting for client connections
                System.out.println("Server started...");
                System.out.println("Waiting for tanks...");
                Socket clientSocket = servers.accept();
                System.out.println("Client connected");

                Runnable tankThread = new ClientThread(gameController, clientSocket.getInputStream(), clientSocket.getOutputStream());
                tanktThreads.add(tankThread);
                acc++;
                if (acc >= TANKS_COUNT)
                    break;
            }
            //Start tank threads
            for (Runnable r : tanktThreads) {
                pool.execute(r);
            }
            //Check winner

        } catch (IOException e) {
            System.err.println("Couldn't start server and listen to port " + SERVER_PORT);
            System.exit(-1);
        }

    }

    public static void main(String[] args) throws IOException {

    }

}
