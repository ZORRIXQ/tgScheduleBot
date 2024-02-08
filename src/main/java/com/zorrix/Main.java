package com.zorrix;

import com.zorrix.bot.Bot;
import com.zorrix.bot.DataSearch;
import com.zorrix.parser.DayNSubjects;
import com.zorrix.parser.SheetsParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws TelegramApiException, IOException, InvalidFormatException {
        final String botToken = "6427035917:AAHlKYY1uQ0jyFpFXJOtbmczo8pIemVUYO4";
        final String botName = "PLSchoolScheduleBot";
        final String fileName = "test.xlsx";

        Bot bot = new Bot(botToken, botName, fileName);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        telegramBotsApi.registerBot(bot);

        SheetsParser parser = new SheetsParser(fileName);
        Map<Integer, ArrayList<DayNSubjects>> map = parser.parseSubjects();

        DataSearch searcher = new DataSearch(parser.parseSubjects());
        System.out.println(searcher.findDaySubjects());
    }
}
