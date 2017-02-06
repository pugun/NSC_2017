package com.example.asus.nsc2017;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.asus.nsc2017.R.id.imageButton;

public class MainActivity extends AppCompatActivity {
    private PopupMenu mPopupMenu;
    private Intent recieveDataIntent;
    private String lic1, lic2, prov;
    private static Context staticContext;
    private static boolean isFirstTimeSync = true, isCreated = false;

    private String ownerID, carID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isCreated = true;
        staticContext = getApplicationContext();

        /**
         * Getting data from another class
         */
        recieveDataIntent = getIntent();
        lic2 = recieveDataIntent.getStringExtra(Type.GET_LICENSE2);
        prov = recieveDataIntent.getStringExtra(Type.GET_PROVINCE);

        importDataFromStoreData();

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
                switch (id) {
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

        bindingView();
        TextView license = (TextView) findViewById(R.id.license);
        TextView province = (TextView) findViewById(R.id.province);
        license.setText(lic2);
        province.setText(prov);

        if (isFirstTimeSync) startSync();
    }

    static TextView name, birthDate, id, address, idcar, issueDate, expireDate, brand, model, color, fuel, engine, idprb, age;

    private void bindingView() {
        //      String ageString = Integer.toString(StoreData.ownerModel.getAge());

        name = (TextView) findViewById(R.id.name);
        birthDate = (TextView) findViewById(R.id.birthDate);
        id = (TextView) findViewById(R.id.id);
        address = (TextView) findViewById(R.id.address);
        idcar = (TextView) findViewById(R.id.idcar);
        issueDate = (TextView) findViewById(R.id.issueDate);
        expireDate = (TextView) findViewById(R.id.expireDate);
        brand = (TextView) findViewById(R.id.brand);
        model = (TextView) findViewById(R.id.model);
        color = (TextView) findViewById(R.id.color);
        fuel = (TextView) findViewById(R.id.fuel);
        engine = (TextView) findViewById(R.id.engine);
        idprb = (TextView) findViewById(R.id.idprb);
        //      age = (TextView) findViewById(R.id.age) ;
    }

    private static void setTex() {

        name.setText(StoreData.ownerModel.getName());
        birthDate.setText(StoreData.ownerModel.getBirthDate());
        id.setText(StoreData.ownerModel.getId());
        address.setText(StoreData.ownerModel.getAddress());
//        idcar.setText(StoreData.carsModel.getIdcar());
        issueDate.setText(StoreData.carsModel.getIssueDate());
        expireDate.setText(StoreData.carsModel.getExpireDate());
        brand.setText(StoreData.carsModel.getBrand());
        model.setText(StoreData.carsModel.getModel());
        color.setText(StoreData.carsModel.getModel());
        fuel.setText(StoreData.carsModel.getFuel());
        engine.setText(StoreData.carsModel.getEngine());
        idprb.setText(StoreData.carsModel.getIdprb());
        //      age.setText(ageString);
    }

    public void importDataFromStoreData() {
        boolean isTheif = false, isLost = false;

        ownerID = StoreData.carsModel.getOwner_id();
        carID = StoreData.carsModel.getIdcar();

        if (StoreData.thiefList.contains(ownerID)) isTheif = true;
        if (StoreData.lostList.contains(carID)) isLost = true;

        if (isTheif || isLost)
            intentNotifiedData(isLost, isTheif);
    }

    public void intentNotifiedData(boolean isLost, boolean isThief) {
        Intent intentToAlert = new Intent(getApplicationContext(), Alert.class);
        if (isLost && isThief) {
            intentToAlert.putExtra("THIEF", true);
            intentToAlert.putExtra("LOST", true);
        } else if (isLost) {
            intentToAlert.putExtra("THIEF", false);
            intentToAlert.putExtra("LOST", true);
        } else if (isThief) {
            intentToAlert.putExtra("THIEF", true);
            intentToAlert.putExtra("LOST", false);
        }
        startActivity(intentToAlert);
    }

    public static void intentNotFound() {
        staticContext.startActivity(new Intent(staticContext, Error.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
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
            fetchingDialog = new ProgressDialog(MainActivity.this);
            fetchingDialog.setCancelable(false);
            fetchingDialog.setMessage("Fetching Data");
            fetchingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            fetchingDialog.setIndeterminate(true);
            fetchingDialog.show();
        }
    }

}
