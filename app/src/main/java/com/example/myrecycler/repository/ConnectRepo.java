package com.example.myrecycler.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myrecycler.Items;
import com.example.myrecycler.data.AppDataBase;

import java.util.List;

public class ConnectRepo extends AsyncTask<Void,Void, List<Items>> {


    private AppDataBase db;

    public ConnectRepo(@NonNull AppDataBase database, ItemsCallback callback) {
        db = database;
        this.callback = callback;
    }

    private ItemsCallback callback;

    public interface ItemsCallback {
        void getContactList(List<Items> contacts);
    }

    @Override
    protected List<Items> doInBackground(Void... voids) {

        List<Items> list = db.itemsDao().getItems();
        return list;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("TAG", "onPreExecute ... ");
    }

    @Override
    protected void onPostExecute(List<Items> items) {
        super.onPostExecute(items);
        callback.getContactList(items);
    }
}
