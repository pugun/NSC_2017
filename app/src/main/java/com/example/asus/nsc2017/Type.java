package com.example.asus.nsc2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Type extends AppCompatActivity implements View.OnClickListener {

    public static final String GET_PROVINCE = "province", GET_LICENSE2 = "license2";

    Button ok;
    EditText license1, license2;
    EditText province;
    String licenseSend1, licenseSend2, provinceSend;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        license2 = (EditText) findViewById(R.id.license2);
        province = (EditText) findViewById(R.id.province);
        ok = (Button) findViewById(R.id.okBtn);

        ok.setOnClickListener(this);

        intent = new Intent(getApplicationContext(), MainActivity.class);
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
