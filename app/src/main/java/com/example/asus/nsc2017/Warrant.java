package com.example.asus.nsc2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Warrant extends MainActivity {
    private PopupMenu mPopupMenu;
    private static boolean isFirstTimeSync =true, isCreated =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warrant);

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

        TextView name, birthDate, id, address, idcar, license ;
        name = (TextView) findViewById(R.id.name);
        birthDate = (TextView) findViewById(R.id.birthDate);
        id = (TextView) findViewById(R.id.id);
        address = (TextView) findViewById(R.id.address);
        idcar = (TextView) findViewById(R.id.idcar);
        license = (TextView) findViewById(R.id.license);



        name.setText(StoreData.ownerModel.getName());
        birthDate.setText(StoreData.ownerModel.getBirthDate());
        id.setText(StoreData.ownerModel.getId());
        address.setText(StoreData.ownerModel.getAddress());
        license.setText(StoreData.getCurrentLicense());




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public static void finishedSync(){

    }
}
