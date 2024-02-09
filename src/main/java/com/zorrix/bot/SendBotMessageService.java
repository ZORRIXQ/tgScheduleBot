package com.zorrix.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SendBotMessageService {
    private final Bot bot;

    public SendBotMessageService(Bot bot) {
        this.bot = bot;
    }

    public void sendMessage(String messageText, String chatId){
        SendMessage sendingMessage = SendMessage.builder()
                .chatId(chatId)
                .text(messageText)
                .build();

        try {
            this.bot.execute(sendingMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
