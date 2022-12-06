package pro.sky.telegrambot.component;

import org.springframework.stereotype.Component;
import pro.sky.telegrambot.entity.Planner;
import pro.sky.telegrambot.record.PlannerRecord;

import java.time.temporal.ChronoUnit;

@Component
public class RecordMapper {

    public Planner toEntity(PlannerRecord plannerRecord) {
        Planner planner = new Planner();
        planner.setChatId(plannerRecord.getChatId());
        planner.setTask(plannerRecord.getTask());
        planner.setTimeTask(plannerRecord.getTimeTask().truncatedTo(ChronoUnit.MINUTES));
        return planner;
    }

    public PlannerRecord toRecord(Planner planner) {
        PlannerRecord plannerRecord = new PlannerRecord();
        plannerRecord.setTask(planner.getTask());
        plannerRecord.setTimeTask(planner.getTimeTask());
        plannerRecord.setChatId(planner.getChatId());
        plannerRecord.setId(planner.getId());
        return plannerRecord;
    }
}
