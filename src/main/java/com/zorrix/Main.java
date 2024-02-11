package com.zorrix;

import com.zorrix.bot.Bot;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

import static com.zorrix.Constants.*;

/*
* @author ZORRIXQ
 */

public class Main {
    public static void main(String[] args) throws TelegramApiException, IOException, InvalidFormatException {
        Bot bot = new Bot(Constants.BOT_TOKEN, BOT_NAME, Constants.FILE_NAME);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        //starting the bot
        telegramBotsApi.registerBot(bot);

        System.out.println("Bot is working!");
    }
}
