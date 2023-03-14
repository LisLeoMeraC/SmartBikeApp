package com.dist.smartbike.model;

import java.time.LocalDateTime;

public class Monitoring {
    private Integer id;
    private Integer speed_max;
    private Integer speed_min;
    private Integer altitude;
    private Double temperature;
    private Integer travel_time;
    private LocalDateTime dateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpeed_max() {
        return speed_max;
    }

    public void setSpeed_max(Integer speed_max) {
        this.speed_max = speed_max;
    }

    public Integer getSpeed_min() {
        return speed_min;
    }

    public void setSpeed_min(Integer speed_min) {
        this.speed_min = speed_min;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getTravel_time() {
        return travel_time;
    }

    public void setTravel_time(Integer travel_time) {
        this.travel_time = travel_time;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
