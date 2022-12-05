package pro.sky.telegrambot.command;

import org.springframework.stereotype.Component;
import pro.sky.telegrambot.sender.TelegramBotSender;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

@Component
public class CommandContainer {

    private final Map<String, Command> commandMap;

    private final TelegramBotSender telegramBotSender;
    private final Command createNotifyCommand;
    private final Command startCommand;
    private final Command helpCommand;
    private final Command unknownCommand;


    public CommandContainer(TelegramBotSender telegramBotSender,
                            Command createNotifyCommand,
                            Command startCommand,
                            Command helpCommand,
                            Command unknownCommand) {
        this.telegramBotSender = telegramBotSender;
        this.createNotifyCommand = createNotifyCommand;
        this.startCommand = startCommand;
        this.helpCommand = helpCommand;
        this.unknownCommand = unknownCommand;

        commandMap = new HashMap<>();
        commandMap.put("/start", startCommand);
        commandMap.put("/help", helpCommand);

        commandMap.put(CreateNotifyCommand.CREATE_NOTIFY_COMMAND, createNotifyCommand);

    }

    public Command retrieveCommand(String commandIdentifier) {
        Matcher taskPatternMatcher = CreateNotifyCommand.TASK_PATTERN.matcher(commandIdentifier);       // Check for incoming String set task command
        if (taskPatternMatcher.matches()) {
            return commandMap.getOrDefault(CreateNotifyCommand.CREATE_NOTIFY_COMMAND, unknownCommand);
        } else {
            return commandMap.getOrDefault(commandIdentifier, unknownCommand);
        }
    }
}
