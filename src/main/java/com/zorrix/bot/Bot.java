package com.zorrix.bot;

import com.zorrix.bot.botCommands.CommandContainer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

import static com.zorrix.Constants.*;
import static com.zorrix.bot.botCommands.CommandName.*;

public class Bot extends TelegramLongPollingBot {

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
        //function starting, if bot receives any message

        //continue processing if message has text
        if (update.hasMessage() && update.getMessage().hasText()){
            String message = update.getMessage().getText().trim();

            //if received message starts with '/', calling commandContainer function
            if (message.startsWith(COMMAND_PREFIX)){
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else{
                switch (message){
                    //if user pressed any keyButton, handling it
                    case TOMORROW_SCHEDULE_MESSAGE: commandContainer.retrieveCommand(TOMORROW_SCHEDULE_COMMAND.getCommandName()).execute(update); break;
                    case TODAY_SCHEDULE_MESSAGE: commandContainer.retrieveCommand(TODAY_SCHEDULE_COMMAND.getCommandName()).execute(update); break;

                    //if there are no commands or buttons, sending an error message
                    default: commandContainer.retrieveCommand(WRONG_COMMAND.getCommandName()).execute(update);
                }

            }

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
