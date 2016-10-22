package com.example.tomek.firetest;

/**
 * Created by Tomek on 2016-10-11.
 */
public class Animal {

    private String name;
    private String birthdate;
    private String ownerid;
    private String race;
    private String color;
    private String specialSigns;
    private String UniqNumber;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String sex;


    public Animal(){

    }

    public String getUniqNumber() {
        return UniqNumber;
    }

    public void setUniqNumber(String uniqNumber) {
        UniqNumber = uniqNumber;
    }

    public String getSpecialSigns() {
        return specialSigns;
    }

    public void setSpecialSigns(String specialSigns) {
        this.specialSigns = specialSigns;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate(){
        return birthdate;
    }

    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }

    public void setOwner(String userid){
        this.ownerid = userid;
    }

    public String getOwner(){
        return ownerid;
    }

}
