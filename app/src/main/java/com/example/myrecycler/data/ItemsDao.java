package com.example.myrecycler.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myrecycler.Items;

import java.util.List;

@Dao
public interface ItemsDao {
    @Query("SELECT * FROM Items")
     List<Items> getItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Items items);

    @Delete
    int Delete(Items items);

    @Update
    void update(Items items);
}
