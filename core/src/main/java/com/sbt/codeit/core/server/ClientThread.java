package com.sbt.codeit.core.server;

import com.sbt.codeit.server.controller.GameController;
import com.sbt.codeit.server.controller.TankController;
import com.sbt.codeit.server.provider.ClientInputParser;
import com.sbt.codeit.core.Command;

import java.io.*;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class ClientThread implements Runnable {
    private BufferedReader reader;
    private PrintWriter writer;
    private GameController gameController;
    private ClientInputParser clientInputParser;
    private String tankName;

    public ClientThread(GameController gameController, InputStream in, OutputStream out) {
        this.gameController = gameController;
        reader = new BufferedReader(new InputStreamReader(in));
        writer = new PrintWriter(out, false);
        clientInputParser = new ClientInputParser();
    }

    @Override
    public void run() {
        //client registration
        String input = getFromClient();
        tankName = clientInputParser.parseClientRegistration(input);
        if (gameController.register(tankName)) {
            //registration successful
            sendToClient(Command.REGISTERATION.toString() + tankName);
            //wait for game start
            while (!gameController.isGameStarted()) {
            }
            while (true) {
                //send map
                gameController.printState(writer);
                //read command from input stream
                input = getFromClient();
                Command command = clientInputParser.parseCommand(input);
                //perform tank move (parse command)
                TankController tankController = gameController.getTankController(tankName);
                tankController.executeCommand(command);

            }
        }


    }

    private String getFromClient() {
        String line = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    private void sendToClient(String text) {
        writer.println(text);
        writer.flush();
    }
}
