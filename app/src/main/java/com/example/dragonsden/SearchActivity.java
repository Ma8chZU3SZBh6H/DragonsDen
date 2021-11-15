package com.example.dragonsden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private BaseFire baseFire;
    RecyclerView recyclerView;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        baseFire = new BaseFire(this);
        recyclerView = findViewById(R.id.search_list);
        search = findViewById(R.id.search_field);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_btn_back:
                finish();
                break;
            case R.id.search_btn_search:
                baseFire.updateListWithFilter(recyclerView, search.getText().toString());
                break;
            default:
                break;
        }
    }
}