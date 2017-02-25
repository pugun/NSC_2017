package com.example.asus.nsc2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Warrant extends AppCompatActivity {
    private PopupMenu mPopupMenu;
    private static boolean isFirstTimeSync = true, isCreated = false;
    static TextView name, birthDate, id, address;
    private Intent intent;
    private static String lic1, lic2, prov;
    String thiefID;
    private ImageView i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warrant);
        isCreated = true;

        intent = getIntent();
        thiefID = intent.getStringExtra("THIEF_ID");
        FirebaseActivity.fetchThiefWarrantData(thiefID);

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

        setIm();



        name = (TextView) findViewById(R.id.name);
        birthDate = (TextView) findViewById(R.id.birthDate);
        id = (TextView) findViewById(R.id.id);
        address = (TextView) findViewById(R.id.address);
        if(isFirstTimeSync) startSync();
    }

    private static void setTex() {
        name.setText(StoreData.ownerModel.getName());
        birthDate.setText(StoreData.ownerModel.getBirthDate());
        id.setText(StoreData.ownerModel.getId());
        address.setText(StoreData.ownerModel.getAddress());
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
            fetchingDialog = new ProgressDialog(Warrant.this);
            fetchingDialog.setCancelable(false);
            fetchingDialog.setMessage("Fetching Data");
            fetchingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            fetchingDialog.setIndeterminate(true);
            fetchingDialog.show();
        }
    }

    public void setIm() {
        i = (ImageView) findViewById(R.id.imageView6) ;
        if(StoreData.ownerModel.getId().equals("1419901704086")) {
            i.setImageResource(R.drawable.kt3216);
        }
        if(StoreData.ownerModel.getId().equals("1402230659326")) {
          //  i.setImageResource(R.drawable.etu870);
        }
    }
}
