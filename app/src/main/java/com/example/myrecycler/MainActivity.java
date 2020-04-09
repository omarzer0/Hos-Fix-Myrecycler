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
    ItemAdapter adapter = new ItemAdapter();

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
        }
    }

}
