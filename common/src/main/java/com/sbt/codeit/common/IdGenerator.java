package com.sbt.codeit.common;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public enum IdGenerator {

    INSTANCE(0);

    private int id;

    IdGenerator(int id) {
        this.id = id;
    }

    public synchronized int getId() {
        int ret = id;
        id++;
        return ret;
    }
}
