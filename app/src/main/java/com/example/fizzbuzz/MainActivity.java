package com.example.fizzbuzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<Integer> mNumberList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private NumberListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateList(100);

        mRecyclerView = findViewById(R.id.recycler);
        mAdapter = new NumberListAdapter(this, mNumberList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateList(int max) {
        for (int i = 1; i<=max; i++) {
            mNumberList.add(i);
        }
    }
}