package com.company;

import java.io.Serializable;

public class Address implements Serializable {
    private String street = "N/A";
    private String district = "N/A";
    private String city = "N/A";
    private String country = "N/A";

    Address ( String street, String district, String city, String country){
        this.street = street;
        this.district = district;
        this.city = city;
        this.country = country;
    }

    public String getStreet(){
        return street;
    }
    public String getDistrict(){
        return district;
    }
    public String getCity(){
        return city;
    }
    public String getCountry(){
        return country;
    }

    public void setStreet(String street){
        this.street = street;
    }
    public void setDistrict(String district){
        this.district = district;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setCountry(String country){
        this.country = country;
    }
}
