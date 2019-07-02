package com.keshar.barchartdemo.com.keshar.barchartdemo.model;

public class Model {
    private String day;
    private Integer trip;

    public Model(String day, Integer trip) {
        this.day = day;
        this.trip = trip;
    }

    public String getDay() {
        return day;
    }

    public Integer getTrip() {
        return trip;
    }
}
