package com.inovus.javatest.model;

import javax.persistence.*;

@Entity
public class RegNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String regNumber;

    public RegNumber() {
    }

    public RegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Override
    public String toString() {
        return "ID : " + this.id +
                "Reg Number : " + this.regNumber;
    }
}