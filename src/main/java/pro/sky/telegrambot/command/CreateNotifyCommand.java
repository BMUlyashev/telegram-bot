package pro.sky.telegrambot.command;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.sender.TelegramBotSender;
import pro.sky.telegrambot.service.CreateNotifyService;

import java.util.regex.Pattern;

@Component
public class CreateNotifyCommand implements Command {

    public final static Pattern TASK_PATTERN = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
    public final static String CREATE_NOTIFY_COMMAND = "createNotify";
    private final TelegramBotSender telegramBotSender;


    private final CreateNotifyService createNotifyService;

    public CreateNotifyCommand(TelegramBotSender telegramBotSender, CreateNotifyService createNotifyService) {
        this.telegramBotSender = telegramBotSender;
        this.createNotifyService = createNotifyService;
    }

    @Override
    public void execute(Update update) {
        telegramBotSender.sendMessage(update.message().chat().id(), createNotifyService.createNotify(update));
    }
}
