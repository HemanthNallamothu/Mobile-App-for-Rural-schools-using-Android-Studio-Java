package com.smallacademy.userroles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class introduction2 extends AppCompatActivity {
    Button prev,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction2);

        prev = findViewById(R.id.btn_prev);
        next = findViewById(R.id.btn_next);

        prev.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Introduction.class)));
        next.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Login.class)));
    }
}