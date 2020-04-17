package com.example.myrecycler;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "items")
public class Items implements Serializable {
    @ColumnInfo(name = "Name")
    private String Name ;
    @ColumnInfo(name = "Number")
    private String Number ;

    public Items() {
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int Id ;

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
