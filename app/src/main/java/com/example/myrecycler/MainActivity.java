package com.example.myrecycler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
//    List<Items> arrayList = new ArrayList<>();
    final int ADD_REQUEST_CODE = 0;
    final int EDIT_REQUEST_CODE = 1;
    ArrayList<Items> arrayList = new ArrayList();



    final ItemAdapter.Clickmanager AD = new ItemAdapter.Clickmanager() {

        @Override
        public void cl(Items items, int position) {
            Intent intent = new Intent(MainActivity.this, add_item.class);
            intent.putExtra("items", items);
            intent.putExtra("pos", position);
            startActivityForResult(intent, EDIT_REQUEST_CODE);
        }

        @Override
        public void del(Items items) {

            arrayList.remove(items);
            ArrayList<Items> IT = new ArrayList<>(arrayList);
            adapter.submitList(IT);
        }
    };
    ItemAdapter adapter = new ItemAdapter(new DiffUtil.ItemCallback<Items>() {
        @Override
        public boolean areItemsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
            return oldItem.getNumber().equals(newItem.getNumber())&& oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
            return false;
        }
    },AD);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter.submitList(arrayList);


          //here the object of data base will call and build
      AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "app.db")
                .allowMainThreadQueries()
                .build();
      // list item insted of arraylist
        List<Items> dbContacts = db.itemsDao().getItems();


        recyclerView = findViewById(R.id.rview);
        recyclerView.setAdapter(adapter);

        FloatingActionButton create = findViewById(R.id.create_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , add_item.class);
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // here the couple of lines to add item to list or db
//            List<Items> dbContacts = db.itemsDao().getItems();
//            adapter.submitList(dbContacts);
        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                // here the couple of lines to edit item to list or db

                arrayList.set(data.getIntExtra("pos", 0), (Items) data.getSerializableExtra("info"));
            }
            ArrayList<Items> IT = new ArrayList<>(arrayList);
            adapter.submitList(IT);
        }

    }}
