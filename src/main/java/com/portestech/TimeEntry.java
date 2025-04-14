package com.portestech;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeEntry {

    private String employeeName;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public TimeEntry(String employeeName) {
        this.employeeName = employeeName;
        this.entryTime = LocalDateTime.now();
    }

    public void exitTime() {
        this.exitTime = LocalDateTime.now();
    }

    public long calculateWorkedHours() {
        return Duration.between(entryTime, exitTime).toHours();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }
}
