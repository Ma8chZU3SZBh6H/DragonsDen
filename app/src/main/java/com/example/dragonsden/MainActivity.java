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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private BaseFire baseFire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseFire = new BaseFire(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
            {

                EditText email = findViewById(R.id.login_email);
                EditText password = findViewById(R.id.login_password);

                String text_email = email.getText().toString();
                String text_password = password.getText().toString();

                if(baseFire.validateTextViews(new TextView[]{email, password}, "required|min:6|max:256")){
                    baseFire.login(text_email, text_password, task -> {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
                break;
            case R.id.btn_link_register:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}