package by.gsu.lab9;

import by.gsu.pms.Forecast;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Runner {
    public static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=Moscow,ru&appid=d46c47eac54879f975bcbe5c18c24353&units=metric";
    public static final String ICON_URL_PATTERN = "http://openweathermap.org/img/w/";

    public static void main(String[] args) {
        Gson gson = new Gson();
        Forecast forecast = null;
        try {
            System.out.println("Pleas wait...");
            URL url = new URL(URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("User-Agent", "Chrome");
            InputStreamReader reader = new InputStreamReader(httpURLConnection.getInputStream());
            forecast = gson.fromJson(reader, Forecast.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (forecast != null) {
            generateHTMLWidget(forecast);
        }
    }

    public static void generateHTMLWidget(Forecast forecast) {
        String windDirection;
        if (forecast.getWind().getDeg() == 0) {
            windDirection = "W";
        } else if (forecast.getWind().getDeg() == 90) {
            windDirection = "N";
        } else if (forecast.getWind().getDeg() == 180) {
            windDirection = "E";
        } else if (forecast.getWind().getDeg() == 270) {
            windDirection = "S";
        } else if (forecast.getWind().getDeg() > 0 && forecast.getWind().getDeg() < 90) {
            windDirection = "NW";
        } else if (forecast.getWind().getDeg() > 90 && forecast.getWind().getDeg() < 180) {
            windDirection = "NE";
        } else if (forecast.getWind().getDeg() > 180 && forecast.getWind().getDeg() < 270) {
            windDirection = "SE";
        } else if (forecast.getWind().getDeg() > 270 && forecast.getWind().getDeg() < 360) {
            windDirection = "SW";
        } else {
            windDirection = "Incorrect wind direction";
        }


        String iconNumber = forecast.getWeather()[0].getIcon();
        Image icon;
        File outputFIle = new File("./resources/icon.png");
        try (OutputStream outputStream = new FileOutputStream(outputFIle)) {

            URL iconURL = new URL(ICON_URL_PATTERN + iconNumber + ".png");
            icon = ImageIO.read(iconURL);
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            ImageIO.write((RenderedImage) icon, "png", imageOutputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File f = new File("./resources/index.html");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f))) {
            System.out.println(">>> Creating forecast index.html file...");
            bufferedWriter.write(
                    "<!DOCTYPE html>" +
                            "<html lang=\"en\">" +
                            "   <head>" +
                            "       <meta charset=\"UTF-8\">" +
                            "       <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                            "       <title>Прогноз погоды</title>" +
                            "   </head>" +
                            "   <body>" +
                            "       <h1>Прогноз погоды</h1>" +
                            "       <p>Название города: " + forecast.getName() + "</p>" +
                            "       <p>Код страны: " + forecast.getSys().getCountry() + "</p>" +
                            "       <img src=\"icon.png\" alt=\"Forecast icon\">" +
                            "       <p>Краткое описание погоды: " + forecast.getWeather()[0].getMain() + "</p>" +
                            "       <p>Полное описание погоды: " + forecast.getWeather()[0].getDescription() + "</p>" +
                            "       <p>Текущая температура в градусах Цельсия: " + forecast.getMain().getTemp() + " ℃</p>" +
                            "       <p>Давление в мм ртутного столба: " + forecast.getMain().getPressure() + " мм. рт. ст.</p>" +
                            "       <p>Влажность в процентах: " + forecast.getMain().getHumidity() + "%</p>" +
                            "       <p>Минимальная температура за сегодня: " + forecast.getMain().getTemp_min() + " ℃</p>" +
                            "       <p>Максимальная температура за сегодня: " + forecast.getMain().getTemp_max() + " ℃</p>" +
                            "       <p>Скорость и направление ветра: " + forecast.getWind().getSpeed() + " м/c " + windDirection + "</p>" +
                            "       <p>Облачность в процентах: " + forecast.getClouds().getAll() + "%</p>" +
                            "   </body>" +
                            "</html>"
            );
            System.out.println(">>> File saved to ./resources/index.html");
        } catch (Exception ex) {
            System.out.println(">>> Error occurred during markup save");
            ex.printStackTrace();
        }

    }

}
