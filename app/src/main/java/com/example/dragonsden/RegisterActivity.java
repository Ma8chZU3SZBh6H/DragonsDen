package com.example.dragonsden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                Toast.makeText(this, "TEST", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_link_login:
//                Intent i = new Intent(this, MainActivity.class);
//                startActivity(i);
                this.finish();
                break;
            default:
                break;
        }
    }
}