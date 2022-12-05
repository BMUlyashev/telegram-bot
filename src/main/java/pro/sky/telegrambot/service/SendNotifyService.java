package pro.sky.telegrambot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.component.RecordMapper;
import pro.sky.telegrambot.entity.Planner;
import pro.sky.telegrambot.repository.PlannerRepository;
import pro.sky.telegrambot.sender.TelegramBotSender;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Service
public class SendNotifyService {

    private final TelegramBotSender telegramBotSender;
    private final PlannerRepository plannerRepository;

    private final RecordMapper recordMapper;

    public SendNotifyService(TelegramBotSender telegramBotSender, PlannerRepository plannerRepository, RecordMapper recordMapper) {
        this.telegramBotSender = telegramBotSender;
        this.plannerRepository = plannerRepository;
        this.recordMapper = recordMapper;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void sendNotification() {
        checkPlannerForTask()
                .stream().map(recordMapper::toRecord)
                .forEach(t -> {
                    telegramBotSender.sendMessage(t.getChatId(), t.getTask());
                    plannerRepository.deleteById(t.getId());
                });
    }

    private Collection<Planner> checkPlannerForTask() {
        LocalDateTime time = LocalDateTime.now();
        return plannerRepository.findTask(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
    }

}
