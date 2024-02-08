package com.zorrix.bot;

import com.zorrix.parser.SheetsParserService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        SheetsParserService parser = new SheetsParserService(this.fileName);
        try {
            DataSearchService dataSearcherService = new DataSearchService(parser.parseSubjects());
            Long userId = update.getMessage().getChatId();
            String userName = update.getMessage().getFrom().getUserName();

            SendMessage sendMessage = SendMessage.builder()
                    .chatId(userId.toString())
                    .text(dataSearcherService.findDaySubjects(false))
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
