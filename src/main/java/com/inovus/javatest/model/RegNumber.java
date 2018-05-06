package com.inovus.javatest.model;

import javax.persistence.*;

@Entity
public class RegNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String regNumber;

    @Column
    private String letterPart;

    @Column
    private String numberPart;

    public RegNumber() {
        setRegNumber(this.letterPart.substring(0,1) + this.numberPart + this.letterPart.substring(1));

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

    public String getLetterPart() {
        return letterPart;
    }

    public void setLetterPart(String letterPart) {
        this.letterPart = letterPart;
    }

    public String getNumberPart() {
        return numberPart;
    }

    public void setNumberPart(String numberPart) {
        this.numberPart = numberPart;
    }

    @Override
    public String toString() {
        return "ID : " + this.id +
                "Reg Number : " + this.regNumber;
    }
}