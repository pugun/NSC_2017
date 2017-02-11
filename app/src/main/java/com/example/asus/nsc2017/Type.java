package com.example.asus.nsc2017;

import android.*;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.openalpr.OpenALPR;
import org.openalpr.model.Results;
import org.openalpr.model.ResultsError;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Type extends AppCompatActivity implements View.OnClickListener {

    public static final String GET_PROVINCE = "province", GET_LICENSE2 = "license2";
    private Intent recieveDataIntent;

    Button ok;
    EditText license1, license2;
    EditText province;
    String  licenseSend2, provinceSend;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        recieveDataIntent = getIntent();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        license2 = (EditText) findViewById(R.id.license2);
        province = (EditText) findViewById(R.id.province);
        ok = (Button) findViewById(R.id.okBtn);

        ok.setOnClickListener(this);
        intent = new Intent(getApplicationContext(), MainActivity.class);

        licenseSend2 = Storage.getPlateResult();
        if(!licenseSend2.equals("no-license")){
            license2.setText(licenseSend2);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ok) {
            try {
                /*
                SENT DATA TO OTHER CLASSES
                 */

                licenseSend2 = license2.getText().toString();
                provinceSend = province.getText().toString();

                intent.putExtra(GET_LICENSE2, licenseSend2);
                intent.putExtra(GET_PROVINCE, provinceSend);
                FirebaseActivity.showDataList();
                FirebaseActivity.getLicenseData(licenseSend2);
                startActivity(intent);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Something Error", Toast.LENGTH_LONG).show();
            }

        }
    }




}
