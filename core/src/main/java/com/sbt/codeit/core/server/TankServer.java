package com.sbt.codeit.core.server;

import com.sbt.codeit.core.util.WorldMapManager;

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
    private List<Runnable> tanktForBattle;


    public void init() {
        try {
            //init server
            ServerSocket servers = new ServerSocket(SERVER_PORT);
            ExecutorService pool = Executors.newFixedThreadPool(TANKS_COUNT + 1);

            //init map
            WorldMapManager mapManager = new WorldMapManager();

            //init tanks
            tanktForBattle = new ArrayList<>(TANKS_COUNT);
            int acc = 0;
            while (true) {
                //waiting for client connections
                System.out.print("Waiting for tanks...");
                Socket clientSocket = servers.accept();
                System.out.println("Client connected");

                Runnable tankThread = new ClientThread(mapManager, clientSocket.getInputStream(), clientSocket.getOutputStream());
                tanktForBattle.add(tankThread);
                acc++;
                if (acc >= TANKS_COUNT)
                    break;
            }
            //Start battle
            for (Runnable r : tanktForBattle) {
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
