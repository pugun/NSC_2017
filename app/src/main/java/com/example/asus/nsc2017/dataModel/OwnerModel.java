package com.example.asus.nsc2017.dataModel;

/**
 * Created by Dell on 5/2/2560.
 */

public class OwnerModel {

    /**
     * address : 48/15 ถ.นกแก้ว ตำบลกุดป่อง อ.เมือง จ.เลย 42000
     * age : 4
     * birthDate : 1994-11-13
     * id : 1419901704086
     * name : พัสกร แสงสว่าง
     */

    private String address;
    private int age;
    private String birthDate;
    private long id;
    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
