package com.example.asus.nsc2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Option extends AppCompatActivity implements View.OnClickListener {
    ImageButton type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        type = (ImageButton) findViewById(R.id.type);

        type.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == type) {
            startActivity(new Intent(getApplicationContext(), Type.class));
        }
    }
}
