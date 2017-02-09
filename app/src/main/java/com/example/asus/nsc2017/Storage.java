package com.example.asus.nsc2017;

/**
 * Created by ASUS on 9/2/2560.
 */

public class Storage {
    private static String license = "no-license";

    public static void setPlateResult(String newLicense){
        license = newLicense;
    }

    public static String getPlateResult(){
        return license;
    }
}
