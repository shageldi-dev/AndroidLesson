package com.shageldi.androidlessons.Model;

import java.util.Calendar;

public class Person {
    private String name;
    private String surname;
    private String dob;
    private String phoneNumber;

    // Shunyn icine maglumatlary girizmek ucin gerek
    public Person(String name, String surname, String dob, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    // 2000-11-18

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        // do anything here
    }

    public int getAge(){
        Calendar calendar=Calendar.getInstance();
        int year= calendar.get(Calendar.YEAR);
        int dobYear=Integer.parseInt(this.dob.split("-")[0]);
        return year-dobYear;
    }


}
