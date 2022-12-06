package pro.sky.telegrambot.record;

import java.time.LocalDateTime;
import java.util.Objects;

public class PlannerRecord {
    private long id;
    private long chatId;
    private String task;
    private LocalDateTime timeTask;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDateTime getTimeTask() {
        return timeTask;
    }

    public void setTimeTask(LocalDateTime timeTask) {
        this.timeTask = timeTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlannerRecord that = (PlannerRecord) o;
        return id == that.id && chatId == that.chatId && Objects.equals(task, that.task) && Objects.equals(timeTask, that.timeTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, task, timeTask);
    }
}
