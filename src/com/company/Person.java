package com.company;
import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    public static String MALE="MALE";
    public static String FEMALE="FEMALE";
    public static int MAX_PARENTS=2;

    private String fname = "";
    private String lname = "";
    private String lnameAfter = "";
    private String gender = "";
    private String bio = "";
    private Address address;
    private Person[] parents = new Person[MAX_PARENTS];
    private ArrayList<Person> children = new ArrayList<Person>();
    private Person spouse;
    private String date = "";

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLnameAfter() {
        return lnameAfter;
    }

    public void setLnameAfter(String lnameAfter) {
        this.lnameAfter = lnameAfter;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void addChild(Person child) {
        children.add(child);
    }
    public void addSpouse(Person spouse) {
        this.spouse = spouse;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress (Address address) {
        this.address = address;
    }

    public void setAddress(String street,String district, String country, String city) {
        address = new Address(street, district, city, country);
    }

    public  Person getParent(int index){
        return parents[index];
    }
    public Person[] getParents() {
        return parents;

    }
    public void setParents(Person[] parents){
        this.parents=parents;
    }
    public Person getChild(int index) {
        return children.get(index);
    }

    public ArrayList<Person> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Person> children) {
        this.children = children;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public boolean addParent(Person parent) {
        for (int i = 0; i < MAX_PARENTS; i++) {
            if (parents[i] == null) {
                parents[i] = parent;
                return true;
            }
        }
        return false;
    }


    public static boolean isEqual(Person p1, Person p2) {
        boolean flag = false;

        if ( p1.getFname().equals(p2.getFname()) &&
                p1.getLname().equals(p2.getLname()) &&
                p1.getGender().equals(p2.getGender()) &&
                p1.getSpouse() == p2.getSpouse()) {

            flag = true;

            for (int i = 0; i < p1.children.size(); i++) {
                if (p1.getChild(i) != p2.getChild(i)) {
                    flag = false;
                }
            }

            for (int i = 0; i < MAX_PARENTS; i++) {
                if (p1.getParent(i) != p2.getParent(i)) {
                    flag = false;
                }
            }


        }

        return flag;
    }

    @Override
    public String toString() {
        return fname;
    }




}


