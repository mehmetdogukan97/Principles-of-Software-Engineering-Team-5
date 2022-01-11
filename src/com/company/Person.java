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
    private Person[] parents = new Person[MAX_PARENTS];
    private ArrayList<Person> children = new ArrayList<Person>();
    private Person spouse;

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
}
