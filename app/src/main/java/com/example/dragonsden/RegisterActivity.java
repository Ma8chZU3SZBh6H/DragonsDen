package com.example.dragonsden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private BaseFire baseFire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        baseFire = new BaseFire(this);
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

                if(baseFire.validateTextViews(new TextView[]{email}, "required|min:6|max:256") && baseFire.validateTextView(password, "equals:"+text_password_confirm)){
                    baseFire.register(text_email, text_password, task -> {
                        if (task.isSuccessful()){
                            baseFire.login(text_email, text_password, task2 -> {
                                if (task2.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Your are logged in!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(this, DashboardActivity.class);
                                    startActivity(i);
                                    finish();
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