package com.example.myrecycler;

import java.io.Serializable;

public class Items implements Serializable {

    private String Name ;
    private String Number ;

    public Items(String name, String number) {
        this.Name = name;
        this.Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        this.Number = number;
    }
}
