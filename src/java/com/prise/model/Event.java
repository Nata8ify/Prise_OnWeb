/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.model;

/**
 *
 * @author QuartierLatin
 */
public class Event {
    private String event;
    private String description;
    private int eventId;
    private int userId;

    public Event(String event, String description, int eventId, int userId) {
        this.event = event;
        this.description = description;
        this.eventId = eventId;
        this.userId = userId;
    }

    public Event() {
    }
    
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Event{" + "event=" + event + ", description=" + description + ", eventId=" + eventId + ", userId=" + userId + '}';
    }
    
    
}
