package com.example.orderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        modelList = new ArrayList<>();
        modelList.add(new Model("Big Mac", getString(R.string.bigmac), R.drawable.bigmac ));
        modelList.add(new Model("McPollo",getString(R.string.mcpollo), R.drawable.mcpollo));
        modelList.add(new Model("CBO", getString(R.string.cbo), R.drawable.cbo));
        modelList.add(new Model("MC Royal Deluxe", getString(R.string.royaldeluxe), R.drawable.royaldeluxe));
        modelList.add(new Model("McWrap Chicken crujiente & Bacon", getString(R.string.wrap), R.drawable.wrap));
        modelList.add(new Model("Grand McExtreme Double Bacon", getString(R.string.extreme), R.drawable.mcextreme));
        modelList.add(new Model("Grand McSignature Queso de Cabra", getString(R.string.signature), R.drawable.signature));
        modelList.add(new Model("Big Chicken Supreme", getString(R.string.supreme), R.drawable.chicksupreme));
        modelList.add(new Model("Big Double Cheese", getString(R.string.doubles), R.drawable.doublecheese));
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.mcdonalds);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));


        mcAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mcAdapter);




    }
}