package com.example.dragonsden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {

    private BaseFire baseFire;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        baseFire = new BaseFire(this);
        extras = getIntent().getExtras();

        TextView title = findViewById(R.id.item_title);
        TextView condition = findViewById(R.id.item_condition);
        TextView worth = findViewById(R.id.item_worth);
        TextView species = findViewById(R.id.item_species);

        title.setText("Name of item: "+extras.getString("text"));
        condition.setText("Condition: "+extras.getString("used"));
        worth.setText("Worth: "+extras.getString("money"));
        species.setText("Species: "+extras.getString("species"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_btn_back:
                finish();
                break;
            case R.id.item_btn_delete:
                baseFire.deleteItem(extras.getString("id"));
                finish();
                break;
            default:
                break;
        }
    }

}