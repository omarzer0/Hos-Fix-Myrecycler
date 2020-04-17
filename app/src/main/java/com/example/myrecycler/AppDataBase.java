package com.example.myrecycler;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities ={Items.class} ,version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static volatile  AppDataBase INSTANCE;

    public abstract ItemsDao itemsDao();

    // i removed the instance block cause it makes a bugs until just fix a bug



}
