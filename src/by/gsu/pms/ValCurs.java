package by.gsu.pms;

import java.util.ArrayList;

public class ValCurs {
    private String Date;
    private String Name;
    private ArrayList<Valute> valutes;

    public ValCurs(String date, String name, ArrayList<Valute> valutes) {
        Date = date;
        Name = name;
        this.valutes = valutes;
    }

    public ValCurs() {
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Valute> getValutes() {
        return valutes;
    }

    public void setValutes(ArrayList<Valute> valutes) {
        this.valutes = valutes;
    }
}
