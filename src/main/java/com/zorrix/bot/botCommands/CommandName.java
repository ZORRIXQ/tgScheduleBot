package com.zorrix.bot.botCommands;

public enum CommandName {
    START_COMMAND("/start"),
    TODAY_SCHEDULE_COMMAND("/today"),
    TOMORROW_SCHEDULE_COMMAND("/tomorrow"),
    CHOOSE_TIME_COMMAND("/choose_time"),
    HELP_COMMAND("/help"),
    WRONG_COMMAND("Wrong");

    private String commandName;
    CommandName(String s) {
        this.commandName = s;
    }

    public String getCommandName() {
        return commandName;
    }
}
