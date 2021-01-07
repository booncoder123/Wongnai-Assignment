package com.example.wongnai_bitcoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.wongnai_bitcoin.DController.Controller;
import com.example.wongnai_bitcoin.DController.IController;
import com.example.wongnai_bitcoin.DModel.Adapter.MyAdapter;
import com.example.wongnai_bitcoin.DView.IView;

public class MainActivity extends AppCompatActivity implements IView {

    private RecyclerView recyclerView;
    private IController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.Relist);
        controller = new Controller(this);
    }
    @Override
    public void ShowDataOnRecycleView() {
        MyAdapter myAdapter = new MyAdapter(MainActivity.this, controller.GetAllCoin());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}