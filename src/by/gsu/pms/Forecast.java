package by.gsu.pms;

public class Forecast {
    private Weather[] weather;
    private MainInfo main;
    private Wind wind;
    private Clouds clouds;
    private SystemInfo sys;
    private String name;

    public Forecast(Weather[] weather, MainInfo main, Wind wind, Clouds clouds, SystemInfo sys, String name) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.sys = sys;
        this.name = name;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public SystemInfo getSys() {
        return sys;
    }

    public void setSys(SystemInfo sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }
}