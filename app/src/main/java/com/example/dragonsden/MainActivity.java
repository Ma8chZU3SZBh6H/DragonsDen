package com.example.dragonsden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
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

                auth.signInWithEmailAndPassword(text_email, text_password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(this, "Login failed - "+task.getException().getCause().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
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