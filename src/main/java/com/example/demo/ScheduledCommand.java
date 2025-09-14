package com.example.demo;

import java.time.LocalDateTime;

public class ScheduledCommand {
    public enum Type { ONE_TIME, RECURRING }

    public Type type;
    public LocalDateTime scheduledTime; // for one-time
    public int intervalMinutes;         // for recurring
    public String command;

    public ScheduledCommand(Type type, LocalDateTime time, int interval, String command) {
        this.type = type;
        this.scheduledTime = time;
        this.intervalMinutes = interval;
        this.command = command;
    }
}
