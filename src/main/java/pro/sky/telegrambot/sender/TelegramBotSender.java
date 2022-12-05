package pro.sky.telegrambot.sender;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotSender {

    private final TelegramBot telegramBot;

    private final Logger logger = LoggerFactory.getLogger(TelegramBotSender.class);

    @Autowired
    public TelegramBotSender(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text).parseMode(ParseMode.HTML);
        SendResponse response = telegramBot.execute(message);
        logger.info("SendResponse status is " + response.isOk());
    }

}
