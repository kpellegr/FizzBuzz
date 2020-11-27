package com.example.fizzbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startFizzBuzz(View v) {
        Intent intent = new Intent(this, MainActivity.class); // Explicit Intent

        EditText numberView = findViewById(R.id.numberEditText);
        int number = Integer.parseInt(numberView.getText().toString());

        intent.putExtra("MAXNUMBER", number);

        startActivity(intent);
    }
}