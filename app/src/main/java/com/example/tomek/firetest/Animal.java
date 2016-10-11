package com.example.tomek.firetest;

/**
 * Created by Tomek on 2016-10-11.
 */
public class Animal {

    private String name;
    private String birthdate;
    private String ownerid;

    public Animal(){

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
