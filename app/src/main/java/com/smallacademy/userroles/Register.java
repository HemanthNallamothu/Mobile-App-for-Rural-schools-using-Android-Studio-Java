package com.smallacademy.userroles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity {
    EditText fullName,email,password,phone;
    Button registerBtn,goToLogin;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CheckBox isTeacherBox, isStudentBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        phone = findViewById(R.id.registerPhone);
        registerBtn = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.gotoLogin);

        isTeacherBox = findViewById(R.id.isTeacher);
        isStudentBox = findViewById(R.id.isStudent);

        // Checkboxes Logic
        isStudentBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(compoundButton.isChecked()){
                isTeacherBox.setChecked(false);
            }
        });

        isTeacherBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(compoundButton.isChecked()){
                isStudentBox.setChecked(false);
            }
        });

        registerBtn.setOnClickListener(v -> {
            checkField(fullName);
            checkField(email);
            checkField(password);
            checkField(phone);

            //checkBox validation
            if(!(isTeacherBox.isChecked() || isStudentBox.isChecked())) {
                Toast.makeText(Register.this, "Select an Account type", Toast.LENGTH_SHORT).show();
                return;
            }

            if(valid) {
                // start user registration process
                fAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(authResult -> {
                    FirebaseUser user = fAuth.getCurrentUser();
                    Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                    DocumentReference df = fStore.collection("Users").document(Objects.requireNonNull(user).getUid());
                    Map<String,Object> userInfo = new HashMap<>();
                    userInfo.put("FullName", fullName.getText().toString());
                    userInfo.put("UserEmail", email.getText().toString());
                    userInfo.put("PhoneNumber", phone.getText().toString());

                    //specify if user is admin
                    if(isTeacherBox.isChecked()){
                        userInfo.put("isTeacher", "1");
                    }
                    if(isStudentBox.isChecked()){
                        userInfo.put("isStudent", "1");
                    }

                    df.set(userInfo);
                    if(isTeacherBox.isChecked()) {
                        startActivity(new Intent(getApplicationContext(), adminhome0.class));
                    }
                    else {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    finish();
                }).addOnFailureListener(e -> Toast.makeText(Register.this, "Failed to Create Account", Toast.LENGTH_SHORT).show());
            }
        });

        goToLogin.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Login.class)));

    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
}