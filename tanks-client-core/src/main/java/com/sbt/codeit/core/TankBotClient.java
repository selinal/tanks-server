package com.sbt.codeit.core;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by sbt-selin-an on 15.04.2017.
 */
public class TankBotClient {

    Socket socket;

    public TankBotClient(String host, int port) {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean register(String name) {
        String registrationString = TechnicalInfo.REGISTRATION.toString() + name;
        send(registrationString);
        String answer = receive();
        TechnicalInfo info = TechnicalInfo.parseString(answer);
        return info == TechnicalInfo.REGISTRATION_OK;
    }

    private String receive() {
        return null;
    }

    private void send(String registrationString) {

    }
}
