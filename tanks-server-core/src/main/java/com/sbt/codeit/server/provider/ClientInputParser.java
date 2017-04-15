package com.sbt.codeit.server.provider;

import com.sbt.codeit.core.Command;

/**
 * Created by sbt-selin-an on 15.04.2017.
 */
public class ClientInputParser {

    public String parseClientRegistration(String input) {
        if (input.contains(Command.REGISTERATION.toString())) {
            return input.substring(input.indexOf("=") + 1);
        }
        return null;
    }

    public Command parseCommand(String input) {
        String command = input.substring(input.indexOf("=") + 1);
        return Command.valueOf(command);
    }
}
