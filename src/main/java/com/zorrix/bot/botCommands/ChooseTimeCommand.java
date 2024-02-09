package com.zorrix.bot.botCommands;

import com.zorrix.bot.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ChooseTimeCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public ChooseTimeCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void execute(Update update) {
        String TEXT_SEND = "Not available now";
        sendBotMessageService.sendMessage(TEXT_SEND, update.getMessage().getChatId().toString());
    }
}
