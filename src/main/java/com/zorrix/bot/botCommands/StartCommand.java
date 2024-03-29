package com.zorrix.bot.botCommands;

import com.zorrix.bot.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
//    private final String START_MESSAGE = "Hello, dear user! type a /choose_time to set a time, bot should send you a schedule";
    private final String START_MESSAGE = "Hello, dear user! Choose an option you need \uD83D\uDC47";

    StartCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(START_MESSAGE, update.getMessage().getChatId().toString());
    }
}
