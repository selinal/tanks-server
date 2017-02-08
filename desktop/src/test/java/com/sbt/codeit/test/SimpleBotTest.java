package com.sbt.codeit.test;

import com.sbt.codeit.bot.SimpleBot;
import com.sbt.codeit.java.TanksGameDesktop;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Roman on 08.02.2017.
 */
public class SimpleBotTest {

    @Test
    public void connectToServerTest() {
        TanksGameDesktop.main();
        try {
            SimpleBot.main();
        } catch (Exception ex) {
            assertEquals(null, ex);
        }
    }

}
