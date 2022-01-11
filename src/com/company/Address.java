package com.company;

import java.io.Serializable;

public class Address implements Serializable {
    private int number = 0;
    private String street = "N/A";
    private String suburbs = "N/A";
    private int postcode = 0;

    Address ( int number, String street, String suburbs, int postcode){
        this.number = number;
        this.street = street;
        this.suburbs = suburbs;
        this.postcode = postcode;
    }

    Address(){ }

    public int getNumber(){
        return number;
    }
    public String getStreet(){
        return street;
    }
    public String getSuburbs(){
        return suburbs;
    }
    public int getPostcode(){
        return postcode;
    }

    public void setNumber(int number){
        this.number = number;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public void setSuburbs(String suburbs){
        this.suburbs = suburbs;
    }
    public void setPostcode(int postcode){
        this.postcode = postcode;
    }

    public static boolean isEqual(Address a1, Address a2){
        if (a1.getNumber() == a2.getNumber()
                && a1.getPostcode() == a2.getPostcode()
                && a1.getStreet().equals(a2.getStreet())
                && a1.getSuburbs().equals(a2.getSuburbs())) {
            return true;
        } else {
            return false;
        }
    }
}
