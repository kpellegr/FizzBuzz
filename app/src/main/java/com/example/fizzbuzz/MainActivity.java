package com.example.fizzbuzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

        //populateList(100);

        mRecyclerView = findViewById(R.id.recycler);
        mAdapter = new NumberListAdapter(this, mNumberList); // List is empty at this point
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // We create a new Volley request, to fetch the data at myURL
        // Note that Volley automatically creates the request on a background thread, so
        // we don't have to create an AsyncTask ourselves;
        JsonArrayRequest request = new JsonArrayRequest(myURL,

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

        // Set the request up for execution
        mQueue.add(request);
    }

    private void populateList(int max) {
        for (int i = 1; i<=max; i++) {
            mNumberList.add(i);
        }
    }
}