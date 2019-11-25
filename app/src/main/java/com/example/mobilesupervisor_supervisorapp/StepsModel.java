package com.example.mobilesupervisor_supervisorapp;

public class StepsModel {
    long Steps;
    String Date;

    public StepsModel() {
    }

    public StepsModel(String Date, long  Steps) {
        this.Date = Date;
        this.Steps = Steps;
    }
    public long getSteps() {
        return Steps;
    }

    public void setSteps(long Steps) {
        this.Steps = Steps;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
}
