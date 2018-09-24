package com.kazikhaledsaif.tripadvisor.POJO;

public class Expense {

    private String expanseId;
    private String userId;

    private String eventName;
    private String expanseDetails;
    private double expanseAmount;
    private String expanseDate;

    public Expense(String expanseId, String userId, String eventName, String expanseDetails, double expanseAmount, String expanseDate) {
        this.expanseId = expanseId;
        this.userId = userId;
        this.eventName = eventName;
        this.expanseDetails = expanseDetails;
        this.expanseAmount = expanseAmount;
        this.expanseDate = expanseDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Expense() {
    }

    public String getExpanseId() {
        return expanseId;
    }

    public void setExpanseId(String expanseId) {
        this.expanseId = expanseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpanseDetails() {
        return expanseDetails;
    }

    public void setExpanseDetails(String expanseDetails) {
        this.expanseDetails = expanseDetails;
    }

    public double getExpanseAmount() {
        return expanseAmount;
    }

    public void setExpanseAmount(double expanseAmount) {
        this.expanseAmount = expanseAmount;
    }

    public String getExpanseDate() {
        return expanseDate;
    }

    public void setExpanseDate(String expanseDate) {
        this.expanseDate = expanseDate;
    }
}
