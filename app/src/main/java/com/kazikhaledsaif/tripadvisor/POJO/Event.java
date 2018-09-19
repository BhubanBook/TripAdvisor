package com.kazikhaledsaif.tripadvisor.POJO;

public class Event {
    private String eventId;
    private String userId;
    private String eventDesc;
    private double eventBudget;
    private String eventFromDate;
    private String eventToDate;

    public Event() {
    }

    public Event(String eventId, String userId, String eventDesc, double eventBudget, String eventFromDate, String eventToDate) {
        this.eventId = eventId;
        this.userId = userId;
        this.eventDesc = eventDesc;
        this.eventBudget = eventBudget;
        this.eventFromDate = eventFromDate;
        this.eventToDate = eventToDate;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public double getEventBudget() {
        return eventBudget;
    }

    public void setEventBudget(double eventBudget) {
        this.eventBudget = eventBudget;
    }

    public String getEventFromDate() {
        return eventFromDate;
    }

    public void setEventFromDate(String eventFromDate) {
        this.eventFromDate = eventFromDate;
    }

    public String getEventToDate() {
        return eventToDate;
    }

    public void setEventToDate(String eventToDate) {
        this.eventToDate = eventToDate;
    }
}
