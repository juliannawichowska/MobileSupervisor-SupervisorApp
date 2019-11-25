package com.example.mobilesupervisor_supervisorapp;

public class HeartRateModel {
    int Pulse;
    String Date;

    public HeartRateModel() {
    }

    public HeartRateModel (String Date, int Pulse) {
        this.Date = Date;
        this.Pulse = Pulse;
    }
    public long getPulse() {
        return Pulse;
    }

    public void setPulse(int Pulse) {
        this.Pulse = Pulse;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
}
