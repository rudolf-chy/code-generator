package com.yupi.cli.pattern;

import java.util.List;

public class RemoteControl {

    private Command command;

    private List<Command> historyCommands;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        historyCommands.add(command);
        command.execute();
    }
}
