package com.example.asus.nsc2017;

import android.util.Log;

import com.example.asus.nsc2017.dataModel.CarsModel;
import com.example.asus.nsc2017.dataModel.LostModel;
import com.example.asus.nsc2017.dataModel.OwnerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 *
 * Created by ASUS on 24/1/2560.
 */
public class FirebaseActivity {
    private static FirebaseDatabase projectDataBase = FirebaseDatabase.getInstance();
    public static DatabaseReference CAR_REF = projectDataBase.getReference("car"),
            OWNER_REF = projectDataBase.getReference("owner"),
            LOST_REF = projectDataBase.getReference("lost"),
            THIEF_REF = projectDataBase.getReference("thief"),
            DATA_REF = projectDataBase.getReference();

    private static ValueEventListener CAR_VALUE_LISTENER = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String ownerID;
            DataSnapshot cars, owner;
            cars = dataSnapshot.child("car");
            owner = dataSnapshot.child("owner");

            if (cars.hasChild(StoreData.currentLicense)) {
                StoreData.carsModel = cars.child(StoreData.currentLicense).getValue(CarsModel.class);
                Log.e("FIREBASE", StoreData.carsModel.getColor());

                //StoreData.carsModel.setOwner_id(cars.child(StoreData.currentLicense).child("owner_id").getValue().toString());
                ownerID = StoreData.carsModel.getOwner_id();
                StoreData.ownerModel = owner.child(ownerID).getValue(OwnerModel.class);
                MainActivity.finishedSync();
            }
            else {
                MainActivity.intentNotFound();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    },
            LOST_LIST_PULLER = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot lostCar : dataSnapshot.getChildren()) {
                        StoreData.lostList.add(lostCar.getKey());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("FIREBASE", "[LOST CAR] Something error!");
                    databaseError.toException().printStackTrace();
                }
            }, THIEF_LIST_PULLER = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot thief : dataSnapshot.getChildren()) {
                StoreData.thiefList.add(thief.getKey());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("FIREBASE", "[THIEF LIST] Something error!");
            databaseError.toException().printStackTrace();
        }
    } ,
            THIEF_WARRANT_LIST_PULLER = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot thief) {
            thief = thief.child(StoreData.getThiefID());
            for(DataSnapshot warrant : thief.getChildren()){
                StoreData.thiefWarrantList.add((String)warrant.getValue());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("FIREBASE", "[LOST CAR] Something error!");
            databaseError.toException().printStackTrace();
        }
    };

    public static boolean checkCarContentAvailable(String licensePlate, String provice) {
        CAR_REF.addValueEventListener(CAR_VALUE_LISTENER);
        return true;
    }


    //This method will use for adding listener after login to firebase
    public static void initializeDataList() {
        LOST_REF.addValueEventListener(LOST_LIST_PULLER);
        THIEF_REF.addValueEventListener(THIEF_LIST_PULLER);
    }

    public static void showDataList() {
        for (String keyLost : StoreData.lostList) {
            Log.e("[LOST-LIST]", keyLost);
        }
        for (String keyThief : StoreData.thiefList) {
            Log.e("[THIEF-LIST]", keyThief);
        }
    }

    public static void getLicenseData(String license) {
        StoreData.setCurrentLicense(license);
        try {
            DATA_REF.removeEventListener(CAR_VALUE_LISTENER);
        } catch (Exception ex) {
            Log.e("FIREBASE", "Can't remove event listener");
        }
        DATA_REF.addValueEventListener(CAR_VALUE_LISTENER);
    }

    public static void fetchThiefWarrantData(String citizenID){
        StoreData.setThiefID(citizenID);
        try {
            THIEF_REF.removeEventListener(THIEF_WARRANT_LIST_PULLER);
        }catch (Exception ex){
            Log.e("FIREBASE", "Can't remove event listener");
        }
        THIEF_REF.addValueEventListener(THIEF_WARRANT_LIST_PULLER);
    }
}

class StoreData {
    public static ArrayList<String> lostList = new ArrayList<>(), thiefList = new ArrayList<>();
    public static CarsModel carsModel = new CarsModel();
    public static LostModel lostModel;
    public static OwnerModel ownerModel = new OwnerModel();
    public static String currentLicense = new String();
    public static ArrayList<String> thiefWarrantList = new ArrayList<>();

    public static String getThiefID() {
        return thiefID;
    }

    public static void setThiefID(String thiefID) {
        StoreData.thiefID = thiefID;
    }

    private static String thiefID = new String();

    public static void setCurrentLicense(String newLicense) {
        currentLicense = newLicense;
    }
    public static String getCurrentLicense(){ return currentLicense; }
}
