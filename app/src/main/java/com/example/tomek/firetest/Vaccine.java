package com.example.tomek.firetest;

/**
 * Created by Tomek on 2016-09-27.
 */
public class Vaccine {

    private String date;
    private String dogName;
    private String ownerid;
    private String type;

    public Vaccine(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}