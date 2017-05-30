package com.example.marietopphem.groupout1;


/**
 * Created by vije5851 on 2017-05-18.
 */

public class PositionObject {

    String name;
    String id;
    double latitude;
    double longitude;



    public PositionObject(String name, String id, double latitude, double longitude){
        this.name = name;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setName(String inputName){
        name = inputName;
    }

    public void setId(String inputId){
        id = inputId;
    }

    public void setLatitude(double ix){
        latitude = ix;
    }

    public void setLongitude(double iy){
        longitude = iy;
    }

    public String getName(){
        return name;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getId(){
        return id;
    }

    public String toString(){
        return "Name: " + name + " (id = " + id + ") x = " + latitude + " y = " + longitude ;
    }
}