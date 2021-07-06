package com.smallacademy.userroles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Introduction extends AppCompatActivity {
    Button next,skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        next = findViewById(R.id.btn_next);
        skip = findViewById(R.id.btn_skip);

        next.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),introduction2.class)));
        skip.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Login.class)));
    }
}