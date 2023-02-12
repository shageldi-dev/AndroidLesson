package com.shageldi.androidlessons.Model.Api;

import java.util.ArrayList;

public class GetWeather {
    private Cord coord;
    private ArrayList<Weather> weather=new ArrayList<>();
    private String base;
    private Weather.Main main;
    private Integer visibility;
    private Wind wind;

    public GetWeather(Cord coord, ArrayList<Weather> weather, String base, Weather.Main main, Integer visibility, Wind wind) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
    }

    public Cord getCoord() {
        return coord;
    }

    public void setCoord(Cord coord) {
        this.coord = coord;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Weather.Main getMain() {
        return main;
    }

    public void setMain(Weather.Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
