package com.zorrix.bot.botCommands;

import com.zorrix.bot.SendBotMessageService;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.zorrix.bot.botCommands.CommandName.*;

public class CommandContainer {
    private final Map<String, Command> commandMap;
    private final Command wrongCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) throws IOException, InvalidFormatException {

        this.commandMap = Collections.unmodifiableMap(Map.of(
                START_COMMAND.getCommandName(), new StartCommand(sendBotMessageService),
                TODAY_SCHEDULE_COMMAND.getCommandName(), new TodaySchedule(sendBotMessageService),
                TOMORROW_SCHEDULE_COMMAND.getCommandName(), new TomorrowSchedule(sendBotMessageService),
                CHOOSE_TIME_COMMAND.getCommandName(), new ChooseTimeCommand(sendBotMessageService),
                HELP_COMMAND.getCommandName(), new HelpCommand(sendBotMessageService),
                WRONG_COMMAND.getCommandName(), new WrongCommand(sendBotMessageService)
                ));

        this.wrongCommand = new WrongCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier){
        return commandMap.getOrDefault(commandIdentifier, wrongCommand);
    }
}
