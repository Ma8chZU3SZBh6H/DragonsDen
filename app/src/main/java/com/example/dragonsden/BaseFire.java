package com.example.dragonsden;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.function.Function;

public class BaseFire {
    public FirebaseAuth auth;
    public DatabaseReference database;
    public FirebaseUser user;
    public Validation validation;
    private Context cx;

    private void onChange(ValueEventListener vel){
        database.child("posts_v1").child(user.getUid()).addValueEventListener(vel);
    }

    public BaseFire(Context _cx){
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        user = auth.getCurrentUser();
        validation = new Validation();
        cx = _cx;
    }

    public void updateListWithFilter(RecyclerView list, String filter){
        RecyclerAdapter listAdapter;
        ArrayList<ListItem> listItems = new ArrayList<>();
        listAdapter = new RecyclerAdapter(cx, listItems);
        list.setAdapter(listAdapter);
        list.setLayoutManager(new LinearLayoutManager(cx));

        onChange(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listItems.clear();
                for (DataSnapshot snap : snapshot.getChildren()){
                    String name = snap.child("text").getValue().toString();
                    if (name.contains(filter)){
                        String id = snap.getKey().toString();
                        String used = snap.child("used").getValue().toString();
                        String money = snap.child("money").getValue().toString();
                        String species = snap.child("species").getValue().toString();
                        ListItem item = new ListItem(name, id, used, money, species);
                        listItems.add(item);
                    }
                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void updateList(RecyclerView list){
        updateListWithFilter(list, "");
    }


    public void deleteItem(String item_id){
        database.child("posts_v1").child(user.getUid()).child(item_id).removeValue();
    }

    public boolean validateTextView(TextView input, String rules){
        ArrayList<Validation.ValidationMessage> errors =  validation.validate(input.getText().toString(), rules);
        if (errors.size() > 0){
            input.setError(errors.get(0).message);
            return false;
        }
        else{
            return true;
        }
    }

    public boolean validateTextViews(TextView[] input, String rules){
        for (int i = 0; i < input.length; i++){
           TextView view = input[i];
           boolean valid = validateTextView(view, rules);
           if (!valid){
               return false;
           }
        }
        return true;
    }

    public void login(String email, String password,  OnCompleteListener<AuthResult> onFinish){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(onFinish);
    }

    public void register(String email, String password, OnCompleteListener<AuthResult> onFinish){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(onFinish);
    }

    public void add(CloudItem item){
        database.child("posts_v1").child(user.getUid()).push().setValue(item);
    }

    public void logout(){
        auth.signOut();
    }
}
