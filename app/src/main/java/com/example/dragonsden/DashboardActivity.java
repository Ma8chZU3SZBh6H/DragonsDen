package com.example.dragonsden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    BaseFire baseFire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        baseFire = new BaseFire(this);
        baseFire.updateList(findViewById(R.id.dashboard_list));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dashboard_logout:
            {
                baseFire.logout();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }
            break;
            case R.id.dashboard_add:
                Intent i = new Intent(this, NewEntryActivity.class);
                startActivity(i);
                //database.child("posts_v1").child(user.getUid()).push().child("test").setValue(textFieldAdd.getText().toString());
                break;
            default:
                break;
        }
    }
}