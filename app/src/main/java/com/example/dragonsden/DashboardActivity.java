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
    private FirebaseAuth auth;
    private DatabaseReference database;
    private FirebaseUser user;
    private RecyclerView list;
    private RecyclerAdapter listAdapter;
    private ArrayList<String> listItems = new ArrayList<>();
    private EditText textFieldAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        user = auth.getCurrentUser();

        textFieldAdd = findViewById(R.id.dashboard_text_add);

        list = findViewById(R.id.dashboard_list);
        listItems.add("Loading...");
        listAdapter= new RecyclerAdapter(this, listItems);
        list.setAdapter(listAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));

        database.child("posts_v1").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listItems.clear();
                for (DataSnapshot snap : snapshot.getChildren()){
                    System.out.println(snap.child("test").getValue().toString());
                    listItems.add(snap.child("test").getValue().toString());
                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
            case R.id.dashboard_add:
                database.child("posts_v1").child(user.getUid()).push().child("test").setValue(textFieldAdd.getText().toString());
                break;
            default:
                break;
        }
    }
}