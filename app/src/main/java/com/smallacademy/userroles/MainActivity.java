package com.smallacademy.userroles;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    TextView Subject, Date, Name;
    private Spinner Timeslot;
    Button btn, logout;
    DatabaseReference reference;
    Member member;
    int maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timeslot = findViewById(R.id.spinner);
        Subject = findViewById(R.id.subject);
        Date = findViewById(R.id.Inputdate);
        Name = findViewById(R.id.Name);
        btn = findViewById(R.id.btn_Save);
        logout = findViewById(R.id.logout);

        member = new Member();
        reference = FirebaseDatabase.getInstance().getReference().child("Chinaogirala");

        List<String> Categories = new ArrayList<>();
        Categories.add("10:00 AM");
        Categories.add("11:00 AM");
        Categories.add("01:00 AM");
        Categories.add("02:00 AM");
        Categories.add("03:00 AM");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,Categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Timeslot.setAdapter(dataAdapter);

        logout.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(v -> {
            String subject = Subject.getText().toString();
            String date = Date.getText().toString();
            String name = Name.getText().toString();

            member.setDate(date);
            member.setSubject(subject);
            member.setName(name);
            member.setTimeslot(Timeslot.getSelectedItem().toString());

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                    Toast.makeText(MainActivity.this, "Your data has been stored successfully", Toast.LENGTH_LONG).show();
                    reference.child(String.valueOf(maxid+1)).setValue(member);
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        });
    }
}