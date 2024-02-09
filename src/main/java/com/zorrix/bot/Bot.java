package com.zorrix.bot;

import com.zorrix.bot.botCommands.CommandContainer;
import com.zorrix.bot.botCommands.WrongCommand;
import com.zorrix.parser.SheetsParserService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

import static com.zorrix.bot.botCommands.CommandName.WRONG_COMMAND;

public class Bot extends TelegramLongPollingBot {
    public static String COMMAND_PREFIX = "/";
    private String botName;
    private String botToken;
    private String fileName;
    private final CommandContainer commandContainer;

    public Bot(String botToken, String botName, String fileName) throws IOException, InvalidFormatException {
        super(botToken);
        this.botName = botName;
        this.botToken = botToken;
        this.fileName = fileName;

        this.commandContainer = new CommandContainer(new SendBotMessageService(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String message = update.getMessage().getText().trim();

            if (message.startsWith(COMMAND_PREFIX)){
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            }
            else
                commandContainer.retrieveCommand(WRONG_COMMAND.getCommandName()).execute(update);
        }
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
