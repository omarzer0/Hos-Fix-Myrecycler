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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView;
        ArrayList<Items> arrayList = new ArrayList<Items>();
        final int ADD_REQUEST_CODE = 0;
        recyclerView = findViewById(R.id.rview);
        FloatingActionButton create = findViewById(R.id.create_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , add_item.class);
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });

        ItemAdapter adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);



    }

}
