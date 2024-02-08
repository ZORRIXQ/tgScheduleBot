package com.zorrix.bot.botCommands;

public enum CommandName {
    START_COMMAND("/start"),
    TODAY_SCHEDULE_COMMAND("/today"),
    TOMORROW_SCHEDULE_COMMAND("/tomorrow"),
    CHANGE_TIME("/chooseTime");

    private String commandName;
    CommandName(String s) {
        this.commandName = s;
    }


}
