package com.zorrix.bot.botCommands;

import com.zorrix.bot.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.zorrix.bot.botCommands.CommandName.*;

public class HelpCommand implements Command{

    private final SendBotMessageService sendBotMessageService;

    private final String HELP_MESSAGE = "List of my commands: \n" +
            START_COMMAND.name() + " - start me \n" +
            CHANGE_TIME.name() + " - change time to send schedule \n" +
            TODAY_SCHEDULE_COMMAND.name() + " - send today's schedule \n" +
            TOMORROW_SCHEDULE_COMMAND.name() + " - send tomorrow's schedule";

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(HELP_MESSAGE, update.getMessage().getChatId().toString());
    }
}
