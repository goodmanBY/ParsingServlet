package com.savko.fifth.holdel;


import com.savko.fifth.command.Command;
import com.savko.fifth.command.DomCommand;
import com.savko.fifth.command.SaxCommand;
import com.savko.fifth.command.StaxCommand;

import java.util.HashMap;

public class CommandHolder {

    private static final CommandHolder INSTANCE = new CommandHolder();

    public HashMap<String, Command> commands;

    private CommandHolder() {
        commands = new HashMap<>();
        commands.put("dom", new DomCommand());
        commands.put("sax", new SaxCommand());
        commands.put("stax", new StaxCommand());
    }

    public static CommandHolder getInstance(){
        return INSTANCE;
    }

    public Command getCommand(String parser){
        return commands.get(parser);
    }

}
