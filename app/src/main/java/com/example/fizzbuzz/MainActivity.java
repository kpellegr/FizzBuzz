package com.example.fizzbuzz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private static final String myURL = "https://my.api.mockaroo.com/fizzbuzz.json?key=e88569c0";

    private final LinkedList<Integer> mNumberList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private NumberListAdapter mAdapter;
    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        int number = intent.getIntExtra("MAXNUMBER", 100);

        populateList(number);

        mRecyclerView = findViewById(R.id.recycler);
        mAdapter = new NumberListAdapter(this, mNumberList); // List is empty at this point
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //mQueue.add(createStudentFetchRequest());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.top_menuitem) {
            mRecyclerView.smoothScrollToPosition(0);
            return true;
        }
        if(id == R.id.bottom_menuitem) {
            mRecyclerView.smoothScrollToPosition(mNumberList.size());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateList(int max) {
        for (int i = 1; i<=max; i++) {
            mNumberList.add(i);
        }
    }

    // We create a new Volley request, to fetch the data at myURL
    // Note that Volley automatically creates the request on a background thread, so
    // we don't have to create an AsyncTask ourselves;
    // Set the request up for execution
    private JsonArrayRequest createStudentFetchRequest () {
        return new JsonArrayRequest(myURL,

                response -> {
                    // Method executed when the response is successful
                    for (int i=0; i<response.length(); i++) {
                        try {
                            JSONObject student = response.getJSONObject(i);
                            mNumberList.add(student.getInt("number"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    // Let the adapter know that it has to redraw itself
                    mAdapter.notifyDataSetChanged();
                },
                error -> {
                    // Method executed when there is an error.
                    Log.e("FizzBuzz", error.getMessage());
                });
    }
}