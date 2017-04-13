package com.sbt.codeit.common;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public class TankClientMessage {

    private String clientName;
    private WorldMap map;

    public static TankClientMessage getInstance(String clientName) {
        TankClientMessage tankClientMessage = new TankClientMessage();
        tankClientMessage.setClientName(clientName);
        return tankClientMessage;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public WorldMap getMap() {
        return map;
    }

    public void setMap(WorldMap map) {
        this.map = map;
    }
}
