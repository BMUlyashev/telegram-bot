package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.command.CreateNotifyCommand;
import pro.sky.telegrambot.component.RecordMapper;
import pro.sky.telegrambot.record.PlannerRecord;
import pro.sky.telegrambot.repository.PlannerRepository;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;

@Service
public class CreateNotifyService {

    private final PlannerRepository plannerRepository;
    private final RecordMapper recordMapper;

    private final static String WRONG_FORMAT_DATE = "Неверно задан формат даты и времени запроса!\nПопробуйте изменить запрос";
    private final static String TOO_OLD_DATE = "Указанное Вами время уже прошло\nПопробуйте изменить запрос";

    private final Logger logger = LoggerFactory.getLogger(CreateNotifyService.class);

    public CreateNotifyService(PlannerRepository plannerRepository, RecordMapper recordMapper) {
        this.plannerRepository = plannerRepository;
        this.recordMapper = recordMapper;
    }

    public String createNotify(Update update) {

        Matcher matcher = CreateNotifyCommand.TASK_PATTERN.matcher(update.message().text());
        if (matcher.matches()) {
            PlannerRecord plannerRecord = new PlannerRecord();
            LocalDateTime date;
            try {
                date = parseDateAndTime(matcher.group(1));
            } catch (DateTimeException e) {
                logger.debug("Request DateTime is invalid." + matcher.group(1));
                return WRONG_FORMAT_DATE;
            }
            /* Check the receive date on actual date and time */
            if (checkDateAndTime(date)) {
                plannerRecord.setTimeTask(date);
            } else {
                return TOO_OLD_DATE;
            }

            plannerRecord.setChatId(update.message().chat().id());
            plannerRecord.setTask(matcher.group(3));
            plannerRepository.save(recordMapper.toEntity(plannerRecord));
            return "Запись создана\n"
                    + "Тебе придет напоминание " + date
                    .truncatedTo(ChronoUnit.MINUTES)
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy в HH:mm"));
        }
        return "Неизвестный формат. Напишите /help чтобы узнать что я понимаю.";
    }

    private LocalDateTime parseDateAndTime(String dateTime) throws DateTimeException {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    private boolean checkDateAndTime(LocalDateTime date) {
        return date.isAfter(LocalDateTime.now());
    }
}
