package com.example.dragonsden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
            {
                EditText email = findViewById(R.id.register_email);
                EditText password = findViewById(R.id.register_password);
                EditText password_confirm = findViewById(R.id.register_password_confirm);

                String text_email = email.getText().toString();
                String text_password = password.getText().toString();
                String text_password_confirm = password_confirm.getText().toString();

                if (!text_password.equals(text_password_confirm)){
                    password.setError("Password's did not match");
                }
                else{
                    auth.createUserWithEmailAndPassword(text_email, text_password)
                            .addOnCompleteListener(RegisterActivity.this, task -> {
                                if (task.isSuccessful()){
                                    auth.signInWithEmailAndPassword(text_email, text_password).addOnCompleteListener(RegisterActivity.this, task2 -> {
                                        if (task2.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this, "Your are logged in!", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(RegisterActivity.this, "Login failed - " + task.getException().getCause().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "Registration failed - "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
                break;
            case R.id.btn_link_login:
                finish();
                break;
            default:
                break;
        }
    }


    private void registerUser(){

    }
}