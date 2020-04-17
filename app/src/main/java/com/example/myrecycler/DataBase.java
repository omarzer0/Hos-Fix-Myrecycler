package com.example.myrecycler;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities ={Items.class} ,version = 1)
public abstract class DataBase extends RoomDatabase {

    private static volatile Database INSTANCE;

    public abstract ItemsDao itemsDao();

    public static Database getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "app.db")
                            .allowMainThreadQueries()
                            .build();
            }
        }
        return INSTANCE;
    }

}
