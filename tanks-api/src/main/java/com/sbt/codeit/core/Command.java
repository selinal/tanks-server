package com.sbt.codeit.core;

/**
 * Created by sbt-selin-an on 15.04.2017.
 */
public enum Command {

    REGISTRATION,
    START,
    STOP,
    UP,
    DOWN,
    LEFT,
    RIGHT,
    FIRE,
    NONE;

    @Override
    public String toString() {
        return this == REGISTRATION ? "client." + this + "=" : "client.command=" + this;
    }
}
