package com.example.vavatur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnEntrar1 = findViewById(R.id.btnEntrar1);
        btnEntrar1.setOnClickListener(this);

        Button btnEntrar2 = findViewById(R.id.btnEntrar2);
        btnEntrar2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEntrar1:
                Intent it1 = new Intent(this, MarkSeatActivity.class);
                startActivity(it1);
            case R.id.btnEntrar2:
                Intent it2 = new Intent(this, MarkSeat2Activity.class);
                startActivity(it2);
                break;
        }
    }
}