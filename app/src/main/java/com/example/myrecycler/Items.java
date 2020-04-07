package com.example.myrecycler;

import java.io.Serializable;

public class Items implements Serializable {

    String Name ;
    String Number ;

    public Items(String name, String number) {
        Name = name;
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
