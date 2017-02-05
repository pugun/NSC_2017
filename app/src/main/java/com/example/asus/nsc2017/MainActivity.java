package com.example.asus.nsc2017;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Getting data from another class
         */
        recieveDataIntent = getIntent();
        lic2 = recieveDataIntent.getStringExtra(Type.GET_LICENSE2);
        prov = recieveDataIntent.getStringExtra(Type.GET_PROVINCE);

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

        TextView license = (TextView) findViewById(R.id.license);
        TextView province = (TextView) findViewById(R.id.province);
        license.setText(lic2);
        province.setText(prov);
    }

    public static void intentNotifiedData(){
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


}
