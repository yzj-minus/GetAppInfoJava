package com.yzj.minus.getallapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<JSONObject> datas = AppJsonData.get(MainActivity.this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setAdapter(new AppAdapter(MainActivity.this, datas));
                    }
                });
            }
        }).start();

    }
}