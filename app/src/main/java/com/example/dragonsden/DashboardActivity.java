package com.example.dragonsden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        auth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dashboard_logout:
            {
                auth.signOut();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }
            break;
            default:
                break;
        }
    }
}