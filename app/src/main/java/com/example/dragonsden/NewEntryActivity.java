package com.example.dragonsden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class NewEntryActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText textFieldAdd;
    private RadioGroup condition;
    private String text_condition = "New";
    private EditText worth;
    private Spinner species;



    private BaseFire baseFire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        textFieldAdd = findViewById(R.id.new_name);
        condition = findViewById(R.id.new_condition);
        worth = findViewById(R.id.new_price);
        species = findViewById(R.id.new_species);

        condition.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i){
                case R.id.new_radio_new:
                    text_condition = "New";
                    break;
                case R.id.new_radio_used:
                    text_condition = "Used";
                    break;
                default:
                    break;
            }
        });

        baseFire = new BaseFire(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.new_btn_cancel:
                finish();
            break;
            case R.id.new_btn_add:
                String text_name = textFieldAdd.getText().toString();
                String text_number = worth.getText().toString();
                String text_species = species.getSelectedItem().toString();

                if (baseFire.validateTextViews(new TextView[]{worth, textFieldAdd}, "required|max:255")){


                    System.out.println(text_name);
                    System.out.println(text_number);
                    System.out.println(text_species);
                    System.out.println(text_condition);

                    CloudItem item = new CloudItem(text_name, text_condition, text_number, text_species);
                    baseFire.add(item);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}