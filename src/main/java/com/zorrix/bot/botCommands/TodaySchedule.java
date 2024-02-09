package com.zorrix.bot.botCommands;

import com.zorrix.Constants;
import com.zorrix.bot.DataSearchService;
import com.zorrix.bot.SendBotMessageService;
import com.zorrix.parser.SheetsParserService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public class TodaySchedule implements Command{
    private String fileName;

    private final SendBotMessageService sendBotMessageService;

    TodaySchedule(SendBotMessageService sendBotMessageService) throws IOException, InvalidFormatException {
        this.fileName = fileName;
        this.sendBotMessageService = sendBotMessageService;
    }

    SheetsParserService parser = new SheetsParserService();
    DataSearchService searcher = new DataSearchService(parser.parseSubjects());
    private final String TODAY_SCHEDULE = searcher.findDaySubjects(false);


    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(TODAY_SCHEDULE, update.getMessage().getChatId().toString());
    }
}
