package com.mx.ontech.tianguis.postgresql.crudrestpostgresql.models;

import java.io.Serializable;

public class Products implements Serializable {

    private static final long serialVersionUID = -34756611169219611L;

    private String fname;
    private  String lName;
    private int age;

    protected Products() {
        super();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
