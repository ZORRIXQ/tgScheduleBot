package com.zorrix;

import com.zorrix.bot.Bot;
import com.zorrix.bot.DataSearchService;
import com.zorrix.parser.DayNSubjects;
import com.zorrix.parser.SheetsParserService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.*;

import static com.zorrix.Constants.*;

public class Main {
    public static void main(String[] args) throws TelegramApiException, IOException, InvalidFormatException {
        Calendar calendar = Calendar.getInstance();

        Bot bot = new Bot(botToken, botName, fileName);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        telegramBotsApi.registerBot(bot);

        SheetsParserService parser = new SheetsParserService();
        Map<Integer, ArrayList<DayNSubjects>> map = parser.parseSubjects();

        DataSearchService searcher = new DataSearchService(parser.parseSubjects());
        System.out.println(searcher.findDaySubjects(true));
        System.out.println(searcher.findDaySubjects(false));
    }
}
