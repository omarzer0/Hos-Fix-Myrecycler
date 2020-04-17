package com.example.myrecycler;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "Items")
public class Items  {
@ColumnInfo(name = "Name")
    private String Name ;
@PrimaryKey(autoGenerate = false)
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
