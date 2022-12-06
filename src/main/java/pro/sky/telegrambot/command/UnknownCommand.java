package pro.sky.telegrambot.command;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.sender.TelegramBotSender;

@Component
public class UnknownCommand implements Command {

    private final TelegramBotSender telegramBotSender;

    private final static String UNKNOWN_COMMAND_MESSAGE = "Не понимаю вас \uD83D\uDE1F, напишите /help чтобы узнать что я понимаю.";

    public UnknownCommand(TelegramBotSender telegramBotSender) {
        this.telegramBotSender = telegramBotSender;
    }


    @Override
    public void execute(Update update) {
        telegramBotSender.sendMessage(update.message().chat().id(), UNKNOWN_COMMAND_MESSAGE);
    }
}
