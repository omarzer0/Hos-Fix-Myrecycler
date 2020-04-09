package com.example.myrecycler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Items> arrayList = new ArrayList<>();
    final int ADD_REQUEST_CODE = 0;
    final int EDIT_REQUEST_CODE = 1;


    final ItemAdapter.Clickmanager AD = new ItemAdapter.Clickmanager() {

        @Override
        public void cl(Items items, int position) {
            Intent intent = new Intent(MainActivity.this, add_item.class);
            intent.putExtra("items", items);
            intent.putExtra("pos", position);
            startActivityForResult(intent, EDIT_REQUEST_CODE);
        }

        @Override
        public void del(int position) {

            arrayList.remove(position);
            adapter.notifyDataSetChanged();

        }
    };
    ItemAdapter adapter = new ItemAdapter(AD);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter.itemsArrayList = arrayList;


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
            arrayList.add((Items) data.getSerializableExtra("info"));
            adapter.notifyDataSetChanged();
        } else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            arrayList.set(data.getIntExtra("pos", 0), (Items) data.getSerializableExtra("items"));
            adapter.notifyDataSetChanged();
        }

    }}
