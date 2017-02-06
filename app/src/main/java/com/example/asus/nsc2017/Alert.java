package com.example.asus.nsc2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Alert extends AppCompatActivity implements View.OnClickListener{

    Intent intent;
    ImageView lostBtn , thiefBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        boolean isLost = false, isThief = false;
        intent = getIntent();
        isThief = intent.getBooleanExtra("THIEF" , false);
        isLost = intent.getBooleanExtra("LOST" , false);

        lostBtn = (ImageView) findViewById(R.id.lostCar);
        thiefBtn = (ImageView) findViewById(R.id.thief);
    }

    public void setComponentVisible(boolean isLost , boolean isThief){
        if(!isLost) lostBtn.setVisibility(View.GONE);
        if(!isThief) thiefBtn.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {

    }
}
