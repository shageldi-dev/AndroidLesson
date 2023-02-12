package com.shageldi.androidlessons.Model.Api;

public class Wind {
    private Double wind;
    private Double deg;

    public Wind(Double wind, Double deg) {
        this.wind = wind;
        this.deg = deg;
    }

    public Double getWind() {
        return wind;
    }

    public void setWind(Double wind) {
        this.wind = wind;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }
}
