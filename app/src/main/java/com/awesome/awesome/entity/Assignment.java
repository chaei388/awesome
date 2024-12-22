package com.awesome.awesome.entity;


import com.awesome.awesome.Status;
import com.awesome.awesome.Priority;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Assignment implements Serializable {

    int ID;
    String name;
    LocalDateTime endDateTime;
    Status status;
    Priority priority;

    public Assignment(String name, LocalDateTime endDateTime) {
        this.name = name;
        this.endDateTime = endDateTime;
        status = Status.WAITING;
    }

    public Assignment(int ID, String name, LocalDateTime endDateTime, Status status, Priority priority) {
        this.ID = ID;
        this.name = name;
        this.endDateTime = endDateTime;
        this.status = status;
        this.priority = priority;
    }

    public int getID() {
        return ID;
    }

    public void setID(int newID) {
        this.ID = newID;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime newEndDateTime) {
        this.endDateTime = newEndDateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
