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






}
