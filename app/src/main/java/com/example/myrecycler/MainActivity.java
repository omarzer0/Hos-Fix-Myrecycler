package com.example.myrecycler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrecycler.data.AppDataBase;
import com.example.myrecycler.repository.Add_data;
import com.example.myrecycler.repository.ConnectRepo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    final int ADD_REQUEST_CODE = 0;
    final int EDIT_REQUEST_CODE = 1;
    List<Items> itemsList;
    AppDataBase db;






    final ItemAdapter.Clickmanager AD = new ItemAdapter.Clickmanager() {

        // no more need for position you can delete it here
        //you must also delete it in add_item
        @Override
        public void cl(Items items, int position) {
            Intent intent = new Intent(MainActivity.this, add_item.class);
            intent.putExtra("items", items);
            intent.putExtra("pos", itemsList.indexOf(items));
            startActivityForResult(intent, EDIT_REQUEST_CODE);
        }






        @Override
        public void del(Items items) {

//            arrayList.remove(items);
//            ArrayList<Items> IT = new ArrayList<>(arrayList);
//            adapter.submitList(IT);

            //---------------------------------
            //delete should be small case
            db.itemsDao().Delete(items);
            itemsList = db.itemsDao().getItems();
            adapter.submitList(itemsList);
        }
    };


    ConnectRepo.ItemsCallback callback = new ConnectRepo.ItemsCallback() {
        @Override
        public void getContactList(List<Items> itemsList) {
            adapter.submitList(itemsList);

        }
    };







    ItemAdapter adapter = new ItemAdapter(new DiffUtil.ItemCallback<Items>() {
        @Override
        public boolean areItemsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
            return oldItem.getNumber().equals(newItem.getNumber()) && oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
            return false;
        }
    }, AD);







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ConnectRepo repo = new ConnectRepo(db, callback);
        ConnectRepo repo = new ConnectRepo(db,callback);


        repo.execute();


        db = AppDataBase.getInstance(this);
        itemsList = db.itemsDao().getItems();
        adapter.submitList(itemsList);




        recyclerView = findViewById(R.id.rview);
        recyclerView.setAdapter(adapter);





        FloatingActionButton create = findViewById(R.id.create_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_item.class);
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });


    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

//            db.itemsDao().insertAll((Items) data.getSerializableExtra("info"));
//            itemsList = db.itemsDao().getItems();
//            adapter.submitList(itemsList);
            Add_data addContact = new Add_data(db, callback);
            addContact.execute(new Items(data.getStringExtra("name"),data.getStringExtra("number")));

        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            db.itemsDao().update((Items) data.getSerializableExtra("info"));
            itemsList = db.itemsDao().getItems();
            adapter.submitList(itemsList);
        }

    }
}
