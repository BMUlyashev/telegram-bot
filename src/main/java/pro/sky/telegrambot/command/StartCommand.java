package pro.sky.telegrambot.command;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.sender.TelegramBotSender;

@Component
public class StartCommand implements Command {

    private final TelegramBotSender telegramBotSender;

    private final String START_MESSAGE = "Привет! " + "\uD83D\uDC4B\n" +
            "Я BMUlyashevBot. Я могу помочь тебе сохранять твои напоминания и присылать тебе уведомления в то время, " +
            "которое ты указал в запросе. \nНапишите /help чтобы узнать что я понимаю.";

    public StartCommand(TelegramBotSender telegramBotSender) {
        this.telegramBotSender = telegramBotSender;
    }

    @Override
    public void execute(Update update) {
        telegramBotSender.sendMessage(update.message().chat().id(), START_MESSAGE);
    }
}
