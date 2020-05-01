package com.example.myrecycler.repository;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.myrecycler.Items;
import com.example.myrecycler.data.AppDataBase;

import java.util.List;

public class ConnectRepo extends AsyncTask<Void, Void, List<Items>> {
    private AppDataBase db;
    private ItemsCallback callback;

    public ConnectRepo(@NonNull AppDataBase database, ItemsCallback callback) {
        this.db = database;
        this.callback = callback;
    }

    @Override
    protected List<Items> doInBackground(Void... voids) {
        return db.itemsDao().getItems();
    }

    @Override
    protected void onPostExecute(List<Items> items) {
        super.onPostExecute(items);
        callback.getContactList(items);
    }

    public interface ItemsCallback {
        void getContactList(List<Items> contacts);
    }
}
