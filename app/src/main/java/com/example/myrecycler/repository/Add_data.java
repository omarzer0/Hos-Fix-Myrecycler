package com.example.myrecycler.repository;

import android.os.AsyncTask;

import com.example.myrecycler.Items;
import com.example.myrecycler.data.AppDataBase;

import java.util.List;

public class Add_data extends AsyncTask<Items, Void , List<Items>> {

    private AppDataBase db;
    private ConnectRepo.ItemsCallback itemsCallback;

    public Add_data(AppDataBase database, ConnectRepo.ItemsCallback callback) {
        db = database;
        itemsCallback = callback;
    }


    @Override
    protected List<Items> doInBackground(Items...items) {
        for (Items c : items) {
            db.itemsDao().insertAll(c);
        }
        return db.itemsDao().getItems();
    }

    @Override
    protected void onPostExecute(List<Items> items) {
        super.onPostExecute(items);
        itemsCallback.getContactList(items);
    }
}
