package com.sbt.codeit.server;

import java.util.Random;

/**
 * Created by sbt-selin-an on 13.04.2017.
 */
public enum GeneratorHelper {

    INSTANCE(0);

    private volatile int id;
    private static char[] chars = "ABCDEFGHIJKLMNPQRSTUWXYZ".toCharArray();

    GeneratorHelper(int id) {
        this.id = id;
    }

    public synchronized int getId() {
        int ret = id;
        id++;
        return ret;
    }

    public synchronized Character getCharacter(){
        Random random = new Random();
        return chars[random.nextInt(chars.length)];
    }
}
