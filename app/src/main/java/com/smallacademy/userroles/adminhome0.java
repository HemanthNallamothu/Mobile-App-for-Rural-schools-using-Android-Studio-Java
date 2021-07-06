package com.smallacademy.userroles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class adminhome0 extends AppCompatActivity {
    Button send,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome0);

        send = findViewById(R.id.sendToAdmin);
        logout = findViewById(R.id.logout);
        send.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), adminhome.class)));
        logout.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });
    }
}