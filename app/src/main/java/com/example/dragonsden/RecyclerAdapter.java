package com.example.dragonsden;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<ListItem> data;
    Context context;
    private DatabaseReference database;
    private FirebaseUser user;

    public RecyclerAdapter(Context ct, ArrayList<ListItem> items){
        context = ct;
        data = items;
        database = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = data.get(position);
        holder.text.setText(listItem.text);
        holder.btn_view.setOnClickListener(view -> {
            Intent instant = new Intent(context, ItemActivity.class);

            instant.putExtra("id", listItem.id);
            instant.putExtra("text", listItem.text);
            instant.putExtra("money", listItem.money);
            instant.putExtra("species", listItem.species);
            instant.putExtra("used", listItem.used);

            context.startActivity(instant);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public Button btn_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.row_text);
            btn_view = itemView.findViewById(R.id.row_btn_view);
        }
    }
}
