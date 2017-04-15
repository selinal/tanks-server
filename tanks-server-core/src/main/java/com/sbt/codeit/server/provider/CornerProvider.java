package com.sbt.codeit.server.provider;

import java.util.Random;

/**
 * Created by sbt-selin-an on 15.04.2017.
 */
public class CornerProvider {
    private Random random = new Random();
    private boolean[] corners = new boolean[4];

    public int getCorner() {

        int c = 0;
        int tryCount = 4;
        while(corners[c] != true || tryCount != 0) {
            c = random.nextInt() % 4;
            tryCount--;
        }
        return c;
    }
}
