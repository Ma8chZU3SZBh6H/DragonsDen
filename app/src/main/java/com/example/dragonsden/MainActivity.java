package com.example.dragonsden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                Toast.makeText(this, "TEST", Toast.LENGTH_SHORT).show();
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