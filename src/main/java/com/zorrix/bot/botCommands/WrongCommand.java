package com.zorrix.bot.botCommands;

import com.zorrix.bot.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class WrongCommand implements Command{
    private final String WRONG_COMMAND_MESSAGE = "This bot can process only commands starts with (/) \n " +
            "type /help to see commands list";
    private final SendBotMessageService sendBotMessageService;

    public WrongCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(WRONG_COMMAND_MESSAGE, update.getMessage().getChatId().toString());
    }
}
