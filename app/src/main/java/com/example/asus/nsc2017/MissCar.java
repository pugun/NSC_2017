package com.example.asus.nsc2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MissCar extends AppCompatActivity {
    ViewPager viewPager;
    CustomSwip customSwip;
    private PopupMenu mPopupMenu;
    private static boolean isFirstTimeSync = true, isCreated =false;
    private static TextView lost, lostDate, lostTime, detail, brand, model, license, province, color, fuel, engine, idprb ;
    private static String lic2, prov;
    Intent intent, recieveDataIntent;
    String lostCarLicense ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miss_car);
        isCreated = true;


        recieveDataIntent = getIntent();
        prov = recieveDataIntent.getStringExtra(Type.GET_PROVINCE);
        lic2 = recieveDataIntent.getStringExtra(Type.GET_LICENSE2);

        intent = getIntent();
        lostCarLicense = intent.getStringExtra("LOST_LICENSE");
        FirebaseActivity.fetchLostCar(lostCarLicense);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        customSwip = new CustomSwip(this);
        viewPager.setAdapter(customSwip);

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        mPopupMenu = new PopupMenu(this, imageButton);
        MenuInflater menuInflater = mPopupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu, mPopupMenu.getMenu());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupMenu.show();
            }
        });
        mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.menuLogout:
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        break;
                    case R.id.menuSettings:
                        Toast.makeText(getApplicationContext(), "You clicked settings", Toast.LENGTH_LONG);
                }
                return true;
            }
        });


        province = (TextView) findViewById(R.id.province);
        license = (TextView) findViewById(R.id.license);
        lost = (TextView) findViewById(R.id.lost);
        lostDate = (TextView) findViewById(R.id.lostDate);
        lostTime = (TextView) findViewById(R.id.lostTime);
        detail = (TextView) findViewById(R.id.detail);
        brand = (TextView) findViewById(R.id.brand);
        model = (TextView) findViewById(R.id.model);
        color = (TextView) findViewById(R.id.color);
        fuel = (TextView) findViewById(R.id.fuel);
        engine = (TextView) findViewById(R.id.engine);
        idprb = (TextView) findViewById(R.id.idprb);

        if (isFirstTimeSync) startSync();
    }

    private static void setTex(){
        /*


        province.setText(prov);
        license.setText(lic2);
        lostDate.setText(StoreData.lostModel.getLostDate());
        lostTime.setText(StoreData.lostModel.getLostTime());
        detail.setText(StoreData.lostModel.getDetail());
 */
        brand.setText(StoreData.carsModel.getBrand());
        model.setText(StoreData.carsModel.getModel());
        color.setText(StoreData.carsModel.getColor());
        fuel.setText(StoreData.carsModel.getFuel());
        engine.setText(StoreData.carsModel.getEngine());
        //       idprb.setText(StoreData.carsModel.getIdprb());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isCreated = false;
        isFirstTimeSync = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isCreated = false;
        isFirstTimeSync = true;
    }

    static ProgressDialog fetchingDialog;

    public static void finishedSync() {
        if (isCreated) {
            fetchingDialog.dismiss();
            setTex();
        }
    }

    private void startSync() {
        if (isCreated) {
            isFirstTimeSync = false;
            fetchingDialog = new ProgressDialog(MissCar.this);
            fetchingDialog.setCancelable(false);
            fetchingDialog.setMessage("Fetching Data");
            fetchingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            fetchingDialog.setIndeterminate(true);
            fetchingDialog.show();
        }
    }
}
