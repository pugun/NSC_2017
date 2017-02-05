package com.example.asus.nsc2017.dataModel;

/**
 * Created by Dell on 5/2/2560.
 */

public class CarsModel {

    /**
     * brand : Honda
     * color : Black
     * engine : 4 Cylinder, DOHC i-DTEC
     * expireDate : 2016-11-12
     * fuel : Diesel
     * idcar : 123456
     * idprb : 1337
     * issueDate : 2014-11-12
     * model : City
     * owner_id : 1419901704086
     */

    private String brand;
    private String color;
    private String engine;
    private String expireDate;
    private String fuel;
    private String idcar;
    private String idprb;
    private String issueDate;
    private String model;
    private String owner_id;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getIdcar() {
        return idcar;
    }

    public void setIdcar(String idcar) {
        this.idcar = idcar;
    }

    public String getIdprb() {
        return idprb;
    }

    public void setIdprb(String idprb) {
        this.idprb = idprb;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "CarsModel{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", engine='" + engine + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", fuel='" + fuel + '\'' +
                ", idcar='" + idcar + '\'' +
                ", idprb='" + idprb + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", model='" + model + '\'' +
                ", owner_id='" + owner_id + '\'' +
                '}';
    }
}
