package pro.sky.telegrambot.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "planner")
public class Planner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chatid")
    private long chatId;

    @Column(name = "task")
    private String task;

    @Column(name = "timetask")
    private LocalDateTime timeTask;

    public long getId() {
        return id;
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

}
