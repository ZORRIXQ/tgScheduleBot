package com.zorrix.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static com.zorrix.Constants.TODAY_SCHEDULE_MESSAGE;
import static com.zorrix.Constants.TOMORROW_SCHEDULE_MESSAGE;

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

        //creating keyboard buttons menu
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow1 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton(TODAY_SCHEDULE_MESSAGE);
        KeyboardButton button2 = new KeyboardButton(TOMORROW_SCHEDULE_MESSAGE);

        keyboardRow1.add(button1);
        keyboardRow1.add(button2);
        keyboardRows.add(keyboardRow1);

        keyboardMarkup.setKeyboard(keyboardRows);

        sendingMessage.setReplyMarkup(keyboardMarkup);

        //sending message
        try {
            this.bot.execute(sendingMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
