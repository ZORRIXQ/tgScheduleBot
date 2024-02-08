package com.zorrix.bot;

import com.zorrix.parser.SheetsParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.swing.text.html.parser.Parser;
import java.io.IOException;

public class Bot extends TelegramLongPollingBot {
    private String botName;
    private String botToken;
    private String fileName;

    public Bot(String botToken, String botName, String fileName) {
        super(botToken);
        this.botName = botName;
        this.botToken = botToken;
        this.fileName = fileName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SheetsParser parser = new SheetsParser(this.fileName);
        try {
            DataSearch dataSearcher = new DataSearch(parser.parseSubjects());
            Long userId = update.getMessage().getChatId();
            String userName = update.getMessage().getFrom().getUserName();

            SendMessage sendMessage = SendMessage.builder()
                    .chatId(userId.toString())
                    .text(dataSearcher.findDaySubjects())
                    .build();
            this.sendApiMethod(sendMessage);
        } catch (IOException | InvalidFormatException | TelegramApiException e) {
            throw new RuntimeException(e);
        }

//        if (update.hasMessage() && update.getMessage().hasText()){
    }



    @Override
    public String getBotUsername() {
        return this.botName;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
}
