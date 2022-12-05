package pro.sky.telegrambot.command;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.sender.TelegramBotSender;

@Component
public class HelpCommand implements Command {

    private final TelegramBotSender telegramBotSender;

    public static final String HELP_MESSAGE = "Я принимаю запрос в формате:\ndd.mm.yyyy hh:mm твое напоминание\n"
            + "Пример:\n"
            + "04.12.2022 18:45 Позвонить в автосервис\n";


    public HelpCommand(TelegramBotSender telegramBotSender) {
        this.telegramBotSender = telegramBotSender;
    }

    @Override
    public void execute(Update update) {
        telegramBotSender.sendMessage(update.message().chat().id(), HELP_MESSAGE);
    }
}
